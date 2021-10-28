package com.sdb.iot.dataManager.repository;

import com.sdb.iot.dataManager.domain.SdbIotData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SdbIotDataRepository extends MongoRepository<SdbIotData,Long> {
}
