import request from '@/utils/request'

// 查询驱动属性信息列表
export function listDriverAttribute (query) {
  return request({
    url: '/driverManager/driverAttribute/list',
    method: 'get',
    params: query
  })
}

// 查询驱动属性信息详细
export function getDriverAttribute (id) {
  return request({
    url: '/driverManager/driverAttribute/' + id,
    method: 'get'
  })
}

// 新增驱动属性信息
export function addDriverAttribute (data) {
  return request({
    url: '/driverManager/driverAttribute',
    method: 'post',
    data: data
  })
}

// 修改驱动属性信息
export function updateDriverAttribute (data) {
  return request({
    url: '/driverManager/driverAttribute',
    method: 'put',
    data: data
  })
}

// 删除驱动属性信息
export function delDriverAttribute (id) {
  return request({
    url: '/driverManager/driverAttribute/' + id,
    method: 'delete'
  })
}

// 导出驱动属性信息
export function exportDriverAttribute (query) {
  return request({
    url: '/driverManager/driverAttribute/export',
    method: 'get',
    params: query
  })
}
