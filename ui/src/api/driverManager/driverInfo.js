import request from '@/utils/request'

// 查询驱动配置列表
export function listDriverInfo (query) {
  return request({
    url: '/driverManager/driverInfo/list',
    method: 'get',
    params: query
  })
}

// 查询驱动配置详细
export function getDriverInfo (id) {
  return request({
    url: '/driverManager/driverInfo/' + id,
    method: 'get'
  })
}

// 新增驱动配置
export function addDriverInfo (data) {
  return request({
    url: '/driverManager/driverInfo',
    method: 'post',
    data: data
  })
}

// 修改驱动配置
export function updateDriverInfo (data) {
  return request({
    url: '/driverManager/driverInfo',
    method: 'put',
    data: data
  })
}

// 删除驱动配置
export function delDriverInfo (id) {
  return request({
    url: '/driverManager/driverInfo/' + id,
    method: 'delete'
  })
}

// 导出驱动配置
export function exportDriverInfo (query) {
  return request({
    url: '/driverManager/driverInfo/export',
    method: 'get',
    params: query
  })
}
