package com.neu.carbon.mes.service.impl;

import java.util.List;
import com.neu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.neu.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.neu.carbon.mes.domain.MesProductBomDetail;
import com.neu.carbon.mes.mapper.MesProductBomMapper;
import com.neu.carbon.mes.domain.MesProductBom;
import com.neu.carbon.mes.service.IMesProductBomService;

/**
 * 产品BOM单Service业务层处理
 * 
 * @author neuedu
 * @date 2024-05-15
 */
@Service
public class MesProductBomServiceImpl implements IMesProductBomService 
{
    @Autowired
    private MesProductBomMapper mesProductBomMapper;

    /**
     * 查询产品BOM单
     * 
     * @param id 产品BOM单ID
     * @return 产品BOM单
     */
    @Override
    public MesProductBom selectMesProductBomById(Long id)
    {
        return mesProductBomMapper.selectMesProductBomById(id);
    }

    /**
     * 查询产品BOM单列表
     * 
     * @param mesProductBom 产品BOM单
     * @return 产品BOM单
     */
    @Override
    public List<MesProductBom> selectMesProductBomList(MesProductBom mesProductBom)
    {
        return mesProductBomMapper.selectMesProductBomList(mesProductBom);
    }

    /**
     * 新增产品BOM单
     * 
     * @param mesProductBom 产品BOM单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMesProductBom(MesProductBom mesProductBom)
    {
        mesProductBom.setCreateTime(DateUtils.getNowDate());
        int rows = mesProductBomMapper.insertMesProductBom(mesProductBom);
        insertMesProductBomDetail(mesProductBom);
        return rows;
    }

    /**
     * 修改产品BOM单
     * 
     * @param mesProductBom 产品BOM单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateMesProductBom(MesProductBom mesProductBom)
    {
        mesProductBom.setUpdateTime(DateUtils.getNowDate());
        mesProductBomMapper.deleteMesProductBomDetailByBomId(mesProductBom.getId());
        insertMesProductBomDetail(mesProductBom);
        return mesProductBomMapper.updateMesProductBom(mesProductBom);
    }

    /**
     * 批量删除产品BOM单
     * 
     * @param ids 需要删除的产品BOM单ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMesProductBomByIds(Long[] ids)
    {
        mesProductBomMapper.deleteMesProductBomDetailByBomIds(ids);
        return mesProductBomMapper.deleteMesProductBomByIds(ids);
    }

    /**
     * 删除产品BOM单信息
     * 
     * @param id 产品BOM单ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteMesProductBomById(Long id)
    {
        mesProductBomMapper.deleteMesProductBomDetailByBomId(id);
        return mesProductBomMapper.deleteMesProductBomById(id);
    }

    /**
     * 新增BOM单明细信息
     * 
     * @param mesProductBom 产品BOM单对象
     */
    public void insertMesProductBomDetail(MesProductBom mesProductBom)
    {
        List<MesProductBomDetail> mesProductBomDetailList = mesProductBom.getMesProductBomDetailList();
        Long id = mesProductBom.getId();
        if (StringUtils.isNotNull(mesProductBomDetailList))
        {
            List<MesProductBomDetail> list = new ArrayList<MesProductBomDetail>();
            for (MesProductBomDetail mesProductBomDetail : mesProductBomDetailList)
            {
                mesProductBomDetail.setBomId(id);
                list.add(mesProductBomDetail);
            }
            if (list.size() > 0)
            {
                mesProductBomMapper.batchMesProductBomDetail(list);
            }
        }
    }
}
