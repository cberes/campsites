import Vue from 'vue'

export function waitForTicks (n, callback) {
  if (n === 0) {
    callback()
  } else {
    Vue.nextTick(() => {
      waitForTicks(n - 1, callback)
    })
  }
}

export class MockHttpService {
  getUrls () {
    return this.urls
  }

  get (url) {
    this.urls.push(url)
    return this.result
  }

  setResult (result) {
    this.result = result
    this.urls = []
  }
}
