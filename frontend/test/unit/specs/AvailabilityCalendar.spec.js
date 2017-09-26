import Vue from 'vue'
import AvailabilityService from '@/services/AvailabilityService'
import {MockHttpService, waitForTicks} from '../helpers'

const mockHttp = new MockHttpService()

const AvailabilityCalendarInjector = require('!!vue-loader?inject!../../../src/components/AvailabilityCalendar.vue')

const AvailabilityCalendarWithMocks = AvailabilityCalendarInjector({
  '../services': {
    availabilityService: new AvailabilityService('http://example.com', mockHttp)
  }
})

describe('AvailabilityCalendar.vue', () => {
  it('should render correct contents', done => {
    mockHttp.setResult(Promise.resolve({
      campsites: [
        {
          availability: [
            {date: [2017, 1, 1], status: 'RESERVED'},
            {date: [2017, 1, 2], status: 'AVAILABLE'}
          ]
        }
      ]
    }))

    const vm = new Vue({
      template: '<div><test></test></div>',
      components: {
        'test': AvailabilityCalendarWithMocks
      },
      propsData: {
        id: 10101,
        today: '2017-01-01'
      }
    }).$mount()

    waitForTicks(3, () => {
      const items = vm.$el.querySelector('#availability').children
      expect(items[0].textContent)
        .to.include('2017-01-01: RESERVED')
      expect(items[1].textContent)
        .to.include('2017-01-02: AVAILABLE')
      expect(mockHttp.getUrls())
        .to.include('http://example.com/api/avilability/campsite/10101?start=2017-01-01&end=2017-01-15')
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
        'test': AvailabilityCalendarWithMocks
      },
      propsData: {
        id: 10101,
        today: '2017-01-01'
      }
    }).$mount()

    waitForTicks(3, () => {
      expect(vm.$el.textContent)
        .to.equal('Error message')
      done()
    })
  })

  it('should render loading message', () => {
    const vm = new Vue({
      template: '<div><test></test></div>',
      components: {
        'test': AvailabilityCalendarWithMocks
      },
      propsData: {
        id: 10101,
        today: '2017-01-01'
      }
    }).$mount()

    expect(vm.$el.textContent)
      .to.equal('Loading...')
  })
})
