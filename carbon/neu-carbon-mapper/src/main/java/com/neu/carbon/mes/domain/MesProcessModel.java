package com.neu.carbon.mes.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.neu.common.annotation.Excel;
import com.neu.common.core.domain.BaseEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 工艺建模对象 mes_process_model
 * 
 * @author neuedu
 * @date 2024-04-23
 */
@ApiModel("工艺建模")
public class MesProcessModel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    @ApiModelProperty("编号")
    @Excel(name = "编号")
    private Long id;

    /** 产品 */
    @ApiModelProperty("产品")
    @Excel(name = "产品")
    private Long materialId;

    /** 产品 */
    @ApiModelProperty("产品")
    @Excel(name = "产品")
    private String materialName;


    /** 工艺名称 */
    @ApiModelProperty("工艺名称")
    @Excel(name = "工艺名称")
    private String processName;

    /** 工艺描述 */
    @ApiModelProperty("工艺描述")
    private String description;

    /** 单位耗电量 */
    @ApiModelProperty("单位耗电量")
    @Excel(name = "单位耗电量")
    private Long powerConsume;

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
    public void setProcessName(String processName) 
    {
        this.processName = processName;
    }

    public String getProcessName() 
    {
        return processName;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setPowerConsume(Long powerConsume) 
    {
        this.powerConsume = powerConsume;
    }

    public Long getPowerConsume() 
    {
        return powerConsume;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("materialId", getMaterialId())
            .append("processName", getProcessName())
            .append("description", getDescription())
            .append("powerConsume", getPowerConsume())
            .append("remark", getRemark())
            .toString();
    }
}
