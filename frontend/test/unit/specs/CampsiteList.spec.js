import Vue from 'vue'
import router from '@/router'
import moment from 'moment'
import CampsiteList from '@/components/CampsiteList'
import AvailabilityService from '@/services/AvailabilityService'
import CampgroundService from '@/services/CampgroundService'
import SettingsService from '@/services/SettingsService'
import injector from '@/services/InjectionService'
import {MockHttpService, waitForTicks} from '../helpers'

const settings = {
  backendHost: 'http://example.com',
  campgroundId: 1001
}
const mockHttpForCampground = new MockHttpService()
const mockHttpForAvailability = new MockHttpService()
const settingsService = new SettingsService(settings)

function mount () {
  injector.reset()
  injector.provide('settings', settings)
  injector.provide('AvailabilityService', new AvailabilityService(settingsService, mockHttpForAvailability))
  injector.provide('CampgroundService', new CampgroundService(settingsService, mockHttpForCampground))
  const Constructor = Vue.extend({ ...CampsiteList, router })
  return new Constructor().$mount()
}

describe('CampsiteList.vue', () => {
  it('should render correct contents', done => {
    mockHttpForCampground.setResult(Promise.resolve([{
      id: 10101,
      name: 'Example 1',
      description: 'Test example description 1'
    },
    {
      id: 10102,
      name: 'Example 2',
      description: 'Test example description 2'
    }]))

    mockHttpForAvailability.setResult(Promise.resolve({
      campsites: [
        {
          id: 10102,
          availability: [
            {date: [2017, 1, 1], status: 'RESERVED'},
            {date: [2017, 1, 2], status: 'AVAILABLE'}
          ]
        },
        {
          id: 10101,
          availability: [
            {date: [2017, 1, 1], status: 'FIRST_COME_FIRST_SERVED'},
            {date: [2017, 1, 2], status: 'AVAILABLE'}
          ]
        }
      ]
    }))

    const vm = mount()

    waitForTicks(3, () => {
      const start = moment().add(1, 'days')
      const end = start.clone().add(5, 'days')
      expect(vm.$el.querySelectorAll('.name')[0].textContent)
        .to.equal('Example 1')
      expect(vm.$el.querySelectorAll('.name')[1].textContent)
        .to.equal('Example 2')
      expect(vm.$el.querySelectorAll('.quick-availability .availability-period')[0].textContent)
        .to.include('2017-01-01: FIRST_COME_FIRST_SERVED')
      expect(vm.$el.querySelectorAll('.quick-availability .availability-period')[1].textContent)
        .to.include('2017-01-02: AVAILABLE')
      expect(vm.$el.querySelectorAll('.quick-availability .availability-period')[2].textContent)
        .to.include('2017-01-01: RESERVED')
      expect(vm.$el.querySelectorAll('.quick-availability .availability-period')[3].textContent)
        .to.include('2017-01-02: AVAILABLE')
      expect(mockHttpForAvailability.getUrls())
        .to.include('http://example.com/api/availability/campgrounds/1001?start=' +
        start.format('YYYY-MM-DD') + '&end=' + end.format('YYYY-MM-DD'))
      expect(mockHttpForCampground.getUrls())
        .to.include('http://example.com/api/campgrounds/1001/campsites')
      done()
    })
  })

  it('should render error message', done => {
    mockHttpForCampground.setResult(Promise.reject({
      message: 'Error message'
    }))

    const vm = mount()

    waitForTicks(3, () => {
      expect(vm.$el.querySelector('.error').textContent)
        .to.equal('Error message')
      done()
    })
  })

  it('should render loading message', () => {
    const vm = mount()

    expect(vm.$el.querySelector('.loading').textContent)
      .to.equal('Loading...')
  })
})
