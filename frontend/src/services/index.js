import AvailabilityService from './AvailabilityService'
import CampgroundService from './CampgroundService'
import CampsiteService from './CampsiteService'
import HttpService from './HttpService'
import SettingsService from './SettingsService'

const http = new HttpService()
export const settings = new SettingsService({
  backendHost: 'http://localhost:8080',
  campgroundId: 1
})
export const availabilityService = new AvailabilityService(settings.backendHost, http)
export const campgroundService = new CampgroundService(settings.backendHost, http)
export const campsiteService = new CampsiteService(settings.backendHost, http)
