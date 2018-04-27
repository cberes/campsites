import Vue from 'vue'
import vueTestUtils from '@vue/test-utils'
import MainLayout from '@/router/layouts/main'

describe('@layouts/main', () => {
  it('renders its content', () => {
    const slotContent = '<p>Hello!</p>'
    const comp = new Vue({
      template: slotContent
    })
    const { element } = vueTestUtils.shallow(MainLayout, {
      slots: {
        default: comp
      }
    })
    expect(element.innerHTML).to.include(slotContent)
  })
})
