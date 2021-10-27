package com.sdb.iot.dataManager.service.impl;

import com.sdb.iot.dataManager.domain.sdbIotData;
import com.sdb.iot.dataManager.service.sdbIotDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangshiwei
 * @Description
 * @date 2021/10/27-15:50
 */
@Service
public class sdbIotDataServiceImpl {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private sdbIotDataService sdbIotDataService;
    public List<sdbIotData> findDataList(){
        return sdbIotDataService.findAll();
    }
}
