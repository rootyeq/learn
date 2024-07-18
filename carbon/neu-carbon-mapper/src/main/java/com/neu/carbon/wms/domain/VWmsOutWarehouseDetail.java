package com.neu.carbon.wms.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 物料出库明细对象 v_wms_out_warehouse_detail
 * 
 * @author neuedu
 * @date 2022-07-30
 */
@ApiModel("物料出库明细")
public class VWmsOutWarehouseDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物料 */
    @ApiModelProperty("物料")
    @Excel(name = "物料")
    private Long materialId;

    /** 生产批号 */
    @ApiModelProperty("生产批号")
    @Excel(name = "生产批号")
    private String batchNo;

    /** 发货数量 */
    @ApiModelProperty("发货数量")
    @Excel(name = "发货数量")
    private Double outQuantity;

    /** 仓库 */
    @ApiModelProperty("仓库")
    private Long whId;

    /** 库区 */
    @ApiModelProperty("库区")
    private Long whRegionId;

    /** 库位 */
    @ApiModelProperty("库位")
    private Long whLocationId;

    /** 业务类型 */
    @ApiModelProperty("业务类型")
    private String bizType;

    /** 出库日期 */
    @ApiModelProperty(value="出库日期",example = "2021-09-10 10:20:40")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date inWhDate;

    /** 物料名称 */
    @ApiModelProperty("物料名称")
    @Excel(name = "物料名称")
    private String materialName;

    /** 型号 */
    @ApiModelProperty("型号")
    @Excel(name = "型号")
    private String materialModel;

    /** 规格 */
    @ApiModelProperty("规格")
    @Excel(name = "规格")
    private String materialSpecification;

    /** 单位 */
    @ApiModelProperty("单位")
    @Excel(name = "单位")
    private String materialUnit;

    /** 仓库名 */
    @ApiModelProperty("仓库名")
    @Excel(name = "仓库名")
    private String whName;

    /** 库区名称 */
    @ApiModelProperty("库区名称")
    @Excel(name = "库区名称")
    private String whRegionName;

    /** 库位名称 */
    @ApiModelProperty("库位名称")
    @Excel(name = "库位名称")
    private String whLocationName;

    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
    }
    public void setBatchNo(String batchNo) 
    {
        this.batchNo = batchNo;
    }

    public String getBatchNo() 
    {
        return batchNo;
    }
    public void setOutQuantity(Double outQuantity) 
    {
        this.outQuantity = outQuantity;
    }

    public Double getOutQuantity() 
    {
        return outQuantity;
    }
    public void setWhId(Long whId) 
    {
        this.whId = whId;
    }

    public Long getWhId() 
    {
        return whId;
    }
    public void setWhRegionId(Long whRegionId) 
    {
        this.whRegionId = whRegionId;
    }

    public Long getWhRegionId() 
    {
        return whRegionId;
    }
    public void setWhLocationId(Long whLocationId) 
    {
        this.whLocationId = whLocationId;
    }

    public Long getWhLocationId() 
    {
        return whLocationId;
    }
    public void setBizType(String bizType) 
    {
        this.bizType = bizType;
    }

    public String getBizType() 
    {
        return bizType;
    }
    public void setInWhDate(Date inWhDate) 
    {
        this.inWhDate = inWhDate;
    }

    public Date getInWhDate() 
    {
        return inWhDate;
    }
    public void setMaterialName(String materialName) 
    {
        this.materialName = materialName;
    }

    public String getMaterialName() 
    {
        return materialName;
    }
    public void setMaterialModel(String materialModel) 
    {
        this.materialModel = materialModel;
    }

    public String getMaterialModel() 
    {
        return materialModel;
    }
    public void setMaterialSpecification(String materialSpecification) 
    {
        this.materialSpecification = materialSpecification;
    }

    public String getMaterialSpecification() 
    {
        return materialSpecification;
    }
    public void setMaterialUnit(String materialUnit) 
    {
        this.materialUnit = materialUnit;
    }

    public String getMaterialUnit() 
    {
        return materialUnit;
    }
    public void setWhName(String whName) 
    {
        this.whName = whName;
    }

    public String getWhName() 
    {
        return whName;
    }
    public void setWhRegionName(String whRegionName) 
    {
        this.whRegionName = whRegionName;
    }

    public String getWhRegionName() 
    {
        return whRegionName;
    }
    public void setWhLocationName(String whLocationName) 
    {
        this.whLocationName = whLocationName;
    }

    public String getWhLocationName() 
    {
        return whLocationName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("materialId", getMaterialId())
            .append("batchNo", getBatchNo())
            .append("outQuantity", getOutQuantity())
            .append("whId", getWhId())
            .append("whRegionId", getWhRegionId())
            .append("whLocationId", getWhLocationId())
            .append("bizType", getBizType())
            .append("inWhDate", getInWhDate())
            .append("materialName", getMaterialName())
            .append("materialModel", getMaterialModel())
            .append("materialSpecification", getMaterialSpecification())
            .append("materialUnit", getMaterialUnit())
            .append("whName", getWhName())
            .append("whRegionName", getWhRegionName())
            .append("whLocationName", getWhLocationName())
            .toString();
    }
}
