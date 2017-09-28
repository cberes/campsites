export default class CampsiteService {
  constructor (settings, http) {
    this.baseUrl = settings.backendHost + '/api/campsite'
    this.http = http
  }

  getCampsite (id) {
    const url = this.baseUrl + '/' + id
    return this.http.get(url)
  }

  static injectionDependencies () {
    return ['SettingsService', 'HttpService']
  }
}
