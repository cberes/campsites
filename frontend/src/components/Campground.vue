<template>
  <div id="campground" v-if="campground">
    <h1 id="name">{{ campground.name }}</h1>
    <p id="description">{{ campground.description }}</p>
    <p>View the <a href="campsites.html">list of campsites</a> or the <a href="map.html">map</a>.</p>
  </div>
  <div class="error" v-else-if="error">{{ error }}</div>
  <div class="loading" v-else>Loading...</div>
</template>

<script>
import services from '../services'

const config = {
  CAMPGROUND_ID: 0
}

export default {
  name: 'campground',
  data () {
    return {
      campground: null,
      error: null
    }
  },
  mounted () {
    this.loadCampground(config.CAMPGROUND_ID)
  },
  methods: {
    loadCampground (id) {
      services.campgrounds.getCampground(id)
        .then(response => response.json())
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
