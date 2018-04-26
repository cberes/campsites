export default [
  {
    path: '/',
    name: 'campground',
    component: require('@/router/views/campground').default
  },
  {
    path: '/campsites',
    name: 'campsite-list',
    component: require('@/router/views/campsite-list').default
  },
  {
    path: '/campsite/:id',
    name: 'campsite',
    component: require('@/router/views/campsite').default,
    props: true
  },
  {
    path: '/404',
    name: '404',
    component: require('@/router/views/404').default,
    props: true
  },
  {
    path: '*',
    redirect: '404'
  }
]
