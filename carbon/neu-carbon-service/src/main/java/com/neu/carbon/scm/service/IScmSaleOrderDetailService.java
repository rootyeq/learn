package com.neu.carbon.scm.service;

import java.util.List;
import com.neu.carbon.scm.domain.ScmSaleOrderDetail;

/**
 * 订单明细Service接口
 * 
 * @author neuedu
 * @date 2024-06-03
 */
public interface IScmSaleOrderDetailService 
{
    /**
     * 查询订单明细
     * 
     * @param id 订单明细ID
     * @return 订单明细
     */
    public ScmSaleOrderDetail selectScmSaleOrderDetailById(Long id);

    /**
     * 查询订单明细列表
     * 
     * @param scmSaleOrderDetail 订单明细
     * @return 订单明细集合
     */
    public List<ScmSaleOrderDetail> selectScmSaleOrderDetailList(ScmSaleOrderDetail scmSaleOrderDetail);

    /**
     * 新增订单明细
     * 
     * @param scmSaleOrderDetail 订单明细
     * @return 结果
     */
    public int insertScmSaleOrderDetail(ScmSaleOrderDetail scmSaleOrderDetail);

    /**
     * 修改订单明细
     * 
     * @param scmSaleOrderDetail 订单明细
     * @return 结果
     */
    public int updateScmSaleOrderDetail(ScmSaleOrderDetail scmSaleOrderDetail);

    /**
     * 批量删除订单明细
     * 
     * @param ids 需要删除的订单明细ID
     * @return 结果
     */
    public int deleteScmSaleOrderDetailByIds(Long[] ids);

    /**
     * 删除订单明细信息
     * 
     * @param id 订单明细ID
     * @return 结果
     */
    public int deleteScmSaleOrderDetailById(Long id);
}
