export default class CampsiteService {
  constructor (host, http) {
    this.baseUrl = host + '/api/campsite'
    this.http = http
  }

  getCampsite (id) {
    const url = this.baseUrl + '/' + id
    return this.http.get(url)
  }
}
