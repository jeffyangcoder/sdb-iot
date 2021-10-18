# SDB-IOT 物联网项目说明
## 环境说明
* java:1.8
* maven:3.6.x
* mysql:8.0.x
* redis: 3.2.100
* node: 14.x

## 结构说明
后端代码统一放在src目录下

前端代码放在ui目录下

## 启动说明
### 后端启动
* 启动navicat等数据库管理软件，将sdb-base.sql文件导入mysql数据库中
* 使用IDEA等代码软件，进入src目录下使用mvn指令安装所需要的外部库
* 配置resource文件夹下的application-druid.yml文件，需要配置mysql的url、password，还有redis的密码，默认redis是没有密码的
* 启动com.sdb.iot下的SdbErpApplication后端启动完成

### 前端启动
```bash
#进入前端文件夹
cd ui

#安装所需要的依赖
npm install

#启动前端服务
npm run serve
```
默认端口是8080