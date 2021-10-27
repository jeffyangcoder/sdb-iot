package com.sdb.iot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class } )
@ComponentScan({"com.sdb.*"})
public class SdbIotApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(SdbIotApplication.class, args);
        System.out.println("========= 系统启动成功========");
    }
}
