<template>
  <div id="campground" class="container" v-if="campground">
    <h1 id="name">{{ campground.name }}</h1>
    <p id="description">{{ campground.description }}</p>
    <ul>
      <li><router-link to="/campsites">View campsites</router-link></li>
      <li><router-link to="/map">Campground map</router-link></li>
      <li v-if="blog"><a href="/blog/">Development blog</a></li>
    </ul>
  </div>
  <div class="error" v-else-if="error">{{ error }}</div>
  <div class="loading" v-else>Loading...</div>
</template>

<script>
import injector from '../services/InjectionService'

export default {
  name: 'campground',
  data () {
    return {
      campground: null,
      error: null,
      blog: window.location.hostname === 'awayfromho.me'
    }
  },
  mounted () {
    this.loadCampground(injector.inject('settings'), injector.inject('CampgroundService'))
  },
  methods: {
    loadCampground (settings, campgroundService) {
      campgroundService.getCampground(settings.campgroundId)
        .then(result => (this.campground = result))
        .catch(reason => (this.error = reason.message))
    }
  }
}
</script>

<style scoped>
.loading {
  color: #555555;
}

.error {
  color: #660000;
}
</style>
