import Vue from 'vue'
import Campground from '@/components/Campground'

describe('Campground.vue', () => {
  it('should render correct contents', () => {
    const Constructor = Vue.extend(Campground)
    const vm = new Constructor().$mount()
    expect(vm.$el.querySelector('.loading').textContent)
      .to.equal('Loading...')
  })
})
