<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="流水号" prop="serialNo">
        <el-input
          v-model="queryParams.serialNo"
          placeholder="请输入流水号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="领料单号" prop="materialRequisitionId">
        <el-input
          v-model="queryParams.materialRequisitionId"
          placeholder="请输入领料单号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
    
      <el-form-item label="物料名称" prop="materialName">
        <el-input
          v-model="queryParams.materialName"
          placeholder="请输入物料名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      
      
      <el-form-item label="检验人" prop="checkUser">
        <el-input
          v-model="queryParams.checkUser"
          placeholder="请输入检验人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检验时间" prop="checkDate">
        <el-date-picker clearable size="small"
          v-model="daterangeCheckDate"
          type="daterange"
          style="width: 240px;"
          value-format="yyyy-MM-dd"
         start-placeholder="开始时间"
         end-placeholder="结束时间"
         range-separator="-"
         >
        </el-date-picker>
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
          v-hasPermi="['mesCheck:materialCheck:add']"
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
          v-hasPermi="['mesCheck:materialCheck:edit']"
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
          v-hasPermi="['mesCheck:materialCheck:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mesCheck:materialCheck:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="materialCheckList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="流水号" align="center" prop="serialNo" />
      <el-table-column label="领料单号" align="center" prop="materialRequisitionNo" />
      <el-table-column label="物料名称" align="center" prop="materialName" />
      <el-table-column label="规格" align="center" prop="materialSpecification" />
      <el-table-column label="型号" align="center" prop="materialModel" />
      <el-table-column label="单位" align="center" prop="materialUnit" />
      <el-table-column label="需求数量" align="center" prop="requireQuantity" />
      <el-table-column label="已领数量" align="center" prop="receiveQuantity" />
      <el-table-column label="抽检数量" align="center" prop="checkQuantity" />
    
      <el-table-column label="检验项" align="center" prop="checkItemName" />
      <el-table-column label="标准值上限" align="center" prop="maxValue" />
      <el-table-column label="标准值下限" align="center" prop="minValue" />
     
      <el-table-column label="检验值" align="center" prop="checkValue" />
      <el-table-column label="检验结果" align="center" prop="checkResult" :formatter="checkResultFormat" />
      <el-table-column label="检验人" align="center" prop="checkUser" :formatter="checkUserFormat" />
      <el-table-column label="检验时间" align="center" prop="checkDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkDate, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mesCheck:materialCheck:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mesCheck:materialCheck:remove']"
          >删除</el-button>
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

    <!-- 添加或修改物料检验单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="流水号" prop="serialNo">
          <el-input v-model="form.serialNo" placeholder="请输入流水号"  disabled/>
        </el-form-item>
        <el-form-item label="领料编号" prop="materialRequisitionNo">
          <el-input v-model="form.materialRequisitionNo" placeholder="请输入领料单流水" />
        </el-form-item>
        <el-form-item label="物料名称" prop="materialId">
          <el-input v-model="form.materialId" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="检验标准编号" prop="checkStandardId">
          <el-input v-model="form.checkStandardId" placeholder="请输入检验标准编号" />
        </el-form-item>
        <el-form-item label="需求数量" prop="requireQuantity">
          <el-input v-model="form.requireQuantity" placeholder="请输入需求数量" />
        </el-form-item>
        <el-form-item label="已领数量" prop="receiveQuantity">
          <el-input v-model="form.receiveQuantity" placeholder="请输入已领数量" />
        </el-form-item>
        <el-form-item label="抽检数量" prop="checkQuantity">
          <el-input v-model="form.checkQuantity" placeholder="请输入抽检数量" />
        </el-form-item>
        <el-form-item label="检验值" prop="checkValue">
          <el-input v-model="form.checkValue" placeholder="请输入检验值" />
        </el-form-item>
        <el-form-item label="标准值上限" prop="maxValue">
          <el-input v-model="form.maxValue" placeholder="请输入标准值上限" />
        </el-form-item>
        <el-form-item label="标准值下限" prop="minValue">
          <el-input v-model="form.minValue" placeholder="请输入标准值下限" />
        </el-form-item>
        <el-form-item label="检验结果" prop="checkResult">
          <el-select v-model="form.checkResult" placeholder="请选择检验结果" clearable filterable>
            <el-option
              v-for="dict in checkResultOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
       
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMaterialCheck, getMaterialCheck, delMaterialCheck, addMaterialCheck, updateMaterialCheck, exportMaterialCheck } from "@/api/mesCheck/materialCheck";

export default {
  name: "MaterialCheck",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 物料检验单表格数据
      materialCheckList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 检验结果字典
      checkResultOptions: [],
      checkUserOptions:[],

      //检查时间范围
      daterangeCheckDate:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serialNo: null,
        materialRequisitionId: null,
        materialRequisitionNo: null,
        materialId: null,
        checkStandardId: null,
        requireQuantity: null,
        receiveQuantity: null,
        checkQuantity: null,
        checkValue: null,
        maxValue: null,
        minValue: null,
        checkResult: null,
        checkUser: null,
        checkDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        materialId: [
          { required: true, message: "物料名称不能为空", trigger: "blur" }
        ],
        checkQuantity: [
          { required: true, message: "抽检数量不能为空", trigger: "blur" }
        ],
        checkValue: [
          { required: true, message: "检验值不能为空", trigger: "blur" }
        ],
        checkResult: [
          { required: true, message: "检验结果不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("mes_check_result").then(response => {
      this.checkResultOptions = response.data;
    });
    this.getQueryData("query_user").then(res =>{
        this.checkUserOptions = res.data;
    })
  },
  methods: {
    /** 查询物料检验单列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = []
      if (null != this.daterangeCheckDate && '' != this.daterangeCheckDate) {
          this.queryParams.params["beginCheckDate"] = this.daterangeCheckDate[0];
          this.queryParams.params["endCheckDate"] = this.daterangeCheckDate[1];
        }

      listMaterialCheck(this.queryParams).then(response => {
        this.materialCheckList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 检验结果字典翻译
    checkResultFormat(row, column) {
      return this.selectDictLabel(this.checkResultOptions, row.checkResult);
    },
    checkUserFormat(row){
      return this.selectDictLabel(this.checkUserOptions, row.checkUser);
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
        materialRequisitionId: null,
        materialRequisitionNo: null,
        materialId: null,
        checkStandardId: null,
        requireQuantity: null,
        receiveQuantity: null,
        checkQuantity: null,
        checkValue: null,
        maxValue: null,
        minValue: null,
        checkResult: null,
        remark: null,
        checkUser: null,
        checkDate: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeCheckDate = [],
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
      this.title = "添加物料检验单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMaterialCheck(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料检验单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMaterialCheck(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMaterialCheck(this.form).then(response => {
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
      this.$confirm('是否确认删除物料检验单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMaterialCheck(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有物料检验单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMaterialCheck(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
