import request from '@/utils/request'

// 查询模板列表
export function listProfile (query) {
  return request({
    url: '/profileManager/profile/list',
    method: 'get',
    params: query
  })
}

// 查询模板详细
export function getProfile (id) {
  return request({
    url: '/profileManager/profile/' + id,
    method: 'get'
  })
}

// 新增模板
export function addProfile (data) {
  return request({
    url: '/profileManager/profile',
    method: 'post',
    data: data
  })
}

// 修改模板
export function updateProfile (data) {
  return request({
    url: '/profileManager/profile',
    method: 'put',
    data: data
  })
}

// 删除模板
export function delProfile (id) {
  return request({
    url: '/profileManager/profile/' + id,
    method: 'delete'
  })
}

// 导出模板
export function exportProfile (query) {
  return request({
    url: '/profileManager/profile/export',
    method: 'get',
    params: query
  })
}
