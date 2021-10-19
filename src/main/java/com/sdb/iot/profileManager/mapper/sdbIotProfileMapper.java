package com.sdb.iot.profileManager.mapper;

import java.util.List;
import com.sdb.iot.profileManager.domain.sdbIotProfile;

/**
 * 模板Mapper接口
 * 
 * @author sdb
 * @date 2021-10-19
 */
public interface sdbIotProfileMapper 
{
    /**
     * 查询模板
     * 
     * @param id 模板ID
     * @return 模板
     */
    public sdbIotProfile selectsdbIotProfileById(Long id);

    /**
     * 查询模板列表
     * 
     * @param sdbIotProfile 模板
     * @return 模板集合
     */
    public List<sdbIotProfile> selectsdbIotProfileList(sdbIotProfile sdbIotProfile);

    /**
     * 新增模板
     * 
     * @param sdbIotProfile 模板
     * @return 结果
     */
    public int insertsdbIotProfile(sdbIotProfile sdbIotProfile);

    /**
     * 修改模板
     * 
     * @param sdbIotProfile 模板
     * @return 结果
     */
    public int updatesdbIotProfile(sdbIotProfile sdbIotProfile);

    /**
     * 删除模板
     * 
     * @param id 模板ID
     * @return 结果
     */
    public int deletesdbIotProfileById(Long id);

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletesdbIotProfileByIds(Long[] ids);
}
