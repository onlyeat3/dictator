import request from '@/utils/request'

export function listConfig(data) {
  return request({
    url: "/config/list",
    data
  })
}

export function saveOrUpdateConfig(data) {
  return request({
    url: "/config/saveOrUpdate",
    data
  })
}

export function batchAddConfig(data) {
  return request({
    url: "/config/batchAdd",
    data
  })
}
