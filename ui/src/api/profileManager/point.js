import request from '@/utils/request'

// 查询位号列表
export function listPoint (query) {
  return request({
    url: '/profileManager/point/list',
    method: 'get',
    params: query
  })
}

// 查询位号详细
export function getPoint (id) {
  return request({
    url: '/profileManager/point/' + id,
    method: 'get'
  })
}

// 新增位号
export function addPoint (data) {
  return request({
    url: '/profileManager/point',
    method: 'post',
    data: data
  })
}

// 修改位号
export function updatePoint (data) {
  return request({
    url: '/profileManager/point',
    method: 'put',
    data: data
  })
}

// 删除位号
export function delPoint (id) {
  return request({
    url: '/profileManager/point/' + id,
    method: 'delete'
  })
}

// 导出位号
export function exportPoint (query) {
  return request({
    url: '/profileManager/point/export',
    method: 'get',
    params: query
  })
}
