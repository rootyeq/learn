package com.neu.carbon.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

/**
 * BOM单明细对象 mes_product_bom_detail
 * 
 * @author neuedu
 * @date 2024-05-15
 */
public class MesProductBomDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** bom单id */
    @Excel(name = "bom单id")
    private Long bomId;

    /** 物料id */
    @Excel(name = "物料id")
    private Long materialId;

    /** 用量 */
    @Excel(name = "用量")
    private Long usage;


    @Excel(name = "物料型号")
    private String materialModel;

    @Excel(name = "物料名称")
    private String materialName;



    @Excel(name = "物料单位")
    private String materialUnit;

    @Excel(name = "物料规格")
    private String materialSpecification;

    @Excel(name = "物料编码")
    private String materialCode;










    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBomId(Long bomId) 
    {
        this.bomId = bomId;
    }

    public Long getBomId() 
    {
        return bomId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setUsage(Long usage) 
    {
        this.usage = usage;
    }

    public Long getUsage() 
    {
        return usage;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bomId", getBomId())
            .append("materialId", getMaterialId())
            .append("usage", getUsage())
            .append("remark", getRemark())
            .toString();
    }
}
