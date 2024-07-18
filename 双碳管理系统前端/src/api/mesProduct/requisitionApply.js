import request from '@/utils/request'

// 查询领料单列表
export function listRequisitionApply(query) {
  return request({
    url: '/mesProduct/requisitionApply/list',
    method: 'get',
    params: query
  })
}

// 查询领料单详细
export function getRequisitionApply(id) {
  return request({
    url: '/mesProduct/requisitionApply/' + id,
    method: 'get'
  })
}

// 新增领料单
export function addRequisitionApply(data) {
  return request({
    url: '/mesProduct/requisitionApply',
    method: 'post',
    data: data
  })
}

// 修改领料单
export function updateRequisitionApply(data) {
  return request({
    url: '/mesProduct/requisitionApply',
    method: 'put',
    data: data
  })
}

// 删除领料单
export function delRequisitionApply(id) {
  return request({
    url: '/mesProduct/requisitionApply/' + id,
    method: 'delete'
  })
}

// 导出领料单
export function exportRequisitionApply(query) {
  return request({
    url: '/mesProduct/requisitionApply/export',
    method: 'get',
    params: query
  })
}