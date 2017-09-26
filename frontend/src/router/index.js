import Vue from 'vue'
import Router from 'vue-router'
import Campground from '@/components/Campground'
import Campsite from '@/components/Campsite'
import CampsiteList from '@/components/CampsiteList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Campground',
      component: Campground
    },
    {
      path: '/campsites',
      name: 'CampsiteList',
      component: CampsiteList
    },
    {
      path: '/campsite/:id',
      name: 'Campsite',
      component: Campsite
    }
  ]
})
