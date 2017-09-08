import Vue from 'vue'
import CampgroundService from '@/services/CampgroundService'
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
    mockHttp.setResult(Promise.resolve({
      name: 'Example',
      description: 'Test example description'
    }))

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
        .to.equal('Test example description')
      expect(mockHttp.getUrls())
        .to.include('http://example.com/api/campground/1001')
      done()
    })
  })

  it('should render error message', done => {
    mockHttp.setResult(Promise.reject({
      message: 'Error message'
    }))

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
