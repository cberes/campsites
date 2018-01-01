import AvailabilityService from '@/services/AvailabilityService'
import moment from 'moment'

let getResult = null

const mockHttp = {
  get: url => getResult(url)
}

describe('AvailabilityService', () => {
  it('getArea', done => {
    getResult = url => {
      return Promise.resolve({
        value: 'Queried ' + url
      })
    }

    const service = new AvailabilityService({backendHost: 'http://example.com'}, mockHttp)
    const start = moment({year: 2017, month: 0, day: 1})
    const end = moment({year: 2017, month: 0, day: 15})
    service.getArea(10101, start, end).then(result => {
      expect(result.value).to.equal('Queried http://example.com/api/availability/area/10101?start=2017-01-01&end=2017-01-15')
      done()
    })
  })

  it('getCampground', done => {
    getResult = url => {
      return Promise.resolve({
        value: 'Queried ' + url
      })
    }

    const service = new AvailabilityService({backendHost: 'http://example.com'}, mockHttp)
    const start = moment({year: 2017, month: 0, day: 1})
    const end = moment({year: 2017, month: 0, day: 15})
    service.getCampground(10101, start, end).then(result => {
      expect(result.value).to.equal('Queried http://example.com/api/availability/campground/10101?start=2017-01-01&end=2017-01-15')
      done()
    })
  })

  it('getCampsite', done => {
    getResult = url => {
      return Promise.resolve({
        value: 'Queried ' + url
      })
    }

    const service = new AvailabilityService({backendHost: 'http://example.com'}, mockHttp)
    const start = moment({year: 2017, month: 0, day: 1})
    const end = moment({year: 2017, month: 0, day: 15})
    service.getCampsite(10101, start, end).then(result => {
      expect(result.value).to.equal('Queried http://example.com/api/availability/campsite/10101?start=2017-01-01&end=2017-01-15')
      done()
    })
  })

  it('keyByCampsiteId', () => {
    const keyed = AvailabilityService.keyByCampsiteId({
      campgroundId: 1001,
      campsites: [
        {
          id: 10101,
          availability: [
            {date: '2017-01-01', status: 'RESERVED'},
            {date: '2017-01-02', status: 'AVAILABLE'}
          ]
        },
        {
          id: 10102,
          availability: [
            {date: '2017-01-01', status: 'FIRST_COME_FIRST_SERVE'},
            {date: '2017-01-02', status: 'RESERVED'}
          ]
        }
      ]
    })
    expect(keyed[10101]).to.deep.equal([
      {date: '2017-01-01', status: 'RESERVED'},
      {date: '2017-01-02', status: 'AVAILABLE'}
    ])
    expect(keyed[10102]).to.deep.equal([
      {date: '2017-01-01', status: 'FIRST_COME_FIRST_SERVE'},
      {date: '2017-01-02', status: 'RESERVED'}
    ])
  })
})
