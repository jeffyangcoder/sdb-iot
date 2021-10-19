package com.sdb.iot.profileManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.profileManager.mapper.sdbIotProfileMapper;
import com.sdb.iot.profileManager.domain.sdbIotProfile;
import com.sdb.iot.profileManager.service.IsdbIotProfileService;

/**
 * 模板Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-19
 */
@Service
public class sdbIotProfileServiceImpl implements IsdbIotProfileService 
{
    @Autowired
    private sdbIotProfileMapper sdbIotProfileMapper;

    /**
     * 查询模板
     * 
     * @param id 模板ID
     * @return 模板
     */
    @Override
    public sdbIotProfile selectsdbIotProfileById(Long id)
    {
        return sdbIotProfileMapper.selectsdbIotProfileById(id);
    }

    /**
     * 查询模板列表
     * 
     * @param sdbIotProfile 模板
     * @return 模板
     */
    @Override
    public List<sdbIotProfile> selectsdbIotProfileList(sdbIotProfile sdbIotProfile)
    {
        return sdbIotProfileMapper.selectsdbIotProfileList(sdbIotProfile);
    }

    /**
     * 新增模板
     * 
     * @param sdbIotProfile 模板
     * @return 结果
     */
    @Override
    public int insertsdbIotProfile(sdbIotProfile sdbIotProfile)
    {
        sdbIotProfile.setCreateTime(DateUtils.getNowDate());
        return sdbIotProfileMapper.insertsdbIotProfile(sdbIotProfile);
    }

    /**
     * 修改模板
     * 
     * @param sdbIotProfile 模板
     * @return 结果
     */
    @Override
    public int updatesdbIotProfile(sdbIotProfile sdbIotProfile)
    {
        return sdbIotProfileMapper.updatesdbIotProfile(sdbIotProfile);
    }

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板ID
     * @return 结果
     */
    @Override
    public int deletesdbIotProfileByIds(Long[] ids)
    {
        return sdbIotProfileMapper.deletesdbIotProfileByIds(ids);
    }

    /**
     * 删除模板信息
     * 
     * @param id 模板ID
     * @return 结果
     */
    @Override
    public int deletesdbIotProfileById(Long id)
    {
        return sdbIotProfileMapper.deletesdbIotProfileById(id);
    }
}
