import request from '@/utils/request'

// 查询位号配置列表
export function listDevicePoint (query) {
  return request({
    url: '/deviceManager/devicePoint/list',
    method: 'get',
    params: query
  })
}

// 查询位号配置详细
export function getDevicePoint (id) {
  return request({
    url: '/deviceManager/devicePoint/' + id,
    method: 'get'
  })
}

// 新增位号配置
export function addDevicePoint (data) {
  return request({
    url: '/deviceManager/devicePoint',
    method: 'post',
    data: data
  })
}

// 修改位号配置
export function updateDevicePoint (data) {
  return request({
    url: '/deviceManager/devicePoint',
    method: 'put',
    data: data
  })
}

// 删除位号配置
export function delDevicePoint (id) {
  return request({
    url: '/deviceManager/devicePoint/' + id,
    method: 'delete'
  })
}

// 导出位号配置
export function exportDevicePoint (query) {
  return request({
    url: '/deviceManager/devicePoint/export',
    method: 'get',
    params: query
  })
}
