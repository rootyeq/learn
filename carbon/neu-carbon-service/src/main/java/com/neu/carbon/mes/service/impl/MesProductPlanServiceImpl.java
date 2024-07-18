package com.neu.carbon.mes.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neu.carbon.mes.mapper.MesProductPlanMapper;
import com.neu.carbon.mes.domain.MesProductPlan;
import com.neu.carbon.mes.service.IMesProductPlanService;

/**
 * 生产计划Service业务层处理
 * 
 * @author neuedu
 * @date 2024-05-26
 */
@Service
public class MesProductPlanServiceImpl implements IMesProductPlanService 
{
    @Autowired
    private MesProductPlanMapper mesProductPlanMapper;

    /**
     * 查询生产计划
     * 
     * @param id 生产计划ID
     * @return 生产计划
     */
    @Override
    public MesProductPlan selectMesProductPlanById(Long id)
    {
        return mesProductPlanMapper.selectMesProductPlanById(id);
    }

    /**
     * 查询生产计划列表
     * 
     * @param mesProductPlan 生产计划
     * @return 生产计划
     */
    @Override
    public List<MesProductPlan> selectMesProductPlanList(MesProductPlan mesProductPlan)
    {
        return mesProductPlanMapper.selectMesProductPlanList(mesProductPlan);
    }

    /**
     * 新增生产计划
     * 
     * @param mesProductPlan 生产计划
     * @return 结果
     */
    @Override
    public int insertMesProductPlan(MesProductPlan mesProductPlan)
    {
        return mesProductPlanMapper.insertMesProductPlan(mesProductPlan);
    }

    /**
     * 修改生产计划
     * 
     * @param mesProductPlan 生产计划
     * @return 结果
     */
    @Override
    public int updateMesProductPlan(MesProductPlan mesProductPlan)
    {
        return mesProductPlanMapper.updateMesProductPlan(mesProductPlan);
    }

    /**
     * 批量删除生产计划
     * 
     * @param ids 需要删除的生产计划ID
     * @return 结果
     */
    @Override
    public int deleteMesProductPlanByIds(Long[] ids)
    {
        return mesProductPlanMapper.deleteMesProductPlanByIds(ids);
    }

    /**
     * 删除生产计划信息
     * 
     * @param id 生产计划ID
     * @return 结果
     */
    @Override
    public int deleteMesProductPlanById(Long id)
    {
        return mesProductPlanMapper.deleteMesProductPlanById(id);
    }
}
