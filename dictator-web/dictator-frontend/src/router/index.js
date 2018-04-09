import Vue from 'vue'
import Router from 'vue-router'

const _import = require('./_import_' + process.env.NODE_ENV)
// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/** note: submenu only apppear when children.length>=1
 *   detail see  https://panjiachen.github.io/vue-element-admin-site/#/router-and-nav?id=sidebar
 **/

/**
 * hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
 *                                if not set alwaysShow, only more than one route under the children
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    resourceList: ['admin','editor']     will control the page resourceList (you can set multiple resourceList)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if fasle ,the page will no be cached(default is false)
  }
 **/
export const constantRouterMap = [
  {
    path: '',
    component: Layout,
    meta: {title: 'config', icon: 'dashboard', noCache: true},
    children: [
      {
        path: 'config/current',
        component: _import('config/current'),
        name: 'currentConfig',
        meta: {title: 'currentConfig', icon: 'config', noCache: true}
      },
      {
        path: 'config/history',
        component: _import('config/history'),
        name: 'configHistory',
        meta: {title: 'configHistory', icon: 'history', noCache: true}
      },
      {
        path: 'config/profile',
        component: _import('config/profile'),
        name: 'profile',
        meta: {title: 'profile', icon: 'profile', noCache: true}
      }
    ]
  },
  {
    path: '/permission',
    component: Layout,
    meta: {title: 'permission', icon: 'permission', noCache: true},
    children: [
      {
        path: 'user',
        component: _import('permission/user'),
        name: 'user',
        meta: {title: 'user', icon: 'user', noCache: true}
      },
      {
        path: 'role',
        component: _import('permission/role'),
        name: 'role',
        meta: {title: 'role', icon: 'role', noCache: true}
      },{
        path: 'resource',
        component: _import('permission/resource'),
        name: 'resource',
        meta: {title: 'resource', icon: 'resource', noCache: true}
      }
    ]
  },
  {path: '/login', component: _import('login/index'), hidden: true},
  {path: '/authredirect', component: _import('login/authredirect'), hidden: true},
  {path: '/404', component: _import('errorPage/404'), hidden: true},
  {path: '/401', component: _import('errorPage/401'), hidden: true},
  {path: '*', redirect: '/404', hidden: true}
];

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  // {
  //   path: '/dashboard',
  //   component: Layout,
  //   redirect: '/dashboard',
  //   meta: {}, // you can set resourceList in root nav
  // },
  {path: '*', redirect: '/404', hidden: true}
];
