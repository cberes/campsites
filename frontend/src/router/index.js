import Vue from 'vue'
import Router from 'vue-router'
import Campground from '@/components/Campground'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Campground',
      component: Campground
    }
  ]
})
