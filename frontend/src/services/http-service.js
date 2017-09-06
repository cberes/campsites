export default class HttpService {
  get (url) {
    return fetch(new Request(url))
  }
}

