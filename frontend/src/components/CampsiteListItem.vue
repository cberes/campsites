<script>
import QuickAvailability from './QuickAvailability'

export default {
  components: {QuickAvailability},
  name: 'campsite-list-item',
  props: ['campsite', 'availability']
}
</script>

<template>
  <el-row>
    <el-col :span="8">
      <router-link class="name" :to="{ name: 'campsite', params: { id: campsite.id }}">{{ campsite.name }}</router-link>
      <quick-availability :availability="availability"></quick-availability>
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
</template>

<style scoped>
</style>
