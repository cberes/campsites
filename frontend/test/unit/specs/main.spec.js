import Vue from 'vue'
import MainLayout from '@/router/layouts/main'

describe('@layouts/main', () => {
  it('renders its content', () => {
    const slotContent = '<p>Hello!</p>'
    const slotComp = new Vue({
      components: { MainLayout },
      template: '<MainLayout>' + slotContent + '</MainLayout>'
    })
    const vm = slotComp.$mount()
    const tags = vm.$el.getElementsByTagName('el-main')
    expect(tags.length).to.equal(1)
    expect(tags[0].innerHTML.replace(/\s*[\w-]+="[^"]*"/g, '')).to.equal(slotContent)
  })
})
