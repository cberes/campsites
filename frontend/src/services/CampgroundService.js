export default class CampgroundService {
  constructor (host, http) {
    this.baseUrl = host + '/api/campground'
    this.http = http
  }

  getCampground (id) {
    const url = this.baseUrl + '/' + id
    return this.http.get(url)
  }

  getCampgrounds () {
    return this.http.get(this.baseUrl)
  }
}
