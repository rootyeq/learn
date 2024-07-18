package com.neu.carbon.mes.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 计划排产对象 mes_product_schedule
 * 
 * @author neuedu
 * @date 2024-05-19
 */
@ApiModel("计划排产")
public class MesProductSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private Long id;


    @ApiModelProperty("计划号")
    @Excel(name = "计划号")
    private String planNo;

    /** 计划编号 */
    @ApiModelProperty("计划id")
    @Excel(name = "计划编id")
    private Long planId;

    /** 订单编号 */
    @ApiModelProperty("订单编号")
    @Excel(name = "订单编号")
    private Long orderId;

    /** 订单编号 */
    @ApiModelProperty("订单号")
    @Excel(name = "订单号")
    private String orderNo;
    /** 物料编号 */
    @ApiModelProperty("物料编号")
    @Excel(name = "物料编号")
    private Long materialId;

    @ApiModelProperty("物料编码")
    @Excel(name = "物料编码")
    private String materialCode;

    @ApiModelProperty("物料模型")
    @Excel(name = "物料模型")
    private String materialModel;


    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;


    @ApiModelProperty("物料规格")
    @Excel(name = "物料规格")
    private String materialSpecification;


    @ApiModelProperty("物料单位")
    @Excel(name = "物料单位")
    private String materialUnit;

    /** BOM单编号 */
    @ApiModelProperty("BOM单编号")
    @Excel(name = "BOM单编号")
    private Long bomId;

    /** 生产线编号 */
    @ApiModelProperty("生产线编号")
    @Excel(name = "生产线编号")
    private Long productLineId;

    /** 工艺编号 */
    @ApiModelProperty("工艺编号")
    @Excel(name = "工艺编号")
    private Long processId;

    /** 项目编号 */
    @ApiModelProperty("项目编号")
    @Excel(name = "项目编号")
    private Long projectId;

    /** 待产数量 */
    @ApiModelProperty("待产数量")
    @Excel(name = "待产数量")
    private Double requireQuantity;

    /** 已产数量 */
    @ApiModelProperty("已产数量")
    @Excel(name = "已产数量")
    private Long productQuantity;

    /** 生产日期 */
    @ApiModelProperty(value="生产日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productDate;

    /** 交货日期 */
    @ApiModelProperty(value="交货日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "交货日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryDate;

    /** 发布状态 */
    @ApiModelProperty("发布状态")
    @Excel(name = "发布状态")
    private String status;

    /** 领料状态 */
    @ApiModelProperty("领料状态")
    @Excel(name = "领料状态")
    private String receiveStatus;

    /** 排产编号 */
    @ApiModelProperty("排产编号")
    @Excel(name = "排产编号")
    private String serialNo;

    @ApiModelProperty(hidden = true)
    private List<MesProductScheduleMaterial> mesProductScheduleMaterialList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setBomId(Long bomId) 
    {
        this.bomId = bomId;
    }

    public Long getBomId() 
    {
        return bomId;
    }
    public void setProductLineId(Long productLineId) 
    {
        this.productLineId = productLineId;
    }

    public Long getProductLineId() 
    {
        return productLineId;
    }
    public void setProcessId(Long processId) 
    {
        this.processId = processId;
    }

    public Long getProcessId() 
    {
        return processId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setRequireQuantity(Double requireQuantity)
    {
        this.requireQuantity = requireQuantity;
    }

    public Double getRequireQuantity()
    {
        return requireQuantity;
    }
    public void setProductQuantity(Long productQuantity) 
    {
        this.productQuantity = productQuantity;
    }

    public Long getProductQuantity() 
    {
        return productQuantity;
    }
    public void setProductDate(Date productDate) 
    {
        this.productDate = productDate;
    }

    public Date getProductDate() 
    {
        return productDate;
    }
    public void setDeliveryDate(Date deliveryDate) 
    {
        this.deliveryDate = deliveryDate;
    }

    public Date getDeliveryDate() 
    {
        return deliveryDate;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setReceiveStatus(String receiveStatus) 
    {
        this.receiveStatus = receiveStatus;
    }

    public String getReceiveStatus() 
    {
        return receiveStatus;
    }
    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
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
    public List<MesProductScheduleMaterial> getMesProductScheduleMaterialList()
    {
        return mesProductScheduleMaterialList;
    }

    public void setMesProductScheduleMaterialList(List<MesProductScheduleMaterial> mesProductScheduleMaterialList)
    {
        this.mesProductScheduleMaterialList = mesProductScheduleMaterialList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("planId", getPlanId())
            .append("orderId", getOrderId())
            .append("materialId", getMaterialId())
            .append("bomId", getBomId())
            .append("productLineId", getProductLineId())
            .append("processId", getProcessId())
            .append("projectId", getProjectId())
            .append("requireQuantity", getRequireQuantity())
            .append("productQuantity", getProductQuantity())
            .append("productDate", getProductDate())
            .append("deliveryDate", getDeliveryDate())
            .append("remark", getRemark())
            .append("status", getStatus())
            .append("receiveStatus", getReceiveStatus())
            .append("serialNo", getSerialNo())
            .toString();
    }



}
