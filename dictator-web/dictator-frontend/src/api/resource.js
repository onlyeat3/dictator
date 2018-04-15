import request from '@/utils/request'

export function listAll(data){
  return request({
    url:"/resource/list",
    data
  });
}

export function saveOrUpdateResource(data){
  return request({
    url: "/resource/saveOrUpdate",
    data
  });
}
export function deleteResource(data){
  return request({
    url: "/resource/delete",
    data
  });
}
