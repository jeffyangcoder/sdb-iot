import request from '@/utils/request'

// 查询驱动状态列表
export function listDriver (query) {
  return request({
    url: '/driverManager/driver/list',
    method: 'get',
    params: query
  })
}

// 查询驱动状态详细
export function getDriver (id) {
  return request({
    url: '/driverManager/driver/' + id,
    method: 'get'
  })
}

// 新增驱动状态
export function addDriver (data) {
  return request({
    url: '/driverManager/driver',
    method: 'post',
    data: data
  })
}

// 修改驱动状态
export function updateDriver (data) {
  return request({
    url: '/driverManager/driver',
    method: 'put',
    data: data
  })
}

// 删除驱动状态
export function delDriver (id) {
  return request({
    url: '/driverManager/driver/' + id,
    method: 'delete'
  })
}

// 导出驱动状态
export function exportDriver (query) {
  return request({
    url: '/driverManager/driver/export',
    method: 'get',
    params: query
  })
}
