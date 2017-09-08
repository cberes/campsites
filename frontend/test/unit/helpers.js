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
  get (url) {
    return this.resultFunc(url)
  }

  setResultFunc (resultFunc) {
    this.resultFunc = resultFunc
  }
}
