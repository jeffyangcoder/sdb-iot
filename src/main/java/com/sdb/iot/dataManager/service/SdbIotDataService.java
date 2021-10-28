package com.sdb.iot.dataManager.service;

import com.sdb.iot.dataManager.domain.SdbIotData;

import java.util.List;

/**
 * 数据Service接口
 *
 * @author yanshiwei
 * @date 2021-10-20
 */
public interface SdbIotDataService {
    /**
     * TODO:查询数据
     *
     * @param deviceId 设备id
     * @return 数据集合
     */
    List<SdbIotData> selectSdbIotDataByDeviceId(Long deviceId);

    /**
     * TODO:查询数据
     *
     * @param groupId 分组ID
     * @return 数据集合
     */
    List<SdbIotData> selectSdbIotDataByGroupId(Long groupId);
    /**
     * 查询数据
     *
     * @param id 数据Id
     * @return 数据
     */
    SdbIotData selectSdbIotDataById(Long id);

}
