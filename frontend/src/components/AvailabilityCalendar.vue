<template>
  <ul id="availability" class="container" v-if="availability">
    <li v-for="period in availability">
      {{ formatDate(period.date) }}: {{ period.status }}
    </li>
  </ul>
  <div class="error" v-else-if="error">{{ error }}</div>
  <div class="loading" v-else>Loading...</div>
</template>

<script>
import injector from '../services/InjectionService'
import moment from 'moment'

export default {
  name: 'availability-calendar',
  props: ['id', 'today'],
  data () {
    return {
      availability: null,
      error: null
    }
  },
  mounted () {
    this.loadAvailability(this.id, this.today, injector.inject('AvailabilityService'))
  },
  methods: {
    loadAvailability (campsiteId, startDate, availabilityService) {
      const start = moment(startDate, 'YYYY-MM-DD')
      const end = start.clone().add(14, 'days')
      availabilityService.getCampsite(campsiteId, start, end)
        .then(result => (this.availability = result.campsites[0].availability))
        .catch(reason => (this.error = reason.message))
    },
    formatDate (d) {
      return moment({year: d[0], month: d[1] - 1, day: d[2]}).format('YYYY-MM-DD')
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
