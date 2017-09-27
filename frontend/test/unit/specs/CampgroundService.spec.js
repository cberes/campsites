import CampgroundService from '@/services/CampgroundService'

let getResult = null

const mockHttp = {
  get: url => getResult(url)
}

describe('CampgroundService', () => {
  it('getCampground', done => {
    getResult = url => {
      return Promise.resolve({
        value: 'Queried ' + url
      })
    }

    const service = new CampgroundService({backendHost: 'http://example.com'}, mockHttp)
    service.getCampground(1001).then(result => {
      expect(result.value).to.equal('Queried http://example.com/api/campground/1001')
      done()
    })
  })

  it('getCampgrounds', done => {
    getResult = (url) => {
      return Promise.resolve({
        value: 'Queried ' + url
      })
    }

    const service = new CampgroundService({backendHost: 'http://example.com'}, mockHttp)
    service.getCampgrounds().then(result => {
      expect(result.value).to.equal('Queried http://example.com/api/campground')
      done()
    })
  })
})
