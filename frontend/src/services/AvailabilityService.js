export default class AvailabilityService {
  constructor (settings, http) {
    this.baseUrl = settings.backendHost + '/api/availability'
    this.http = http
  }

  getArea (id, start, end) {
    const url = this.baseUrl + '/area/' + id + '?' + this.buildRangeQuery(start, end)
    return this.http.get(url)
  }

  buildRangeQuery (start, end) {
    return 'start=' + start.format('YYYY-MM-DD') + '&end=' + end.format('YYYY-MM-DD')
  }

  getCampground (id, start, end) {
    const url = this.baseUrl + '/campground/' + id + '?' + this.buildRangeQuery(start, end)
    return this.http.get(url)
  }

  getCampsite (id, start, end) {
    const url = this.baseUrl + '/campsite/' + id + '?' + this.buildRangeQuery(start, end)
    return this.http.get(url)
  }

  static keyByCampsiteId (availability) {
    const keyed = {}
    availability.campsites.forEach(campsite => (keyed[campsite.id] = campsite.availability))
    return keyed
  }

  static injectionDependencies () {
    return ['SettingsService', 'HttpService']
  }
}
