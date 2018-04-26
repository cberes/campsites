<script>
import injector from '../services/InjectionService'

export default {
  props: {
    title: {
      type: String,
      default: ''
    }
  },
  data () {
    return {
      campground: null
    }
  },
  mounted () {
    if (!this.props) {
      this.loadCampground(injector.inject('settings'), injector.inject('CampgroundService'))
    }
  },
  methods: {
    loadCampground (settings, campgroundService) {
      campgroundService.getCampground(settings.campgroundId)
        .then(result => (this.campground = result))
        .catch(console.error)
    }
  }
}
</script>

<template>
  <h1 v-if="title">{{ title }}</h1>
  <h1 v-else-if="campground">{{ campground.name }}</h1>
  <h1 v-else>Loading...</h1>
</template>

<style scoped>
</style>
