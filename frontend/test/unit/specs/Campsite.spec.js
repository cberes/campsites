import Vue from 'vue'
import router from '@/router'
import Campsite from '@/components/Campsite'
import AvailabilityService from '@/services/AvailabilityService'
import CampsiteService from '@/services/CampsiteService'
import SettingsService from '@/services/SettingsService'
import injector from '@/services/InjectionService'
import {MockHttpService, waitForTicks} from '../helpers'

const mockHttpForCampsite = new MockHttpService()
const mockHttpForAvailability = new MockHttpService()
const settingsService = new SettingsService({
  backendHost: 'http://example.com'
})

function mount () {
  injector.reset()
  injector.provide('AvailabilityService', new AvailabilityService(settingsService, mockHttpForAvailability))
  injector.provide('CampsiteService', new CampsiteService(settingsService, mockHttpForCampsite))
  const route = {params: {id: 10101}}
  const Constructor = Vue.extend({ ...Campsite, router })
  return new Constructor({propsData: {$route: route}}).$mount()
}

describe('Campsite.vue', () => {
  it('should render correct contents', done => {
    mockHttpForCampsite.setResult(Promise.resolve({
      name: 'Example',
      description: 'Test example description'
    }))

    mockHttpForAvailability.setResult(Promise.resolve({
      campsites: [
        {
          availability: [
            {date: [2017, 1, 1], status: 'RESERVED'},
            {date: [2017, 1, 2], status: 'AVAILABLE'}
          ]
        }
      ]
    }))

    const vm = mount()

    waitForTicks(3, () => {
      expect(vm.$el.querySelector('#name').textContent)
        .to.equal('Example')
      expect(vm.$el.querySelector('#description').textContent)
        .to.equal('Test example description')
      expect(mockHttpForCampsite.getUrls())
        .to.include('http://example.com/api/campsite/10101')
      done()
    })
  })

  it('should render error message', done => {
    mockHttpForCampsite.setResult(Promise.reject({
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
