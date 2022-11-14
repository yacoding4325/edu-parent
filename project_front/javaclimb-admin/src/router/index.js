import Vue from 'vue'
import Router from 'vue-router'
Vue.use(Router)
import Layout from '../views/layout/Layout'

export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  //讲师管理
  {
    path: '/teacher',
    component: Layout,
    redirect: '/teacher/list',
    name: 'Teacher',
    meta: { title: '讲师管理', icon: 'peoples' },
    children: [
      {
        path: 'list',
        name: 'TeacherList',
        component: () => import('@/views/teacher/list'),
        meta: { title: '讲师列表', icon: 'peoples' }
      },
      {
        path: 'create',
        name: 'TeacherCreate',
        component: () => import('@/views/teacher/form'),
        meta: { title: '添加讲师',icon: 'form' }
      },
      {
        path: 'edit/:id',
        name: 'TeacherEdit',
        component: () => import('@/views/teacher/form'),
        meta: { title: '编辑讲师',icon: 'edit' },
        hidden: true
      }
    ]
  },

    //课程分类
    {
      path: '/subject',
      component: Layout,
      redirect: '/subject/list',
      name: 'Subject',
      meta: { title: '课程分类管理', icon: 'example' },
      children: [
        {
          path: 'list',
          name: 'SubjectList',
          component: () => import('@/views/subject/list'),
          meta: { title: '课程分类列表', icon: 'table' }
        },
        {
          path: 'create',
          name: 'SubjectCreate',
          component: () => import('@/views/subject/import'),
          meta: { title: '添加课程分类',icon: 'excel' }
        }
      ]
    },

    //课程分类
    {
      path: '/course',
      component: Layout,
      redirect: '/course/list',
      name: 'Course',
      meta: { title: '课程管理', icon: 'example' },
      children: [
        {
          path: 'list',
          name: 'EduCourseList',
          component: () => import('@/views/course/list'),
          meta: { title: '课程列表', icon: 'table' }
        },
        {
          path: 'info',
          name: 'EduCourseInfo',
          component: () => import('@/views/course/info'),
          meta: { title: '发布课程',icon: 'excel' }
        },
        {
          path: 'info/:id',
          name: 'EduCourseEdit',
          component: () => import('@/views/course/info'),
          meta: { title: '编辑课程基本信息',icon: 'edit',noCache: true },
          hidden: true
        },
        {
          path: 'chapter/:id',
          name: 'EduCourseChapterEdit',
          component: () => import('@/views/course/chapter'),
          meta: { title: '编辑课程大纲',noCache: true },
          hidden: true
        },
        {
          path: 'publish/:id',
          name: 'EduCoursePublishEdit',
          component: () => import('@/views/course/publish'),
          meta: { title: '发布课程',noCache: true },
          hidden: true
        }        
      ]
    },

    //内容管理
    {
      path: '/ad',
      component: Layout,
      redirect: '/ad/list',
      name: 'Ad',
      meta: { title: '内容管理', icon: 'example' },
      children: [
        {
          path: 'list',
          name: 'AdList',
          component: () => import('@/views/ad/list'),
          meta: { title: '广告推荐', icon: 'table' }
        },
        {
          path: 'create',
          name: 'AdCreate',
          component: () => import('@/views/ad/form'),
          meta: { title: '添加广告推荐',icon: 'excel' }
        },
        {
          path: 'edit/:id',
          name: 'AdEdit',
          component: () => import('@/views/ad/form'),
          meta: { title: '编辑广告推荐',icon: 'edit',noCache: true },
          hidden: true
        },
        {
          path: 'type-list',
          name: 'AdTypeList',
          component: () => import('@/views/adType/list'),
          meta: { title: '推荐位', icon: 'table' }
        },
        {
          path: 'type-create',
          name: 'AdTypeCreate',
          component: () => import('@/views/adType/form'),
          meta: { title: '添加推荐位',icon: 'excel' }
        },
        {
          path: 'type-edit/:id',
          name: 'AdTypeEdit',
          component: () => import('@/views/adType/form'),
          meta: { title: '编辑推荐位',icon: 'edit',noCache: true },
          hidden: true
        }
      ]
    },

    //统计分析
    {
      path: '/statistics',
      component: Layout,
      redirect: '/statistics/create',
      name: '统计分析',
      meta: { title: '统计分析', icon: 'example' },
      children: [
        {
          path: 'create',
          name: '生成数据',
          component: () => import('@/views/statistics/create'),
          meta: { title: '生成数据', icon: 'table' }
        },
        {
          path: 'chart',
          name: '图表显示',
          component: () => import('@/views/statistics/chart'),
          meta: { title: '图表显示', icon: 'table' }
        },
      ]
    },

    //权限管理
    {
      path: '/acl',
      component: Layout,
      redirect: '/acl/menu/list',
      name: '权限管理',
      meta: { title: '权限管理', icon: 'example' },
      children: [
        {
          path: 'user/list',
          name: '用户管理',
          component: () => import('@/views/acl/user/list'),
          meta: { title: '用户管理', icon: 'table' }
        },
        {
          path: 'user/form',
          name: '用户添加',
          component: () => import('@/views/acl/user/form'),
          meta: { title: '用户添加'},
          hidden: true
        },
        {
          path: 'user/update/:id',
          name: '用户修改',
          component: () => import('@/views/acl/user/form'),
          meta: { title: '用户修改'},
          hidden: true
        },
        {
          path: 'user/role/:id',
          name: '用户角色',
          component: () => import('@/views/acl/user/roleForm'),
          meta: { title: '用户角色'},
          hidden: true
        },
        {
          path: 'role/list',
          name: '角色管理',
          component: () => import('@/views/acl/role/list'),
          meta: { title: '角色管理', icon: 'table' }
        },
        {
          path: 'role/form',
          name: '角色添加',
          component: () => import('@/views/acl/role/form'),
          meta: { title: '角色添加'},
          hidden: true
        },
        {
          path: 'role/update/:id',
          name: '角色修改',
          component: () => import('@/views/acl/role/form'),
          meta: { title: '角色修改'},
          hidden: true
        },
        {
          path: 'role/distribution/:id',
          name: '角色权限',
          component: () => import('@/views/acl/role/roleForm'),
          meta: { title: '角色权限'},
          hidden: true
        },
        {
          path: 'menu/list',
          name: '菜单管理',
          component: () => import('@/views/acl/menu/list'),
          meta: { title: '菜单管理', icon: 'table' }
        },
      ]
    },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
