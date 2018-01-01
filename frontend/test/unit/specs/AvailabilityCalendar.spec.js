import Vue from 'vue'
import AvailabilityCalendar from '@/components/AvailabilityCalendar'
import AvailabilityService from '@/services/AvailabilityService'
import SettingsService from '@/services/SettingsService'
import injector from '@/services/InjectionService'
import {MockHttpService, waitForTicks} from '../helpers'

const mockHttp = new MockHttpService()

function mount () {
  injector.reset()
  injector.register(AvailabilityService)
  injector.register(SettingsService)
  injector.provide('HttpService', mockHttp)
  injector.provide('settings', {
    backendHost: 'http://example.com'
  })
  const Constructor = Vue.extend(AvailabilityCalendar)
  return new Constructor({propsData: {
    id: 10101,
    today: '2017-01-01'
  }}).$mount()
}

describe('AvailabilityCalendar.vue', () => {
  it('should render correct contents', done => {
    mockHttp.setResult(Promise.resolve({
      campsites: [
        {
          availability: [
            {date: '2017-01-01', status: 'RESERVED'},
            {date: '2017-01-02', status: 'AVAILABLE'}
          ]
        }
      ]
    }))

    const vm = mount()

    waitForTicks(3, () => {
      const items = vm.$el.children
      expect(items[0].textContent)
        .to.include('Jan 1: RESERVED')
      expect(items[1].textContent)
        .to.include('Jan 2: AVAILABLE')
      expect(mockHttp.getUrls())
        .to.include('http://example.com/api/availability/campsite/10101?start=2017-01-01&end=2017-01-15')
      done()
    })
  })

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
})
