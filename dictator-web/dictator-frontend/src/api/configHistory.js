import request from '@/utils/request'

export function listConfigHistory(listQuery) {
  return request({
    url: "/configHistory/list",
    data: listQuery
  })
}
export function recovery(historyId) {
  return request({
    url: "/configHistory/recovery",
    data: {
      id: historyId
    }
  })
}
