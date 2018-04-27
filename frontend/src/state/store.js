import Vue from 'vue'
import Vuex from 'vuex'

import campgroundModule from './campground'

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    campgrounds: campgroundModule
  },
  strict: process.env.NODE_ENV !== 'production'
})

export default store
