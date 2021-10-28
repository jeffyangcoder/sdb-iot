package com.sdb.iot.dataManager.service.impl;

import com.sdb.iot.dataManager.domain.SdbIotData;
import com.sdb.iot.dataManager.repository.SdbIotDataRepository;
import com.sdb.iot.dataManager.service.SdbIotDataService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 数据Service业务层处理
 *
 * @author sdb
 * @date 2021-10-20
 */

public class SdbIotDataServiceImpl implements SdbIotDataService {
    @Resource
    private SdbIotDataRepository sdbIotDataRepository;

    @Override
    public List<SdbIotData> selectSdbIotDataByDeviceId(Long deviceId) {
        return null;
    }

    @Override
    public List<SdbIotData> selectSdbIotDataByGroupId(Long groupId) {
        return null;
    }

    @Override
    public SdbIotData selectSdbIotDataById(Long id) {
        return null;
    }
}
