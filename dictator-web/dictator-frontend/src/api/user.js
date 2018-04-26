import request from '@/utils/request';

export default {
    listAll(){
        return request({
            url:"/user/listAll"
        });
    },
    saveOrUpdateUser(data){
        return request({
            url:"/user/saveOrUpdate",
            data
        });
    },
    deleteUser(data){
        return {
            url: "/user/delete",
            data
        };
    }
};