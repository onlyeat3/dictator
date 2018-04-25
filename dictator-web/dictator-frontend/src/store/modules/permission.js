import { asyncRouterMap, constantRouterMap } from '@/router'

/**
 * 通过meta.role判断是否与当前用户权限匹配
 * @param resourceList
 * @param route
 */
function hasPermission(resourceList, route) {
  if (route.path) {
    return resourceList.some(resource => {
      let contains = resource.targetUri.indexOf(route.path) >= 0;
      return contains;
    })
  } else {
    return false;
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param asyncRouterMap
 * @param resourceList
 */
function filterAsyncRouter(asyncRouterMap, resourceList) {
  const accessedRouters = asyncRouterMap.filter(route => {
    if (hasPermission(resourceList, route)) {
      if (route.children && route.children.length) {
        route.children = filterAsyncRouter(route.children, resourceList)
      }
      return true
    }
    return false
  })
  return accessedRouters
}

const permission = {
  state: {
    routers: constantRouterMap,
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers;
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const { resourceList } = data;
        if(resourceList != null && resourceList.length > 0){
          let accessedRouters = filterAsyncRouter(asyncRouterMap, resourceList);
          commit('SET_ROUTERS', accessedRouters)
        }
        resolve()
      })
    }
  }
}

export default permission
