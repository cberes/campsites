// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import injector from './services/InjectionService'
import HttpService from './services/HttpService'
import SettingsService from './services/SettingsService'
import AvailabilityService from './services/AvailabilityService'
import CampgroundService from './services/CampgroundService'
import CampsiteService from './services/CampsiteService'

injector.register(HttpService)
injector.register(SettingsService, ['settings'])
injector.register(AvailabilityService, ['SettingsService', 'HttpService'])
injector.register(CampgroundService, ['SettingsService', 'HttpService'])
injector.register(CampsiteService, ['SettingsService', 'HttpService'])
injector.provide('settings', {
  backendHost: 'http://localhost:8080',
  campgroundId: 1
})

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
