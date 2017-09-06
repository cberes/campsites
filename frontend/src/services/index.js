import HttpService from './http-service'
import CampgroundService from './campground-service'

const config = {
  BACKEND_HOST: 'http://localhost:8080'
}

const http = new HttpService()

export default {
  campgrounds: new CampgroundService(config.BACKEND_HOST, http)
}
