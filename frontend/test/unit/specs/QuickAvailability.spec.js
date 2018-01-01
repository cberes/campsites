import Vue from 'vue'
import QuickAvailability from '@/components/QuickAvailability'

describe('QuickAvailability.vue', () => {
  it('should render correct contents when loading', () => {
    const Constructor = Vue.extend(QuickAvailability)
    const vm = new Constructor().$mount()
    expect(vm.$el.textContent)
      .to.equal('Loading...')
  })

  it('should render correct contents when loaded', () => {
    const Constructor = Vue.extend(QuickAvailability)
    const vm = new Constructor({propsData: {
      availability: [
        {date: '2017-01-01', status: 'RESERVED'},
        {date: '2017-01-02', status: 'AVAILABLE'}
      ]
    }}).$mount()
    expect(vm.$el.children[0].textContent)
      .to.include('Jan 1: RESERVED')
    expect(vm.$el.children[1].textContent)
      .to.include('Jan 2: AVAILABLE')
  })
})
