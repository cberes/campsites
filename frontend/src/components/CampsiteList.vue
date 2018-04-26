<template>
  <div class="container">
    <h1>Campsite list</h1>
    <el-container v-if="campsites" direction="vertical">
      <el-row :key="campsite.id" v-for="campsite in campsites">
        <el-col :span="8">
          <router-link class="name" :to="{ name: 'campsite', params: { id: campsite.id }}">{{ campsite.name }}</router-link>
          <quick-availability :availability="availability[campsite.id]"></quick-availability>
        </el-col>
        <el-col :span="8">
          <ul>
            <li class="size">{{ campsite.size }}'</li>
            <li class="electric">{{ campsite.electric }}</li>
            <li class="vehicles">Max {{ campsite.maxVehicles }} vehicle(s)</li>
            <li class="occupancy">Max {{ campsite.maxOccupancy }} guests</li>
            <li class="pets">Max {{ campsite.petsAllowed }} pets</li>
          </ul>
        </el-col>
        <el-col :span="8">
          <img class="small" src="img/campsite.jpg" alt="Pic"/>
        </el-col>
      </el-row>
    </el-container>
    <el-container class="error" v-else-if="error">{{ error }}</el-container>
    <el-container class="loading" v-else>Loading...</el-container>
  </div>
</template>

<script>
import injector from '../services/InjectionService'
import AvailabilityService from '../services/AvailabilityService'
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
    this.loadCampsites(injector.inject('settings'), injector.inject('CampgroundService'))
    this.loadAvailability(injector.inject('settings'), injector.inject('AvailabilityService'))
  },
  methods: {
    loadCampsites (settings, campgroundService) {
      campgroundService.getCampsites(settings.campgroundId)
        .then(result => (this.campsites = result))
        .catch(reason => (this.error = reason.message))
    },
    loadAvailability (settings, availabilityService) {
      const start = moment().add(1, 'days')
      const end = start.clone().add(5, 'days')
      availabilityService.getCampground(settings.campgroundId, start, end)
        .then(result => (this.availability = AvailabilityService.keyByCampsiteId(result)))
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
