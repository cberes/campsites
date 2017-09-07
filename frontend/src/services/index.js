import CampgroundService from './campground-service'
import HttpService from './http-service'
import SettingsService from './settings-service'

const http = new HttpService()
export const settings = new SettingsService({
  backendHost: 'http://localhost:8080',
  campgroundId: 0
})
export const campgroundService = new CampgroundService(settings.backendHost, http)
