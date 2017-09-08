import Vue from 'vue'
import CampgroundService from '@/services/campground-service'
import {MockHttpService, waitForTicks} from '../helpers'

const mockHttp = new MockHttpService()

const CampgroundInjector = require('!!vue-loader?inject!../../../src/components/Campground.vue')

const CampgroundWithMocks = CampgroundInjector({
  '../services': {
    campgroundService: new CampgroundService('http://example.com', mockHttp),
    settings: {
      campgroundId: 1001
    }
  }
})

describe('Campground.vue', () => {
  it('should render correct contents', done => {
    mockHttp.setResultFunc(url => {
      return Promise.resolve({
        name: 'Example',
        description: 'Test example queried ' + url
      })
    })

    const vm = new Vue({
      template: '<div><test></test></div>',
      components: {
        'test': CampgroundWithMocks
      }
    }).$mount()

    waitForTicks(3, () => {
      expect(vm.$el.querySelector('#name').textContent)
        .to.equal('Example')
      expect(vm.$el.querySelector('#description').textContent)
        .to.equal('Test example queried http://example.com/api/campground/1001')
      done()
    })
  })

  it('should render error message', done => {
    mockHttp.setResultFunc(() => {
      return Promise.reject({
        message: 'Error message'
      })
    })

    const vm = new Vue({
      template: '<div><test></test></div>',
      components: {
        'test': CampgroundWithMocks
      }
    }).$mount()

    waitForTicks(3, () => {
      expect(vm.$el.querySelector('.error').textContent)
        .to.equal('Error message')
      done()
    })
  })

  it('should render loading message', () => {
    const vm = new Vue({
      template: '<div><test></test></div>',
      components: {
        'test': CampgroundWithMocks
      }
    }).$mount()

    expect(vm.$el.querySelector('.loading').textContent)
      .to.equal('Loading...')
  })
})
