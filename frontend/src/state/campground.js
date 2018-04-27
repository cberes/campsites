import injector from '../services/InjectionService'

const state = {
  campground: null
}

const mutations = {
  SET_CURRENT_CAMPGROUND (state, newValue) {
    state.campground = newValue
  }
}

const actions = {
  fetchCampground ({ commit, state, rootState }) {
    if (state.campground) {
      return Promise.resolve(state.campground)
    }

    const settings = injector.inject('settings')
    const service = injector.inject('CampgroundService')

    return service.getCampground(settings.campgroundId).then(result => {
      commit('SET_CURRENT_CAMPGROUND', result)
      return result
    })
  }
}

export default {
  namespaced: true,
  state: state,
  mutations: mutations,
  actions: actions
}
