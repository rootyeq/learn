package com.neu.carbon.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 领料单物料明细对象 mes_material_requisition_detail
 * 
 * @author neuedu
 * @date 2024-05-26
 */
@ApiModel("领料单物料明细")
public class MesMaterialRequisitionDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    private Long id;

    /** 领料单id */
    @ApiModelProperty("领料单id")
    @Excel(name = "领料单id")
    private Long materialRequisitionId;

    /** 物料档案id */
    @ApiModelProperty("物料档案id")
    @Excel(name = "物料档案id")
    private Long materialId;

    /** 需求数量 */
    @ApiModelProperty("需求数量")
    @Excel(name = "需求数量")
    private Long requireQuantity;

    /** 已领数量 */
    @ApiModelProperty("已领数量")
    @Excel(name = "已领数量")
    private Long receiveQuantity;

    /** 备注 */
    @ApiModelProperty("备注")
    @Excel(name = "备注")
    private String detailRemark;

    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    @ApiModelProperty("物料型号")
    @Excel(name = "物料型号")
    private String materialModel;

    @ApiModelProperty("物料规格")
    @Excel(name = "物料规格")
    private String materialSpecification;

    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String materialUnit;

    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String materialCode;



    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMaterialRequisitionId(Long materialRequisitionId) 
    {
        this.materialRequisitionId = materialRequisitionId;
    }

    public Long getMaterialRequisitionId() 
    {
        return materialRequisitionId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setRequireQuantity(Long requireQuantity) 
    {
        this.requireQuantity = requireQuantity;
    }

    public Long getRequireQuantity() 
    {
        return requireQuantity;
    }
    public void setReceiveQuantity(Long receiveQuantity) 
    {
        this.receiveQuantity = receiveQuantity;
    }

    public Long getReceiveQuantity() 
    {
        return receiveQuantity;
    }
    public void setDetailRemark(String detailRemark) 
    {
        this.detailRemark = detailRemark;
    }

    public String getDetailRemark() 
    {
        return detailRemark;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getMaterialSpecification() {
        return materialSpecification;
    }

    public void setMaterialSpecification(String materialSpecification) {
        this.materialSpecification = materialSpecification;
    }

    public String getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
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
            .append("materialRequisitionId", getMaterialRequisitionId())
            .append("materialId", getMaterialId())
            .append("requireQuantity", getRequireQuantity())
            .append("receiveQuantity", getReceiveQuantity())
            .append("detailRemark", getDetailRemark())
            .toString();
    }
}
