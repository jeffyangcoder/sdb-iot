package com.sdb.iot.driverManager.service;

import java.util.List;
import com.sdb.iot.driverManager.domain.sdbIotPointInfo;

/**
 * 位号属性Service接口
 * 
 * @author sdb
 * @date 2021-10-19
 */
public interface IsdbIotPointInfoService 
{
    /**
     * 查询位号属性
     * 
     * @param id 位号属性ID
     * @return 位号属性
     */
    public sdbIotPointInfo selectsdbIotPointInfoById(Long id);

    /**
     * 查询位号属性列表
     * 
     * @param sdbIotPointInfo 位号属性
     * @return 位号属性集合
     */
    public List<sdbIotPointInfo> selectsdbIotPointInfoList(sdbIotPointInfo sdbIotPointInfo);

    /**
     * 新增位号属性
     * 
     * @param sdbIotPointInfo 位号属性
     * @return 结果
     */
    public int insertsdbIotPointInfo(sdbIotPointInfo sdbIotPointInfo);

    /**
     * 修改位号属性
     * 
     * @param sdbIotPointInfo 位号属性
     * @return 结果
     */
    public int updatesdbIotPointInfo(sdbIotPointInfo sdbIotPointInfo);

    /**
     * 批量删除位号属性
     * 
     * @param ids 需要删除的位号属性ID
     * @return 结果
     */
    public int deletesdbIotPointInfoByIds(Long[] ids);

    /**
     * 删除位号属性信息
     * 
     * @param id 位号属性ID
     * @return 结果
     */
    public int deletesdbIotPointInfoById(Long id);
}
