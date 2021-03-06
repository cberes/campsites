import Vue from 'vue'
import router from '@/router'
import Campground from '@/components/Campground'
import CampgroundService from '@/services/CampgroundService'
import SettingsService from '@/services/SettingsService'
import injector from '@/services/InjectionService'
import {MockHttpService, waitForTicks} from '../helpers'

const mockHttp = new MockHttpService()

function mount () {
  injector.reset()
  injector.register(CampgroundService)
  injector.register(SettingsService)
  injector.provide('HttpService', mockHttp)
  injector.provide('settings', {
    backendHost: 'http://example.com',
    campgroundId: 1001
  })
  const Constructor = Vue.extend({ ...Campground, router })
  return new Constructor().$mount()
}

describe('Campground.vue', () => {
  it('should render error message', done => {
    mockHttp.setResult(Promise.reject({
      message: 'Error message'
    }))

    const vm = mount()

    waitForTicks(3, () => {
      expect(vm.$el.textContent)
        .to.equal('Error message')
      done()
    })
  })

  it('should render loading message', () => {
    const vm = mount()

    expect(vm.$el.textContent)
      .to.equal('Loading...')
  })

  it('should render correct contents', done => {
    mockHttp.setResult(Promise.resolve({
      name: 'Example',
      description: 'Test example description'
    }))

    const vm = mount()

    waitForTicks(3, () => {
      expect(vm.$el.querySelector('#name').textContent)
        .to.equal('Welcome to Example')
      expect(vm.$el.querySelector('#description').textContent)
        .to.equal('Test example description')
      expect(mockHttp.getUrls())
        .to.include('http://example.com/api/campgrounds/1001')
      done()
    })
  })
})
