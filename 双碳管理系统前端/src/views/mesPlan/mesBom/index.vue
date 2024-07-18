<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['mesPlan:mesBom:add']"
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
          v-hasPermi="['mesPlan:mesBom:edit']"
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
          v-hasPermi="['mesPlan:mesBom:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mesPlan:mesBom:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="mesBomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="产品" align="center" prop="productName" />
      <el-table-column label="型号" align="center" prop="productModel" />
      <el-table-column label="规格" align="center" prop="productSpecification" />
      <el-table-column label="单位" align="center" prop="productUnit" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mesPlan:mesBom:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mesPlan:mesBom:remove']"
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

    <!-- 添加或修改产品BOM单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" :inline="true">
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="产品" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入产品"  disabled/>
        </el-form-item>

        <el-form-item label="产品模型" prop="produccrModelId">
          <el-select v-model="form.produccrModelId" placeholder="请选择产品模型" clearable fileterable @change="handleModelChang">
            <el-option v-for="dict in productIdOptions" :key="dict.dictValue" :label="dict.dictLabel" :value="parseInt(dict.dictValue)"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="描述">
          <editor :form="form.description" :min-height="192" />
        </el-form-item>
        <el-divider content-position="center">BOM单明细信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddMesProductBomDetail">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteMesProductBomDetail">删除</el-button>
          </el-col>
        </el-row>

        <el-table :data="mesProductBomDetailList" :row-class-name="rowMesProductBomDetailIndex" @selection-change="handleMesProductBomDetailSelectionChange" ref="mesProductBomDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="物料" prop="materialName" width="250">
            <template slot-scope="scope">
             <el-form-item :prop="'mesProductBomDetailList.'+scope.$index+'.materialName'" :rules="rules.materialName">
              <el-input v-model="scope.row.materialName" placeholder="请选择物料">
                <el-button v-on-more-click slot="append" @click="handleSelectMaterial(scope.$index)">选择</el-button>
              </el-input>
             </el-form-item>
            </template>
          </el-table-column>
          <el-table-column label="型号"  prop="materialModel"/>
          <el-table-column label="规格"  prop="materialSpecification" />
          <el-table-column label="单位"  prop="materialUnit"/>
          <el-table-column label="用量" prop="usage" width="150">
            <template slot-scope="scope">
             <el-form-item :prop="'mesProductBomDetailList.'+scope.$index+'.usage'" :rules="rules.usage">
              <el-input v-model="scope.row.usage" placeholder="请输入用量" />
             </el-form-item>
            </template>
          </el-table-column>
          <el-table-column label="备注" prop="remark" width="150">
            <template slot-scope="scope">
             <el-form-item :prop="'mesProductBomDetailList.'+scope.$index+'.remark'" :rules="rules.remark">
              <el-input v-model="scope.row.remark" placeholder="请输入备注" />
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

    <!-- 选择物料档案 -->
    <MaterialSelect :open="materialSelectOpen" @onCancel="handleMaterialCancel" @onSelected="handleMaterialSelected"> </MaterialSelect>
    
  </div>
</template>

<script>
import { listMesBom, getMesBom, delMesBom, addMesBom, updateMesBom, exportMesBom } from "@/api/mesPlan/mesBom";
import Editor from "@/components/MyEditor"
import {getProductModel} from "@/api/mesModel/productModel"
import MaterialSelect from "@/components/Material/MaterialSelect"
export default {
  name: "MesBom",
  components: {
    Editor,
    MaterialSelect
  },
  data() {
    return {
      currentRowIndex: null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMesProductBomDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 产品BOM单表格数据
      mesBomList: [],
      // BOM单明细表格数据
      mesProductBomDetailList: [],
      productIdOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      materialSelectOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        productId: null,
        description: null,
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
    this.getQueryData("query_product_model").then(res =>{
      this.productIdOptions = res.data;
    })


  },
  watch: {
    'mesProductBomDetailList': {
      handler(newVal, oldVal) {
        this.form.mesProductBomDetailList = newVal;
      },
      immediate: true,
      deep: true,
    }
  },
  methods: {

    handleSelectMaterial(index){
      this.materialSelectOpen = true;
      this.currentRowIndex = index;
    },
    /** 查询产品BOM单列表 */
    getList() {
      this.loading = true;
      listMesBom(this.queryParams).then(response => {
        this.mesBomList = response.rows;
        this.total = response.t;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },



    handleMaterialSelected(list){
      if(list && list.length > 0){
        let material = list[0];
        this.mesProductBomDetailList[this.currentRowIndex].materialId = material.id;
        this.mesProductBomDetailList[this.currentRowIndex].materialName = material.name;
        this.mesProductBomDetailList[this.currentRowIndex].productModel = material.model;
        this.mesProductBomDetailList[this.currentRowIndex].productSpecification = material.specification
        this.mesProductBomDetailList[this.currentRowIndex].productUnit = material.unit;
        this.mesProductBomDetailList[this.currentRowIndex].productCode = material.code;
        
      }
      this.materialSelectOpen = false;
    },
    
 
    handleMaterialCancel(){
      this.materialSelectOpen = false;
    },
    /** 选择模型处理函数 */
    handleModelChang(val){
        if(val){
          getProductModel(val).then(res =>{
            let model = res.data;
            this.form.productId = model.productId;
            this.form.productName = model.productName;
            model.mesProductModelDetailList.forEach(material => {
              let obj = {};
              obj.materialId = material.materialId;
              obj.materialName = material.materialName;
              obj.materialModel = material.materialModel;
              obj.materialSpecification = material.materialSpecification;
              obj.materialUnit = material.materialUnit;
              this.mesProductBomDetailList.push(obj)
            });
          })
          
        }
    },

    productIdFormat(row, colum){
      return this.selectDictLabel(this.productIdOptions, row.prooductId);
    },

  
    /** 查询产品BOM单列表 */
    getList() {
      this.loading = true;
      listMesBom(this.queryParams).then(response => {
        this.mesBomList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
        name: null,
        productId: null,
        description: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        mesProductBomDetailList: [],
      };
      this.mesProductBomDetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
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
      this.title = "添加产品BOM单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMesBom(id).then(response => {
        this.form = response.data;
        this.mesProductBomDetailList = response.data.mesProductBomDetailList;
        this.open = true;
        this.title = "修改产品BOM单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          
          if (this.form.id != null) {
            updateMesBom(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMesBom(this.form).then(response => {
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
      this.$confirm('是否确认删除产品BOM单编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMesBom(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
	/** BOM单明细序号 */
    rowMesProductBomDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** BOM单明细添加按钮操作 */
    handleAddMesProductBomDetail() {
      let obj = {};
      obj.materialId = "";
      obj.materialName = "";
      obj.materialModel = "";
      obj.materialSpecification = "";
      obj.materialUnit = "";
      obj.usage = "";
      obj.remark = "";
      this.mesProductBomDetailList.push(obj);
    },
    /** BOM单明细删除按钮操作 */
    handleDeleteMesProductBomDetail() {
      if (this.checkedMesProductBomDetail.length == 0) {
        this.$alert("请先选择要删除的BOM单明细数据", "提示", { confirmButtonText: "确定", });
      } else {
        this.mesProductBomDetailList.splice(this.checkedMesProductBomDetail[0].index - 1, 1);
      }
    },
    /** 单选框选中数据 */
    handleMesProductBomDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.mesProductBomDetail.clearSelection();
        this.$refs.mesProductBomDetail.toggleRowSelection(selection.pop());
      } else {
        this.checkedMesProductBomDetail = selection;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有产品BOM单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMesBom(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
