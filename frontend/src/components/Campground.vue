<script>
import store from '@/state/store'

export default {
  name: 'campground',
  data () {
    return {
      campground: null,
      error: null
    }
  },
  mounted () {
    this.loadCampground()
  },
  methods: {
    loadCampground (settings, campgroundService) {
      store.dispatch('campgrounds/fetchCampground')
        .then(result => (this.campground = result))
        .catch(reason => (this.error = reason.message))
    }
  }
}
</script>

<template>
  <div class="container" v-if="campground">
    <h1 id="name">Welcome to {{ campground.name }}</h1>
    <p id="description">{{ campground.description }}</p>
  </div>
  <div class="error" v-else-if="error">{{ error }}</div>
  <div class="loading" v-else>Loading...</div>
</template>

<style scoped>
.loading {
  color: #555555;
}

.error {
  color: #660000;
}
</style>
