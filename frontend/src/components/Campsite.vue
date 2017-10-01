<template>
  <div id="campsite" class="container" v-if="campsite">
    <h1 id="name">{{ campsite.name }}</h1>
    <p id="description">{{ campsite.description }}</p>
    <img id="image" src="img/campsite.jpg" alt=""/>
    <table>
      <tr>
        <th>Size</th><td id="size">{{ campsite.size }}'</td>
      </tr>
      <tr>
        <th>Max vehicles</th><td id="vehicles">{{ campsite.maxVehicles }}</td>
      </tr>
      <tr>
        <th>Max occupancy</th><td id="occupancy">{{ campsite.maxOccupancy }}</td>
      </tr>
      <tr>
        <th>Pets allowed</th><td id="pets">{{ campsite.petsAllowed }}</td>
      </tr>
      <tr>
        <th>Electric</th><td id="power">{{ campsite.electric }}</td>
      </tr>
      <tr>
        <th>Water</th><td id="water">{{ campsite.water }}</td>
      </tr>
      <tr>
        <th>Sewer</th><td id="sewer">{{ campsite.sewer }}</td>
      </tr>
    </table>
    <h2 id="calendar">Availability</h2>
    <availability-calendar :id="campsite.id" :today="today"></availability-calendar>
    <p>
      <router-link to="/cart">Reserve this campsite</router-link>
    </p>
  </div>
  <div class="error" v-else-if="error">{{ error }}</div>
  <div class="loading" v-else>Loading...</div>
</template>

<script>
import injector from '../services/InjectionService'
import AvailabilityCalendar from './AvailabilityCalendar.vue'
import moment from 'moment'

export default {
  components: {AvailabilityCalendar},
  name: 'campsite',
  data () {
    return {
      campsite: null,
      error: null,
      today: moment().add(1, 'days').format('YYYY-MM-DD')
    }
  },
  mounted () {
    this.loadCampsite(this.$route.params.id, injector.inject('CampsiteService'))
  },
  methods: {
    loadCampsite (id, campsiteService) {
      campsiteService.getCampsite(id)
        .then(result => (this.campsite = result))
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
