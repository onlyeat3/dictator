import fetch from '@/utils/fetch'

export function list(params){
    return fetch({
        url: "/config/list",
        params
    })
}