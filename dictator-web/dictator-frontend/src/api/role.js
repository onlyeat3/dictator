import request from '@/utils/request'

export default {
  listAll(data) {
    return request({
      url: '/role/list'
    });
  },
  saveOrUpdateRole(data) {
    return request({
      url: '/role/saveOrUpdate',
      data
    })
  },
  grantPermission(data) {
    return request({
      url: '/role/grantPermission',
      data
    })
  }
  ,grantProfilePermission(data) {
    return request({
      url: '/role/grantProfilePermission',
      data
    })
  }
  , deleteRole(data) {
    return request({
      url: '/role/delete',
      data
    })
  }
  , loadDetail(data) {
    return request({
      url: '/role/detail',
      data
    });
  },deleteRole(data) {
    return request({
      url: '/role/delete',
      data
    });
  }
}
