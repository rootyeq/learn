package com.neu.carbon.mes.mapper;

import java.util.List;
import com.neu.carbon.mes.domain.MesProductFinish;

/**
 * 生产完工单Mapper接口
 *
 * @author neuedu
 * @date 2022-07-17
 */
public interface MesProductFinishMapper
{
    /**
     * 查询生产完工单
     *
     * @param id 生产完工单ID
     * @return 生产完工单
     */
    public MesProductFinish selectMesProductFinishById(Long id);

    /**
     * 查询生产完工单列表
     *
     * @param mesProductFinish 生产完工单
     * @return 生产完工单集合
     */
    public List<MesProductFinish> selectMesProductFinishList(MesProductFinish mesProductFinish);

    /**
     * 新增生产完工单
     *
     * @param mesProductFinish 生产完工单
     * @return 结果
     */
    public int insertMesProductFinish(MesProductFinish mesProductFinish);

    /**
     * 修改生产完工单
     *
     * @param mesProductFinish 生产完工单
     * @return 结果
     */
    public int updateMesProductFinish(MesProductFinish mesProductFinish);

    /**
     * 删除生产完工单
     *
     * @param id 生产完工单ID
     * @return 结果
     */
    public int deleteMesProductFinishById(Long id);

    /**
     * 批量删除生产完工单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMesProductFinishByIds(Long[] ids);
}
