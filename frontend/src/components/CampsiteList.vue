<template>
  <div class="container">
    <h1>Campsite list</h1>
    <div id="campground" v-if="campsites">
      <div class="row" v-for="campsite in campsites">
        <div class="col-4">
          <router-link class="name" :to="{ name: 'Campsite', params: { id: campsite.id }}">{{ campsite.name }}</router-link>
          <quick-availability :availability="availability[campsite.id]"></quick-availability>
        </div>
        <ul class="col-4">
          <li class="size">{{ campsite.size }}'</li>
          <li class="electric">{{ campsite.electric }}</li>
          <li class="vehicles">Max {{ campsite.maxVehicles }} vehicle(s)</li>
          <li class="occupancy">Max {{ campsite.maxOccupancy }} guests</li>
          <li class="pets">Max {{ campsite.petsAllowed }} pets</li>
        </ul>
        <div class="col-4">
          <img class="small" src="img/campsite.jpg" alt=""/>
        </div>
      </div>
    </div>
    <div class="error" v-else-if="error">{{ error }}</div>
    <div class="loading" v-else>Loading...</div>
  </div>
</template>

<script>
import {availabilityService, campgroundService, settings} from '../services'
import QuickAvailability from './QuickAvailability.vue'
import moment from 'moment'

export default {
  components: {QuickAvailability},
  name: 'campsite-list',
  data () {
    return {
      availability: {},
      campsites: null,
      error: null
    }
  },
  mounted () {
    this.loadCampsites(settings.campgroundId)
    this.loadAvailability(settings.campgroundId)
  },
  methods: {
    loadCampsites (id) {
      campgroundService.getCampsites(id)
        .then(result => (this.campsites = result))
        .catch(reason => (this.error = reason.message))
    },
    loadAvailability (id) {
      const start = moment()
      const end = moment().add(5, 'days')
      availabilityService.getCampground(id, start, end)
        .then(result => (this.availability = availabilityService.keyByCampsiteId(result)))
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
