package com.neu.carbon.mes.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 生产完工单对象 mes_product_finish
 * 
 * @author neuedu
 * @date 2024-06-03
 */
@ApiModel("生产完工单")
public class MesProductFinish extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private Long id;

    /** 生产完工单号 */
    @ApiModelProperty("生产完工单号")
    @Excel(name = "生产完工单号")
    private String serialNo;






    /** 生产计划编号 */
    @ApiModelProperty("生产计划编号")
    @Excel(name = "生产计划编号")
    private Long planId;

    /** 计划排产编号 */
    @ApiModelProperty("计划排产编号")
    @Excel(name = "计划排产编号")
    private Long scheduleId;

    /** 生产作业编号 */
    @ApiModelProperty("生产作业编号")
    @Excel(name = "生产作业编号")
    private Long jobId;

    /** 产品id */
    @ApiModelProperty("产品id")
    @Excel(name = "产品id")
    private Long materialId;

    /** 完工日期 */
    @ApiModelProperty(value="完工日期",example = "2021-09-10")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finishDate;

    /** 生产数量 */
    @ApiModelProperty("生产数量")
    @Excel(name = "生产数量")
    private Long finishQuantity;

    /** 生产批号 */
    @ApiModelProperty("生产批号")
    @Excel(name = "生产批号")
    private String batchNo;

    /** 状态 */
    @ApiModelProperty("状态")
    @Excel(name = "状态")
    private String status;


    @ApiModelProperty("产品")
    @Excel(name = "产品")
    private String materialName;

    private  String materialCode;

    private String materialModel;

    private String  planNo;

    private  String planName;

    private String jobName;

    private String materialSpecification;

    private String materialUnit;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
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

    public String getMaterialModel() {
        return materialModel;
    }

    public void setMaterialModel(String materialModel) {
        this.materialModel = materialModel;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setSerialNo(String serialNo) 
    {
        this.serialNo = serialNo;
    }

    public String getSerialNo() 
    {
        return serialNo;
    }
    public void setPlanId(Long planId) 
    {
        this.planId = planId;
    }

    public Long getPlanId() 
    {
        return planId;
    }
    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }
    public void setJobId(Long jobId) 
    {
        this.jobId = jobId;
    }

    public Long getJobId() 
    {
        return jobId;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setFinishDate(Date finishDate) 
    {
        this.finishDate = finishDate;
    }

    public Date getFinishDate() 
    {
        return finishDate;
    }
    public void setFinishQuantity(Long finishQuantity) 
    {
        this.finishQuantity = finishQuantity;
    }

    public Long getFinishQuantity() 
    {
        return finishQuantity;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return "MesProductFinish{" +
                "id=" + id +
                ", serialNo='" + serialNo + '\'' +
                ", planId=" + planId +
                ", scheduleId=" + scheduleId +
                ", jobId=" + jobId +
                ", materialId=" + materialId +
                ", finishDate=" + finishDate +
                ", finishQuantity=" + finishQuantity +
                ", batchNo='" + batchNo + '\'' +
                ", status='" + status + '\'' +
                ", materialName='" + materialName + '\'' +
                '}';
    }
}
