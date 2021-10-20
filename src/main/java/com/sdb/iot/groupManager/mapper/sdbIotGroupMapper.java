package com.sdb.iot.groupManager.mapper;

import java.util.List;
import com.sdb.iot.groupManager.domain.sdbIotGroup;

/**
 * 分组Mapper接口
 * 
 * @author sdb
 * @date 2021-10-20
 */
public interface sdbIotGroupMapper 
{
    /**
     * 查询分组
     * 
     * @param id 分组ID
     * @return 分组
     */
    public sdbIotGroup selectsdbIotGroupById(Long id);

    /**
     * 查询分组列表
     * 
     * @param sdbIotGroup 分组
     * @return 分组集合
     */
    public List<sdbIotGroup> selectsdbIotGroupList(sdbIotGroup sdbIotGroup);

    /**
     * 新增分组
     * 
     * @param sdbIotGroup 分组
     * @return 结果
     */
    public int insertsdbIotGroup(sdbIotGroup sdbIotGroup);

    /**
     * 修改分组
     * 
     * @param sdbIotGroup 分组
     * @return 结果
     */
    public int updatesdbIotGroup(sdbIotGroup sdbIotGroup);

    /**
     * 删除分组
     * 
     * @param id 分组ID
     * @return 结果
     */
    public int deletesdbIotGroupById(Long id);

    /**
     * 批量删除分组
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletesdbIotGroupByIds(Long[] ids);
}
