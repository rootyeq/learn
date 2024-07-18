package com.neu.carbon.mes.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 产品BOM单对象 mes_product_bom
 * 
 * @author neuedu
 * @date 2024-05-15
 */
@ApiModel("产品BOM单")
public class MesProductBom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 名称 */
    @ApiModelProperty("名称")
    @Excel(name = "名称")
    private String name;

    /** 产品 */
    @ApiModelProperty("产品")
    @Excel(name = "产品")
    private Long productId;

    @ApiModelProperty("产品名称")
    @Excel(name = "产品名称")
    private String productName;

    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private String productCode;

    @ApiModelProperty("规格")
    @Excel(name = "规格")
    private String productSpecification;

    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String productUnit;

    @ApiModelProperty("型号")
    @Excel(name = "型号")
    private String productModel;




    /** 描述 */
    @ApiModelProperty("描述")
    @Excel(name = "描述")
    private String description;

    /** BOM单明细信息 */
	@ApiModelProperty(hidden = true)
    private List<MesProductBomDetail> mesProductBomDetailList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    public List<MesProductBomDetail> getMesProductBomDetailList()
    {
        return mesProductBomDetailList;
    }

    public void setMesProductBomDetailList(List<MesProductBomDetail> mesProductBomDetailList)
    {
        this.mesProductBomDetailList = mesProductBomDetailList;
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

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("productId", getProductId())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("mesProductBomDetailList", getMesProductBomDetailList())
            .toString();
    }
}
