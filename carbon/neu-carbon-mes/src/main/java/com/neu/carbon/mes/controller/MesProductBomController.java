package com.neu.carbon.mes.controller;

import java.util.List;

import com.neu.carbon.mes.domain.MesProductBomDetail;
import com.neu.carbon.wms.domain.WmsMaterialInfo;
import com.neu.carbon.wms.service.IWmsMaterialInfoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicResponseParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.neu.common.annotation.Log;
import com.neu.common.core.controller.BaseController;
import com.neu.common.core.domain.AjaxResult;
import com.neu.common.enums.BusinessType;
import com.neu.carbon.mes.domain.MesProductBom;
import com.neu.carbon.mes.service.IMesProductBomService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 产品BOM单Controller
 * 
 * @author neuedu
 * @date 2024-05-15
 */
@Api(tags = {"产品BOM单"})
@RestController
@RequestMapping("/mesPlan/mesBom")
public class MesProductBomController extends BaseController
{
    @Autowired
    private IMesProductBomService mesProductBomService;
    @Autowired
    private IWmsMaterialInfoService materialInfoService;

    /**
     * 查询产品BOM单列表
     */
    @GetMapping("/list")
    @ApiOperation("查询产品BOM单列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = MesProductBom.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(MesProductBom mesProductBom)
    {
        startPage();
        List<MesProductBom> list = mesProductBomService.selectMesProductBomList(mesProductBom);
        list.stream().forEach(model ->{
            WmsMaterialInfo materialInfo = materialInfoService.selectWmsMaterialInfoById(model.getProductId());
           if(materialInfo != null){
               model.setProductCode(materialInfo.getCode());
               model.setProductSpecification(materialInfo.getSpecification());
               model.setProductName(materialInfo.getName());
               model.setProductModel(materialInfo.getModel());
               model.setProductUnit(materialInfo.getUnit());
           }
        });
        return getDataTable(list);
    }

    /**
     * 导出产品BOM单列表
     */
    @ApiOperation("导出产品BOM单列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('mesPlan:mesBom:export')")
    @Log(title = "产品BOM单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MesProductBom mesProductBom)
    {
        List<MesProductBom> list = mesProductBomService.selectMesProductBomList(mesProductBom);
        list.stream().forEach(model ->{
                    WmsMaterialInfo materialInfo = materialInfoService.selectWmsMaterialInfoById(model.getProductId());
                    if(materialInfo != null){
                        model.setProductCode(materialInfo.getCode());
                        model.setProductSpecification(materialInfo.getSpecification());
                        model.setProductName(materialInfo.getName());
                        model.setProductModel(materialInfo.getModel());
                        model.setProductUnit(materialInfo.getUnit());

        }
        });
        ExcelUtil<MesProductBom> util = new ExcelUtil<MesProductBom>(MesProductBom.class);
        return util.exportExcel(list, "mesBom");
    }

    /**
     * 获取产品BOM单详细信息
     */
    @ApiOperation("获取产品BOM单详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = MesProductBom.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        MesProductBom bom = mesProductBomService.selectMesProductBomById(id);
        WmsMaterialInfo materialInfo = materialInfoService.selectWmsMaterialInfoById(bom.getProductId());
        if(materialInfo != null){
            bom.setProductCode(materialInfo.getCode());
            bom.setProductSpecification(materialInfo.getSpecification());
            bom.setProductName(materialInfo.getName());
            bom.setProductModel(materialInfo.getModel());
            bom.setProductUnit(materialInfo.getUnit());
        }
        List<MesProductBomDetail> detailList = bom.getMesProductBomDetailList();
        detailList.stream().forEach(model ->{
            WmsMaterialInfo material = materialInfoService.selectWmsMaterialInfoById(model.getMaterialId());
            if(material != null){
                model.setMaterialCode(material.getCode());
                model.setMaterialModel(material.getModel());
                model.setMaterialName(material.getName());
                model.setMaterialUnit(material.getUnit());
                model.setMaterialSpecification(material.getSpecification());
            }
        });

        return AjaxResult.success(bom);
    }

    /**
     * 新增产品BOM单
     */
    @ApiOperation("新增产品BOM单")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesPlan:mesBom:add')")
    @Log(title = "产品BOM单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesProductBom mesProductBom)
    {
        return toAjax(mesProductBomService.insertMesProductBom(mesProductBom));
    }

    /**
     * 修改产品BOM单
     */
    @ApiOperation("修改产品BOM单")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesPlan:mesBom:edit')")
    @Log(title = "产品BOM单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesProductBom mesProductBom)
    {
        return toAjax(mesProductBomService.updateMesProductBom(mesProductBom));
    }

    /**
     * 删除产品BOM单
     */
    @ApiOperation("删除产品BOM单")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('mesPlan:mesBom:remove')")
    @Log(title = "产品BOM单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mesProductBomService.deleteMesProductBomByIds(ids));
    }
}
