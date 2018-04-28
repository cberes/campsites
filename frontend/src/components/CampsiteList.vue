<script>
import injector from '../services/InjectionService'
import AvailabilityService from '../services/AvailabilityService'
import CampsiteListItem from './CampsiteListItem'
import moment from 'moment'

export default {
  components: {CampsiteListItem},
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

<template>
  <div class="container">
    <h1>Campsite list</h1>
    <el-container v-if="campsites" direction="vertical">
      <campsite-list-item
        :key="campsite.id"
        :campsite="campsite"
        :availability="availability[campsite.id]"
        v-for="campsite in campsites"></campsite-list-item>
    </el-container>
    <el-container class="error" v-else-if="error">{{ error }}</el-container>
    <el-container class="loading" v-else>Loading...</el-container>
  </div>
</template>

<style scoped>
.loading {
  color: #555555;
}

.error {
  color: #660000;
}
</style>
