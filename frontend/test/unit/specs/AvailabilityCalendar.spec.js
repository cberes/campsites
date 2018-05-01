import Vue from 'vue'
import moment from 'moment'
import AvailabilityCalendar from '@/components/AvailabilityCalendar'
import AvailabilityService from '@/services/AvailabilityService'
import SettingsService from '@/services/SettingsService'
import injector from '@/services/InjectionService'
import {MockHttpService, waitForTicks} from '../helpers'

const mockHttp = new MockHttpService()

function mount (day) {
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
    today: moment({year: 2017, month: 0, day: day || 1})
  }}).$mount()
}

function expectEmpty (el) {
  const headers = el.getElementsByTagName('th')
  const data = el.getElementsByTagName('td')

  expect(headers.length).to.equal(8)
  expect(headers[0].innerHTML).to.equal('January 2017')
  expect(data.length).to.equal(35)
  expect(data[0].children.length).to.equal(2)
  expect(data[0].children[0].innerHTML).to.equal('1')
  expect(data[0].children[1].innerHTML).to.equal('&nbsp;')
  expect(data[1].children.length).to.equal(2)
  expect(data[1].children[0].innerHTML).to.equal('2')
  expect(data[1].children[1].innerHTML).to.equal('&nbsp;')
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
      expect(mockHttp.getUrls())
        .to.include('http://example.com/api/availability/campsite/10101?start=2017-01-01&end=2017-02-01')

      const headers = vm.$el.getElementsByTagName('th')
      const data = vm.$el.getElementsByTagName('td')

      expect(headers.length).to.equal(8)
      expect(headers[0].innerHTML).to.equal('January 2017')
      expect(data.length).to.equal(35)
      expect(data[0].children.length).to.equal(2)
      expect(data[0].children[0].innerHTML).to.equal('1')
      expect(data[0].children[1].innerHTML).to.equal('R')
      expect(data[1].children.length).to.equal(2)
      expect(data[1].children[0].innerHTML).to.equal('2')
      expect(data[1].children[1].innerHTML).to.equal('A')
      done()
    })
  })

  it('should render error message', done => {
    mockHttp.setResult(Promise.reject({
      message: 'Error message'
    }))

    const vm = mount()

    waitForTicks(3, () => {
      expectEmpty(vm.$el)
      done()
    })
  })

  it('should render loading message', () => {
    const vm = mount()
    expectEmpty(vm.$el)
  })
})
