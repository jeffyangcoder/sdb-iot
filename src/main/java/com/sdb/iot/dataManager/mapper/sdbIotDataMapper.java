package com.sdb.iot.dataManager.mapper;

import com.sdb.iot.dataManager.domain.sdbIotData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * 数据Mapper接口
 *
 * @author sdb
 * @date 2021-10-17
 */
@Mapper
public interface sdbIotDataMapper extends MongoRepository<sdbIotData,Long> {

}
