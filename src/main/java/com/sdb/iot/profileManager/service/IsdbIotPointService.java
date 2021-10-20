package com.sdb.iot.profileManager.service;

import java.util.List;
import com.sdb.iot.profileManager.domain.sdbIotPoint;

/**
 * 位号Service接口
 * 
 * @author sdb
 * @date 2021-10-20
 */
public interface IsdbIotPointService 
{
    /**
     * 查询位号
     * 
     * @param id 位号ID
     * @return 位号
     */
    public sdbIotPoint selectsdbIotPointById(Long id);

    /**
     * 查询位号列表
     * 
     * @param sdbIotPoint 位号
     * @return 位号集合
     */
    public List<sdbIotPoint> selectsdbIotPointList(sdbIotPoint sdbIotPoint);

    /**
     * 新增位号
     * 
     * @param sdbIotPoint 位号
     * @return 结果
     */
    public int insertsdbIotPoint(sdbIotPoint sdbIotPoint);

    /**
     * 修改位号
     * 
     * @param sdbIotPoint 位号
     * @return 结果
     */
    public int updatesdbIotPoint(sdbIotPoint sdbIotPoint);

    /**
     * 批量删除位号
     * 
     * @param ids 需要删除的位号ID
     * @return 结果
     */
    public int deletesdbIotPointByIds(Long[] ids);

    /**
     * 删除位号信息
     * 
     * @param id 位号ID
     * @return 结果
     */
    public int deletesdbIotPointById(Long id);
}
