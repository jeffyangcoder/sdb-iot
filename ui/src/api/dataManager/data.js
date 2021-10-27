import request from '@/utils/request'

// 查询数据列表
export function listData (query) {
  return request({
    url: '/dataManager/data/list',
    method: 'get',
    params: query
  })
}

// 查询数据详细状态
export function getData (id) {
  return request({
    url: '/dataManager/data' + id,
    method: 'get'
  })
}

// 新增数据
export function addData (data) {
  return request({
    url: '/dataManager/data',
    method: 'post',
    data: data
  })
}

// 修改数据
export function updateData (data) {
  return request({
    url: '/dataManager/data',
    method: 'put',
    data: data
  })
}

// 删除数据
export function delData (id) {
  return request({
    url: 'dataManager/data' + id,
    method: 'delete'
  })
}

// 导出数据
export function exportData (query) {
  return request({
    url: '/dataManager/data/export',
    method: 'get',
    params: query
  })
}
