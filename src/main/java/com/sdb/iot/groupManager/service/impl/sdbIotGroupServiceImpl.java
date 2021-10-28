package com.sdb.iot.groupManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.groupManager.mapper.sdbIotGroupMapper;
import com.sdb.iot.groupManager.domain.sdbIotGroup;
import com.sdb.iot.groupManager.service.IsdbIotGroupService;

import javax.annotation.Resource;

/**
 * 分组Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-20
 */
@Service
public class sdbIotGroupServiceImpl implements IsdbIotGroupService 
{
    @Resource
    private sdbIotGroupMapper sdbIotGroupMapper;

    /**
     * 查询分组
     * 
     * @param id 分组ID
     * @return 分组
     */
    @Override
    public sdbIotGroup selectsdbIotGroupById(Long id)
    {
        return sdbIotGroupMapper.selectsdbIotGroupById(id);
    }

    /**
     * 查询分组列表
     * 
     * @param sdbIotGroup 分组
     * @return 分组
     */
    @Override
    public List<sdbIotGroup> selectsdbIotGroupList(sdbIotGroup sdbIotGroup)
    {
        return sdbIotGroupMapper.selectsdbIotGroupList(sdbIotGroup);
    }

    /**
     * 新增分组
     * 
     * @param sdbIotGroup 分组
     * @return 结果
     */
    @Override
    public int insertsdbIotGroup(sdbIotGroup sdbIotGroup)
    {
        sdbIotGroup.setCreateTime(DateUtils.getNowDate());
        return sdbIotGroupMapper.insertsdbIotGroup(sdbIotGroup);
    }

    /**
     * 修改分组
     * 
     * @param sdbIotGroup 分组
     * @return 结果
     */
    @Override
    public int updatesdbIotGroup(sdbIotGroup sdbIotGroup)
    {
        sdbIotGroup.setUpdateTime(DateUtils.getNowDate());
        return sdbIotGroupMapper.updatesdbIotGroup(sdbIotGroup);
    }

    /**
     * 批量删除分组
     * 
     * @param ids 需要删除的分组ID
     * @return 结果
     */
    @Override
    public int deletesdbIotGroupByIds(Long[] ids)
    {
        return sdbIotGroupMapper.deletesdbIotGroupByIds(ids);
    }

    /**
     * 删除分组信息
     * 
     * @param id 分组ID
     * @return 结果
     */
    @Override
    public int deletesdbIotGroupById(Long id)
    {
        return sdbIotGroupMapper.deletesdbIotGroupById(id);
    }
}
