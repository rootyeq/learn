import request from '@/utils/request'

// 查询设备能耗数据采集报表
export function listEquipmentPowerReport(query) {
  return request({
    url: '/carbonReport/equipmentPowerReport/list',
    method: 'get',
    params: query
  })
}




export function exportEquipmentPowerReport(query) {
  return request({
    url: '/carbonReport/equipmentPowerReport/export',
    method: 'get',
    params: query
  })
}