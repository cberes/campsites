<script>
import store from '@/state/store'

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
      this.loadCampground()
    }
  },
  methods: {
    loadCampground () {
      store.dispatch('campgrounds/fetchCampground')
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
