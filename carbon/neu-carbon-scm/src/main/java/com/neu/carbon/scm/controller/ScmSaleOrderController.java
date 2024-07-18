package com.neu.carbon.scm.controller;

import java.util.List;

import com.neu.carbon.scm.domain.ScmSaleCustomer;
import com.neu.carbon.scm.domain.ScmSaleOrderDetail;
import com.neu.carbon.scm.service.IScmSaleCustomerService;
import com.neu.carbon.wms.domain.WmsMaterialInfo;
import com.neu.carbon.wms.service.IWmsMaterialInfoService;
import com.neu.common.utils.SecurityUtils;
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
import com.neu.carbon.scm.domain.ScmSaleOrder;
import com.neu.carbon.scm.service.IScmSaleOrderService;
import com.neu.common.utils.poi.ExcelUtil;
import com.neu.common.core.page.TableDataInfo;

/**
 * 销售订单Controller
 * 
 * @author neuedu
 * @date 2024-06-03
 */
@Api(tags = {"销售订单"})
@RestController
@RequestMapping("/sale/order")
public class ScmSaleOrderController extends BaseController
{
    @Autowired
    private IScmSaleOrderService scmSaleOrderService;
    @Autowired
    private IWmsMaterialInfoService wmsMaterialInfoService;
    @Autowired
    private IScmSaleCustomerService saleCustomerService;


    /**
     * 查询销售订单列表
     */
    @GetMapping("/list")
    @ApiOperation("查询销售订单列表")
    @DynamicResponseParameters(properties = {
	        @DynamicParameter(name = "total", value = "总记录数"),
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "rows", value = "返回业务数据（数组类型）", dataTypeClass = ScmSaleOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    public TableDataInfo list(ScmSaleOrder scmSaleOrder)
    {
        Long userId = this.getUserId();
        if (!SecurityUtils.isAdmin(userId)){
            scmSaleOrder.setApplyUser(String.valueOf(userId));
        }
        startPage();
        List<ScmSaleOrder> list = scmSaleOrderService.selectScmSaleOrderList(scmSaleOrder);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @ApiOperation("导出销售订单列表")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "文件名")
    })
    @PreAuthorize("@ss.hasPermi('sale:order:export')")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ScmSaleOrder scmSaleOrder)
    {
        List<ScmSaleOrder> list = scmSaleOrderService.selectScmSaleOrderList(scmSaleOrder);
        ExcelUtil<ScmSaleOrder> util = new ExcelUtil<ScmSaleOrder>(ScmSaleOrder.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取销售订单详细信息
     */
    @ApiOperation("获取销售订单详细信息")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "data", value = "返回业务数据", dataTypeClass = ScmSaleOrder.class),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        ScmSaleOrder scmSaleOrder = scmSaleOrderService.selectScmSaleOrderById(id);
        List<ScmSaleOrderDetail> orderDetailList = scmSaleOrder.getScmSaleOrderDetailList();
        if (orderDetailList  != null && !orderDetailList.isEmpty()){
            orderDetailList.stream().forEach(datail ->{
                WmsMaterialInfo materialInfo = wmsMaterialInfoService.selectWmsMaterialInfoById(datail.getMaterialId());
                if (materialInfo != null){
                    datail.setMaterialName(materialInfo.getName());
                    datail.setMaterialCode(materialInfo.getCode());
                    datail.setMaterialModel(materialInfo.getModel());
                    datail.setMaterialSpecification(materialInfo.getSpecification());
                    datail.setMaterialUnit(materialInfo.getUnit());
                    datail.setMaterialPrice(materialInfo.getPrice());
                }
            });
        }
        ScmSaleCustomer customer = saleCustomerService.selectScmSaleCustomerById(scmSaleOrder.getCustomerId());
        if(customer != null){
            scmSaleOrder.setCustomerName(customer.getName());
            scmSaleOrder.setContact(customer.getContact());
            scmSaleOrder.setContactTel(customer.getContactTel());
        }
        return AjaxResult.success(scmSaleOrder);
    }

    /**
     * 新增销售订单
     */
    @ApiOperation("新增销售订单")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('sale:order:add')")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ScmSaleOrder scmSaleOrder)
    {
        return toAjax(scmSaleOrderService.insertScmSaleOrder(scmSaleOrder));
    }

    /**
     * 修改销售订单
     */
    @ApiOperation("修改销售订单")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('sale:order:edit')")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ScmSaleOrder scmSaleOrder)
    {
        return toAjax(scmSaleOrderService.updateScmSaleOrder(scmSaleOrder));
    }

    /**
     * 删除销售订单
     */
    @ApiOperation("删除销售订单")
    @DynamicResponseParameters(properties = {
            @DynamicParameter(name = "code", value = "状态码，200正确，其他错误"),
            @DynamicParameter(name = "msg", value = "返回消息内容")
    })
    @PreAuthorize("@ss.hasPermi('sale:order:remove')")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(scmSaleOrderService.deleteScmSaleOrderByIds(ids));
    }
}
