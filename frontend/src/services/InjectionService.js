class InjectionService {
  constructor () {
    this.provided = {}
    this.registered = {}
  }

  reset () {
    this.provided = {}
    this.registered = {}
  }

  provide (name, value) {
    this.provided[name] = value
  }

  register (service, dependencies) {
    this.registered[service.name] = {
      factory: service,
      dependencies: dependencies || []
    }
  }

  resolve (name) {
    const registration = this.registered[name]
    const dependencies = []
    for (let dependency of registration.dependencies) {
      dependencies.push(this.inject(dependency))
    }
    return new registration.factory(...dependencies) // eslint-disable-line new-cap
  }

  inject (name) {
    let value = this.provided[name]
    if (!value) {
      value = this.resolve(name)
      this.provide(name, value)
    }
    return value
  }
}

export default new InjectionService()
