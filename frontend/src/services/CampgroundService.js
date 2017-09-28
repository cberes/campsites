export default class CampgroundService {
  constructor (settings, http) {
    this.baseUrl = settings.backendHost + '/api/campground'
    this.http = http
  }

  getCampground (id) {
    const url = this.baseUrl + '/' + id
    return this.http.get(url)
  }

  getCampgrounds () {
    return this.http.get(this.baseUrl)
  }

  getCampsites (id) {
    const url = this.baseUrl + '/' + id + '/campsites'
    return this.http.get(url)
  }

  static injectionDependencies () {
    return ['SettingsService', 'HttpService']
  }
}
