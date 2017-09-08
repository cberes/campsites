import CampgroundService from './CampgroundService'
import HttpService from './HttpService'
import SettingsService from './SettingsService'

const http = new HttpService()
export const settings = new SettingsService({
  backendHost: 'http://localhost:8080',
  campgroundId: 0
})
export const campgroundService = new CampgroundService(settings.backendHost, http)
