import View404 from '@/router/views/404'

describe('@views/404', () => {
  it('is a valid view', () => {
    console.log(View404)
    expect(View404).to.exist
    expect(View404.components).to.exist
    expect(View404.components.Layout).to.exist
    expect(View404.page).to.exist
    expect(View404.page.title).to.exist
    expect(View404.page.meta).to.exist
  })
})
