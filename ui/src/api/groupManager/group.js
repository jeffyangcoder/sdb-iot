import request from '@/utils/request'

// 查询分组列表
export function listGroup (query) {
  return request({
    url: '/groupManager/group/list',
    method: 'get',
    params: query
  })
}

// 查询分组详细
export function getGroup (id) {
  return request({
    url: '/groupManager/group/' + id,
    method: 'get'
  })
}

// 新增分组
export function addGroup (data) {
  return request({
    url: '/groupManager/group',
    method: 'post',
    data: data
  })
}

// 修改分组
export function updateGroup (data) {
  return request({
    url: '/groupManager/group',
    method: 'put',
    data: data
  })
}

// 删除分组
export function delGroup (id) {
  return request({
    url: '/groupManager/group/' + id,
    method: 'delete'
  })
}

// 导出分组
export function exportGroup (query) {
  return request({
    url: '/groupManager/group/export',
    method: 'get',
    params: query
  })
}
