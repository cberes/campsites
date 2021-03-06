<script>
import moment from 'moment'
import injector from '../services/InjectionService'
import Calendar from './Calendar.vue'

export default {
  name: 'availability-calendar',
  props: {
    id: [String, Number],
    today: {
      type: Object,
      default: () => moment().add({days: 1}).month() > moment().month() ? moment().add({days: 1}) : moment()
    }
  },
  components: {Calendar},
  data () {
    return {
      currentMonth: this.today.date(1),
      availability: null,
      availabilityCache: {}
    }
  },
  mounted () {
    this.loadAvailability(this.id, this.currentMonth, injector.inject('AvailabilityService'))
  },
  methods: {
    loadAvailability (campsiteId, firstOfMonth, availabilityService) {
      const key = firstOfMonth.format('Y-MM-DD')
      if (this.availabilityCache[key]) {
        this.availability = this.availabilityCache[key]
      } else {
        this.loadNewAvailability(campsiteId, firstOfMonth, availabilityService)
          .then(result => (this.availabilityCache[key] = result))
          .catch(console.log)
      }
    },
    loadNewAvailability (campsiteId, firstOfMonth, availabilityService) {
      const start = moment.max(moment(firstOfMonth), this.today)
      const end = firstOfMonth.clone().add({months: 1})
      if (start.isBefore(end)) {
        return availabilityService.getCampsite(campsiteId, start, end)
          .then(result => (this.availability = result.campsites[0].availability))
      } else {
        this.availability = []
        return Promise.reject(new Error('past availability is unavailable'))
      }
    },
    formatDate (d) {
      return moment(d, 'Y-MM-DD').format('MMM D')
    },
    nextMonth () {
      this.currentMonth = moment(this.currentMonth).add({months: 1})
      this.loadAvailability(this.id, this.currentMonth, injector.inject('AvailabilityService'))
    },
    previousMonth () {
      this.currentMonth = moment(this.currentMonth).subtract({months: 1})
      this.loadAvailability(this.id, this.currentMonth, injector.inject('AvailabilityService'))
    },
    mergeStatus (acc, value) {
      acc[value.date] = {
        cssClass: this.cssClass(value.status),
        value: value.status.charAt(0)
      }
      return acc
    },
    cssClass (status) {
      switch (status) {
        case 'FIRST_COME_FIRST_SERVE':
          return 'warning'
        case 'IN_PERSON_ONLY':
          return 'idle'
        case 'AVAILABLE':
          return 'success'
        default:
          return ''
      }
    }
  },
  computed: {
    statusByDay () {
      return (this.availability || []).reduce(this.mergeStatus, {})
    }
  }
}
</script>

<template>
  <div>
    <el-button style="float:left" icon="el-icon-arrow-left" circle @click="previousMonth"></el-button>
    <calendar style="float:left" :month="currentMonth.month()" :year="currentMonth.year()" :info="statusByDay"></calendar>
    <el-button style="float:left" icon="el-icon-arrow-right" circle @click="nextMonth"></el-button>
    <div style="clear:left"></div>
  </div>
</template>

<style scoped>
button, button:active, button:focus {
  outline: none;
  margin-top: 10em;
}
</style>
