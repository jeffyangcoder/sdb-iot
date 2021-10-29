package com.sdb.iot.dataManager.repository;

import com.sdb.iot.dataManager.domain.SdbIotData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SdbIotDataRepository extends MongoRepository<SdbIotData, Long> {
    /**
     * 查询数据
     *
     * @param deviceId 设备id
     * @return 数据集合
     */
    List<SdbIotData> findByDeviceId(Long deviceId);

    /**
     * 查询数据
     *
     * @param groupId 分组ID
     * @return 数据集合
     */
    List<SdbIotData> findByGroupId(Long groupId);
}
