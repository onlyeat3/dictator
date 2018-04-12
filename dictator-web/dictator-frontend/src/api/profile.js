import request from '@/utils/request'

export function listAllProfile() {
  return request({
    url: "/profile/list",
  })
}

export function updateProfile(data) {
  return request({
    url: "/profile/saveOrUpdate",
    data
  });
}

export function deleteProfile(profileId) {
  return request({
    url: "/profile/delete",
    data: {
      id: profileId
    }
  })
}
