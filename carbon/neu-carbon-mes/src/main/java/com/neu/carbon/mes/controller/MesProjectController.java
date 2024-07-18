package com.neu.carbon.mes.controller;

import java.util.List;
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
import com.neu.carbon.mes.domain.MesProject;
import com.neu.carbon.mes.service.IMesProjectService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 项目维护Controller
 * 
 * @author neuedu
 * @date 2024-04-10
 */
@Api(tags = {"项目维护"})
@RestController
@RequestMapping("/baseInfo/projectInfo")
public class MesProjectController extends BaseController
{
    @Autowired
    private IMesProjectService mesProjectService;

    /**
     * 查询项目维护列表
     */
    @GetMapping("/list")
    @ApiOperation("查询项目维护列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = MesProject.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(MesProject mesProject)
    {
        startPage();
        List<MesProject> list = mesProjectService.selectMesProjectList(mesProject);
        return getDataTable(list);
    }

    /**
     * 导出项目维护列表
     */
    @ApiOperation("导出项目维护列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:projectInfo:export')")
    @Log(title = "项目维护", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MesProject mesProject)
    {
        List<MesProject> list = mesProjectService.selectMesProjectList(mesProject);
        ExcelUtil<MesProject> util = new ExcelUtil<MesProject>(MesProject.class);
        return util.exportExcel(list, "projectInfo");
    }

    /**
     * 获取项目维护详细信息
     */
    @ApiOperation("获取项目维护详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = MesProject.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(mesProjectService.selectMesProjectById(id));
    }

    /**
     * 新增项目维护
     */
    @ApiOperation("新增项目维护")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:projectInfo:add')")
    @Log(title = "项目维护", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MesProject mesProject)
    {
        return toAjax(mesProjectService.insertMesProject(mesProject));
    }

    /**
     * 修改项目维护
     */
    @ApiOperation("修改项目维护")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:projectInfo:edit')")
    @Log(title = "项目维护", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MesProject mesProject)
    {
        return toAjax(mesProjectService.updateMesProject(mesProject));
    }

    /**
     * 删除项目维护
     */
    @ApiOperation("删除项目维护")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('baseInfo:projectInfo:remove')")
    @Log(title = "项目维护", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(mesProjectService.deleteMesProjectByIds(ids));
    }
}
