export default class HttpService {
  get (url) {
    const request = new Request(url)
    return fetch(request).then(response => response.json())
  }
}
