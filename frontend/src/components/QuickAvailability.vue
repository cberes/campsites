<template>
  <ul class="quick-availability container" v-if="availability">
    <li class="availability-period" v-for="period in availability">
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
      return moment({year: d[0], month: d[1] - 1, day: d[2]}).format('YYYY-MM-DD')
    }
  }
}
</script>

<style scoped>
.loading {
  color: #555555;
}
</style>
