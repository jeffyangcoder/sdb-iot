import request from '@/utils/request'

// 查询位号属性列表
export function listPointInfo (query) {
  return request({
    url: '/driverManager/pointInfo/list',
    method: 'get',
    params: query
  })
}

// 查询位号属性详细
export function getPointInfo (id) {
  return request({
    url: '/driverManager/pointInfo/' + id,
    method: 'get'
  })
}

// 新增位号属性
export function addPointInfo (data) {
  return request({
    url: '/driverManager/pointInfo',
    method: 'post',
    data: data
  })
}

// 修改位号属性
export function updatePointInfo (data) {
  return request({
    url: '/driverManager/pointInfo',
    method: 'put',
    data: data
  })
}

// 删除位号属性
export function delPointInfo (id) {
  return request({
    url: '/driverManager/pointInfo/' + id,
    method: 'delete'
  })
}

// 导出位号属性
export function exportPointInfo (query) {
  return request({
    url: '/driverManager/pointInfo/export',
    method: 'get',
    params: query
  })
}
