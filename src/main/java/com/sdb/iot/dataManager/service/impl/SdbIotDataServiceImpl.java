package com.sdb.iot.dataManager.service.impl;

import com.sdb.iot.dataManager.domain.SdbIotData;
import com.sdb.iot.dataManager.repository.SdbIotDataRepository;
import com.sdb.iot.dataManager.service.SdbIotDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据Service业务层处理
 *
 * @author sdb
 * @date 2021-10-20
 */
@Service
public class SdbIotDataServiceImpl implements SdbIotDataService {
    @Resource
    private SdbIotDataRepository sdbIotDataRepository;

    @Override
    public List<SdbIotData> findSdbIotDataByDeviceId(Long deviceId) {
        return sdbIotDataRepository.findByDeviceId(deviceId);
    }

    @Override
    public List<SdbIotData> findSdbIotDataByGroupId(Long groupId) {
        return sdbIotDataRepository.findByGroupId(groupId);
    }

    @Override
    public SdbIotData findSdbIotDataById(Long id) {
        return sdbIotDataRepository.findById(id).get();
    }

    @Override
    public List<SdbIotData> findSdbIotDataList() {
        return sdbIotDataRepository.findAll();
    }
}
