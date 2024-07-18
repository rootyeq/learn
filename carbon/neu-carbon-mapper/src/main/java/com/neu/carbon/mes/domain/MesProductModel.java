package com.neu.carbon.mes.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 产品建模对象 mes_product_model
 * 
 * @author neuedu
 * @date 2024-05-06
 */
@ApiModel("产品建模")
public class MesProductModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    @ApiModelProperty("产品id")
    private Long productId;

    /** 产品名称 */
    @ApiModelProperty("产品名称")
    private String productName;

    /** 产品编码 */
    @ApiModelProperty("产品编码")
    private String productCode;

    /** 产品型号 */
    @ApiModelProperty("产品型号")
    private String productModel;



    /** 产品规格 */
    @ApiModelProperty("产品规格")
    private String productSpecification;


    /** 产品单位 */
    @ApiModelProperty("产品单位")
    private String productUnit;


    /** 物料档案编号 */
    @ApiModelProperty("物料档案编号")
    @Excel(name = "物料档案编号")
    private Long materialId;

    /** 模型名称 */
    @ApiModelProperty("模型名称")
    @Excel(name = "模型名称")
    private String modelName;

    /** 产品模型明细信息 */
	@ApiModelProperty(hidden = true)
    private List<MesProductModelDetail> mesProductModelDetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setModelName(String modelName) 
    {
        this.modelName = modelName;
    }

    public String getModelName() 
    {
        return modelName;
    }

    public List<MesProductModelDetail> getMesProductModelDetailList()
    {
        return mesProductModelDetailList;
    }

    public void setMesProductModelDetailList(List<MesProductModelDetail> mesProductModelDetailList)
    {
        this.mesProductModelDetailList = mesProductModelDetailList;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductSpecification() {
        return productSpecification;
    }

    public void setProductSpecification(String productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("materialId", getMaterialId())
            .append("modelName", getModelName())
            .append("remark", getRemark())
            .append("mesProductModelDetailList", getMesProductModelDetailList())
            .toString();
    }
}
