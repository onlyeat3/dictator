import request from '@/utils/request'

export default{
    listAll(data) {
        return request({
          url: "/app/listAll",
          data
        })
    },
    saveOrUpdateApp(data) {
        return request({
          url: "/app/saveOrUpdate",
          data
        })
    }
}
