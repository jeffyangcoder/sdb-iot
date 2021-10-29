package com.sdb.iot.dataManager.service;

import com.sdb.iot.dataManager.domain.SdbIotData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 数据Service接口
 *
 * @author yanshiwei
 * @date 2021-10-20
 */
public interface SdbIotDataService {
    /**
     * 查询数据
     *
     * @param deviceId 设备id
     * @return 数据集合
     */
    List<SdbIotData> findSdbIotDataByDeviceId(Long deviceId);

    /**
     * 查询数据
     *
     * @param groupId 分组ID
     * @return 数据集合
     */
    List<SdbIotData> findSdbIotDataByGroupId(Long groupId);

    /**
     * 查询数据
     *
     * @param id 数据Id
     * @return 数据
     */
    SdbIotData findSdbIotDataById(Long id);

    /**
     * 查询所有
     *
     * @param
     * @return 数据集合
     */
    List<SdbIotData> findSdbIotDataList();

}
