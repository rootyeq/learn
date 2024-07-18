<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="领料单号" prop="serialNo">
        <el-input
          v-model="queryParams.serialNo"
          placeholder="请输入领料单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
  


      <el-form-item label="领取日期">
        <el-date-picker clearable size="small" v-model="daterangeRequisitionDate" style="width:240px"  type="daterange" 
        value-format="yyyy-MM-dd" start-placeholder="开始日期"  end-placeholder="结束日期" range-separator="-"  />
      </el-form-item>
      <el-form-item label="申请人" prop="applyUser">
       <el-select v-model="queryParams.applyUser" placeholder="请选择申请人" clearabel fiterable size="small">
          <el-option v-for="dict in applyUserOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
      </el-select>
      </el-form-item>
      <el-form-item label="申请时间" >
        <el-date-picker clearable size="small"
          v-model="daterapplyTime"
          type="daterange"
          value-format="yyyy-MM-dd"
          start-placeholder="开始日期"
          range-separator="-"
          end-placeholder="结束日期"
          style=" width: 240px"
          >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="申请状态" prop="applyStatus">
        <el-select v-model="queryParams.applyStatus" placeholder="请选择申请状态" clearable filterable size="small">
          <el-option v-for="dict in applyStatusOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>
      <el-form-item label="审核人" prop="auditUser">
        <el-select  v-model="queryParams.auditUser"  placeholder="请选择审核人" clearable filterbale  size="small" >
          <el-option v-for="dict in aduitUserOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"></el-option>
       </el-select>
      </el-form-item>

      <el-form-item label="审核时间" >
        <el-date-picker clearable size="small"
          v-model="daterangeAuditTime"
          style="width:240px"
          type="daterange"
          value-format="yyyy-MM-dd"
          start-placeholder="开始时间"
          range-separator="-"
          end-placeholder="结束时间"
          >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="审核状态" prop="auditStatus">
        <el-select v-model="queryParams.auditStatus" placeholder="请选择审核状态" clearable filterable size="small">
          <el-option v-for="dict in  auditStatusFomatOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mesProduct:requisitionApply:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['mesProduct:requisitionApply:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['mesProduct:requisitionApply:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mesProduct:requisitionApply:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="requisitionApplyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="领料单号" align="center" prop="serialNo" />
      <el-table-column label="生产计划" align="center" prop="planNo" />
      <el-table-column label="计划排产" align="center" prop="scheduleNo" />
      <el-table-column label="产品" align="center" prop="materialName" />
      <el-table-column label="型号" align="center" prop="materialModel" />
      <el-table-column label="规格" align="center" prop="materialSpecification" />
      <el-table-column label="单位" align="center" prop="materialUnit" />
      <el-table-column label="领取日期" align="center" prop="requisitionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.requisitionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请人" align="center" prop="applyUser"  :formatter="applyUserFormat"/>
      <el-table-column label="申请时间" align="center" prop="applyTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.applyTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="申请状态" align="center" prop="applyStatus" :formatter="applyStausFormat"/>
      <el-table-column label="审核人" align="center" prop="auditUser" :formatter="aduitUserFormat"/>
      <el-table-column label="审核时间" align="center" prop="auditTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.auditTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="auditStatus" :formatter="auditStatusFomat"/>
   
      <el-table-column label="状态" align="center" prop="status"  :formatter="statusFormat"/>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-no-more-click size="mini" type="text" icon="el-icon-edit"  @click="handleUpdate(scope.row)" v-hasPermi="['mesProduct:requisitionApply:edit']">修改</el-button>
          <el-button v-no-more-click v-if="scope.row.status == '1'" size="mini" type="text" icon="el-icon-check"  @click="handleCheck(scope.row.id, '2')" v-hasPermi="['mesProduct:requisitionApply:edit']">检验合格</el-button>
          <el-button v-no-more-click v-if="scope.row.status == '1'"   size="mini" type="text" icon="el-icon-check"  @click="handleCheck(scope.row.id, '3')" v-hasPermi="['mesProduct:requisitionApply:edit']">检验不合格</el-button>
          <el-button v-no-more-click v-if="scope.row.auditStatus != '1' && scope.row.applyStatus != '1'" size="mini" type="text" icon="el-icon-delete"  @click="handleDelete(scope.row)" v-hasPermi="['mesProduct:requisitionApply:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改领料单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :inline="true">
        <el-form-item label="领料编号" prop="serialNo">
          <el-input v-model="form.serialNo" placeholder="" disabled/>
        </el-form-item>
        <el-form-item label="计划排产" prop="planNo">
          <el-input v-model="form.planNo" placeholder="请选择生产计划" disabled>
              <el-button v-no-more-click slot="append" @click="handSelectScedule" :disabled="form.auditStatus !=  '1' && form.applyStatus != '1'? false:true">选择</el-button>
          </el-input>
        </el-form-item>
        <el-form-item label="产品" prop="materialName">
          <el-input v-model="form.materialName" placeholder=""  disabled/>
        </el-form-item>
        <el-form-item label="规格" prop="materialSpecification">
          <el-input v-model="form.materialSpecification" placeholder="" disabled/>
        </el-form-item>
        <el-form-item label="型号" prop="materialModel">
          <el-input v-model="form.materialModel" placeholder="" disabled/>
        </el-form-item>
        <el-form-item label="单位" prop="materialUnit">
          <el-input v-model="form.materialUnit" placeholder="" disabled/>
        </el-form-item>
        <el-form-item label="待产数量" prop="requireQuantity">
          <el-input v-model="form.requireQuantity" placeholder="" disabled/>
        </el-form-item>


        <el-form-item label="生产日期" prop="productDate">
          <el-date-picker clearable size="small"
            v-model="form.productDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="" disabled>
          </el-date-picker>
        </el-form-item>
        
        <el-form-item label="领取日期" prop="requisitionDate">
          <el-date-picker clearable size="small"
            v-model="form.requisitionDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="" disabled>
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="" disabled>
            <el-option v-for="dict in statusOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" disabled/>
        </el-form-item>


       
        <el-form-item label="申请人">
          <el-select v-model="form.applyUser" placeholder="" disabled>
            <el-option v-for="dict in applyUserOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="申请时间" prop="applyTime">
          <el-date-picker clearable size="small"
            v-model="form.applyTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择申请时间" disabled>
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请状态">
          <el-select v-model="form.applyStatus" placeholder="" disabled>
            <el-option v-for="dict in applyStatusOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="审核人" prop="auditUser">
          <el-select v-model="form.auditUser" placeholder="请输入审核人"  disabled>
              <el-option v-for="dict in aduitUserOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue"/>
          </el-select>
        </el-form-item>
        <el-form-item label="审核时间" prop="auditTime">
          <el-date-picker clearable size="small"
            v-model="form.auditTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="选择审核时间" disabled>
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核状态">
          <el-select v-model="form.auditStatus" placeholder="" disabled>
            <el-option v-for="dict in auditStatusFomatOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="dict.dictValue" />
          </el-select>
        </el-form-item>
        <el-form-item label="审核意见" prop="auditComment">
          <el-input v-model="form.auditComment" type="textarea" placeholder="" disabled/>
        </el-form-item>
       
     
        <el-divider content-position="center">领料单物料明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMesMaterialRequisitionDetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMesMaterialRequisitionDetail">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="mesMaterialRequisitionDetailList" :row-class-name="rowMesMaterialRequisitionDetailIndex" @selection-change="handleMesMaterialRequisitionDetailSelectionChange" ref="mesMaterialRequisitionDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物料" prop="materialName" width="150" >
            <template slot-scope="scope">
             <el-form-item :prop="'mesMaterialRequisitionDetailList.'+scope.$index+'.materialName'" :rules="rules.materialName">
              <el-input v-model="scope.row.materialName" placeholder="请输入物料档案id" disabled />
             </el-form-item>
            </template>
          </el-table-column>
          <el-table-column label="型号" prop="materialModel" width="120">
          </el-table-column>
          <el-table-column label="规格" prop="materialSpecification" width="120">
          </el-table-column>
          <el-table-column label="单位" prop="materialUnit" width="120">
          </el-table-column>

          <el-table-column label="需求数量" prop="requireQuantity" >
            <template slot-scope="scope">
             <el-form-item :prop="'mesMaterialRequisitionDetailList.'+scope.$index+'.requireQuantity'" :rules="rules.requireQuantity">
              <el-input v-model="scope.row.requireQuantity" placeholder="请输入需求数量" disabled />
             </el-form-item>
            </template>
          </el-table-column>
          <el-table-column label="已领数量" prop="receiveQuantity" width="100">
            <template slot-scope="scope">
             <el-form-item :prop="'mesMaterialRequisitionDetailList.'+scope.$index+'.receiveQuantity'" :rules="rules.receiveQuantity">
              <el-input v-model="scope.row.receiveQuantity" placeholder="请输入已领数量"  disabled/>
             </el-form-item>
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="detailRemark" width="150">
            <template slot-scope="scope">
             <el-form-item :prop="'mesMaterialRequisitionDetailList.'+scope.$index+'.detailRemark'" :rules="rules.detailRemark">
              <el-input v-model="scope.row.detailRemark" placeholder="请输入备注" />
             </el-form-item>
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <product-schedule-select status="1" receiveStatus="0" :open="scheduleOpen" @onSelected="handleScheduleSelected" @onCancel="handleScheduleCancel"></product-schedule-select>
  </div>
</template>

<script>
import { listRequisitionApply, getRequisitionApply, delRequisitionApply, addRequisitionApply, updateRequisitionApply, exportRequisitionApply } from "@/api/mesProduct/requisitionApply";
import ProductScheduleSelect from '@/components/product/ProductScheduleSelect.vue'
import {getPoductSchedule} from '@/api/mesPlan/productSchedule' 
export default {
  name: "RequisitionApply",
  components: {
    ProductScheduleSelect,
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMesMaterialRequisitionDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 领料单表格数据
      requisitionApplyList: [],
      //申请人字典
      applyUserOptions:[],
      //申请状态字典
      applyStatusOptions:[],
      //审核人字典
      aduitUserOptions: [],
      //审核状态字典
      auditStatusFomatOptions:[],
      //领料状态字典
      statusOptions: [],
      // 领料单物料明细表格数据
      mesMaterialRequisitionDetailList: [],
      //领取时间范围
      daterangeRequisitionDate:[],
      //申请时间范围
      daterapplyTime:[],
      //审核时间范围
      daterangeAuditTime:[],
   

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      scheduleOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serialNo: null,
        planId: null,
        scheduleId: null,
        productId: null,
        requisitionDate: null,
        applyUser: null,
        applyTime: null,
        applyStatus: null,
        auditUser: null,
        auditTime: null,
        auditStatus: null,
        auditComment: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getQueryData('query_user').then(res =>{
        this.applyUserOptions = res.data
      });
      this.getDicts('apply_status').then(res =>{
        this.applyStatusOptions = res.data
      });
      this.getQueryData('query_user').then(res =>{
        this.aduitUserOptions = res.data
      });
      this.getDicts('audit_status').then(res =>{
        this.auditStatusFomatOptions = res.data;
      });
      this.getDicts('receive_material_status').then(res =>{
        this.statusOptions = res.data;
      });
    
  },
  watch: {
    'mesMaterialRequisitionDetailList': {
      handler(newVal, oldVal) {
        this.form.mesMaterialRequisitionDetailList = newVal;
      },
      immediate: true,
      deep: true,
    }
  },
  methods: {
    handleCheck(id,status){
      let params = {
        id : id,
        status : status
      }
    },

    handSelectScedule(){
      this.scheduleOpen = true
    },
    handleScheduleSelected(row){
      this.form.planId = row.planId;
      this.form.planNo = row.planNo;
      this.form.scheduleId = row.scheduleId;
      this.form.materialName = row.materialName;
      this.form.materialModel = row.materialModel;
      this.form.materialSpecification = row.materialSpecification;
      this.form.projectId = row.projectId;
      this.form.materialUnit = row.materialUnit;
      this.form.requireQuantity = row.requireQuantity;
      this.form.productDate = row.productDate;
      this.mesMaterialRequisitionDetailList = [];
      getPoductSchedule(this.form.scheduleId).then(res =>{
        let list = res.data.mesMaterialRequisitionDetail;
        let obj = {};
        list.forEach(model =>{
          obj.materialId = model.materialId;
          obj.materialName = model.materialName;
          obj.materialModel = model.materialModel;
          obj.materialSpecification = model.materialSpecification;
          obj.materialUnit = model.materialUnit;
          obj.requireQuantity = model.requireQuantity;
          obj.productDate = model.productDate;
          obj.planId = model.planId;
          obj.planNo = model.planNo;
          obj.scheduleId = model.scheduleId;
          this.mesMaterialRequisitionDetailList.push(obj);
        });
      })
      this.scheduleOpen = false;
    },

    handleScheduleCancel(){
      this.scheduleOpen = false
    },

    /** 查询领料单列表 */
    getList() {
      this.loading = true;
      listRequisitionApply(this.queryParams).then(response => {
        console.log(response)
        this.requisitionApplyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });

      if(null != this.daterangeRequisitionDate && '' != this.daterangeRequisitionDate){
        this.queryParams.params["beginRequisitionDate"] = this.daterangeRequistionDate[0];
        this.queryParams.params["endRequisitionDate"] = this.daterangeRequistionDate[1];
      };
      if(null != this.daterapplyTime && ''!= this.daterapplyTime){
        this.queryParams.params["beginapplyTime"] = this.daterapplytime[0];
        this.queryParams.params["endapplyTime"] = this.daterapplyTime[1]
      };
      if(null !=this.daterangeAuditTime && '' != this.daterangeAuditTime){
        this.queryParams.params["beginAuditTime"] = this.daterangeAuditTime[0];
        this.queryParams.params["endAuditTime"] = this.daterangeAuditTime[1];
      };
    },
    applyUserFormat(row){
      return this.selectDictLabel(this.applyUserOptions, row.applyUser)
    },
    applyStausFormat(row){
      return this.selectDictLabel(this.applyStatusOptions, row.applyStatus)
    },
    aduitUserFormat(row){
      return this.selectDictLabel(this.aduitUserOptions, row.auditUser)
    },
    auditStatusFomat(row){
      return this.selectDictLabel(this.auditStatusFomatOptions, row.auditStatus)
    },
    statusFormat(row){
      return this.selectDictLabel(this.statusOptions, row.status)
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        serialNo: null,
        planId: null,
        scheduleId: null,
        productId: null,
        requisitionDate: null,
        applyUser: null,
        applyTime: null,
        applyStatus: "0",
        auditUser: null,
        auditTime: null,
        auditStatus: "0",
        auditComment: null,
        status: "0",
        remark: null,
        mesMaterialRequisitionDetailList: [],
      };
      this.mesMaterialRequisitionDetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeRequisitionDate = [];
      this.daterapplyTime =[];
      this.daterangeAuditTime= [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加领料单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getRequisitionApply(id).then(response => {
        this.form = response.data;
        this.mesMaterialRequisitionDetailList = response.data.mesMaterialRequisitionDetailList;
        this.open = true;
        this.title = "修改领料单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          
          if (this.form.id != null) {
            updateRequisitionApply(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRequisitionApply(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除领料单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delRequisitionApply(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
	/** 领料单物料明细序号 */
    rowMesMaterialRequisitionDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 领料单物料明细添加按钮操作 */
    handleAddMesMaterialRequisitionDetail() {
      let obj = {};
      obj.materialId = "";
      obj.requireQuantity = "";
      obj.receiveQuantity = "";
      obj.detailRemark = "";
      this.mesMaterialRequisitionDetailList.push(obj);
    },
    /** 领料单物料明细删除按钮操作 */
    handleDeleteMesMaterialRequisitionDetail() {
      if (this.checkedMesMaterialRequisitionDetail.length == 0) {
        this.$alert("请先选择要删除的领料单物料明细数据", "提示", { confirmButtonText: "确定", });
      } else {
        this.mesMaterialRequisitionDetailList.splice(this.checkedMesMaterialRequisitionDetail[0].index - 1, 1);
      }
    },
    /** 单选框选中数据 */
    handleMesMaterialRequisitionDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.mesMaterialRequisitionDetail.clearSelection();
        this.$refs.mesMaterialRequisitionDetail.toggleRowSelection(selection.pop());
      } else {
        this.checkedMesMaterialRequisitionDetail = selection;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有领料单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportRequisitionApply(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
