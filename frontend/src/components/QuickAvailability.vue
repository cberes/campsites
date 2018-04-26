<template>
  <ul class="quick-availability container" v-if="availability">
    <li class="availability-period" :key="period.date" v-for="period in availability">
      {{ formatDate(period.date) }}: {{ period.status }}
    </li>
  </ul>
  <div class="loading" v-else>Loading...</div>
</template>

<script>
import moment from 'moment'

export default {
  name: 'quick-availability',
  props: ['availability'],
  methods: {
    formatDate (d) {
      return moment(d, 'Y-MM-DD').format('MMM D')
    }
  }
}
</script>

<style scoped>
.loading {
  color: #555555;
}
</style>
