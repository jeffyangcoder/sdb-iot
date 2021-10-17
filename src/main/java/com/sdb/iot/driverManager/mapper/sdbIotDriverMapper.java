package com.sdb.iot.driverManager.mapper;

import java.util.List;
import com.sdb.iot.driverManager.domain.sdbIotDriver;

/**
 * 驱动状态Mapper接口
 * 
 * @author sdb
 * @date 2021-10-17
 */
public interface sdbIotDriverMapper 
{
    /**
     * 查询驱动状态
     * 
     * @param id 驱动状态ID
     * @return 驱动状态
     */
    public sdbIotDriver selectsdbIotDriverById(Long id);

    /**
     * 查询驱动状态列表
     * 
     * @param sdbIotDriver 驱动状态
     * @return 驱动状态集合
     */
    public List<sdbIotDriver> selectsdbIotDriverList(sdbIotDriver sdbIotDriver);

    /**
     * 新增驱动状态
     * 
     * @param sdbIotDriver 驱动状态
     * @return 结果
     */
    public int insertsdbIotDriver(sdbIotDriver sdbIotDriver);

    /**
     * 修改驱动状态
     * 
     * @param sdbIotDriver 驱动状态
     * @return 结果
     */
    public int updatesdbIotDriver(sdbIotDriver sdbIotDriver);

    /**
     * 删除驱动状态
     * 
     * @param id 驱动状态ID
     * @return 结果
     */
    public int deletesdbIotDriverById(Long id);

    /**
     * 批量删除驱动状态
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletesdbIotDriverByIds(Long[] ids);
}
