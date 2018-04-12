import request from '@/utils/request'

export function listAllGroup() {
  return request({
    url: "/group/list",
  })
}
