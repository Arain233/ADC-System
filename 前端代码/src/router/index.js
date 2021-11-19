import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */

//路由不能重复配置 否则会报错
export const constantRoutes = [
  {
    path: '/login',
    //路由跳转 找到对应路由相应的页面样式
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/register',
    //路由跳转 找到对应路由相应的页面样式
    component: () => import('@/views/register/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      //相关描述 affix:表示将标签固钉，使其在头部永远显示
      meta: { title: '学生首页', icon: 'el-icon-s-custom', affix: true }
    }]
  },

  {
    path: '/generate',
    component: Layout,
    redirect: '/generate/manual',
    name: 'Generate',
    meta: { title: '证书生成', icon: 'el-icon-refresh' },
    children: [
      {
        path: 'manual',
        name: 'Manual',
        component: () => import('@/views/manual/index'),
        meta: { title: '手动生成', icon: 'el-icon-edit-outline' }
      },
      {
        path: 'import',
        name: 'Import',
        component: () => import('@/views/import/index'),
        meta: { title: '学信导入', icon: 'el-icon-upload' }
      }
    ]
  },

  {
    path: '/search',
    component: Layout,
    redirect: '/search/one',
    name: 'Search',
    meta: { title: '证书查询', icon: 'el-icon-search' },
    children: [
      {
        path: 'one',
        name: 'One',
        component: () => import('@/views/search/one'),
        hidden: true,
        meta: { title: '证书查询1', activeMenu: '/search', noCache: true }
      },
      {
        path: 'two',
        name: 'Two',
        component: () => import('@/views/search/two'),
        hidden: true,
        meta: { title: '证书查询2', activeMenu: '/search', noCache: true }
      }
    ]
  },

  {
    path: '/verification',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Verification',
        component: () => import('@/views/verification/index'),
        meta: { title: '证书核验', icon: 'el-icon-document-checked' }
      }
    ]
  },

  {
    path: '/monitor',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Monitor',
        component: () => import('@/views/monitor/index'),
        meta: { title: '服务监控', icon: 'el-icon-view' }
      }
    ]
  },

  {
    path: '/contactus',
    component: Layout,
    children: [
      {
        path: '',
        name: 'Contactus',
        component: () => import('@/views/contactus/index'),
        meta: { title: '联系我们', icon: 'el-icon-notebook-2' }
        // 如果直接外跳路由的话就不需要组件和名称
        // path:'https://www.baidu.com',
      }
    ]
  },

  //保证在刷新页面的时候（清除头部缓存）之后能够正确获取页面
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect/index')
      }
    ]
  },

  // 404 page must be placed at the end !!!
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  //去除url中的#
  mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes    //导入上面创建的路由表
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
