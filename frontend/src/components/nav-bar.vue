<script>
export default {
  data () {
    return {
      demo: window.location.hostname === 'awayfromho.me'
    }
  }
}
</script>

<template>
  <el-menu default-active="1">
    <el-menu-item index="1">
      <i class="el-icon-location"></i>
      <router-link to="/">Campground Home</router-link>
    </el-menu-item>
    <el-menu-item index="2">
      <i class="el-icon-tickets"></i>
      <router-link to="/campsites">Campsite List</router-link>
    </el-menu-item>
    <el-menu-item index="3">
      <i class="el-icon-picture"></i>
      <router-link to="/map">Campsite Map</router-link>
    </el-menu-item>
    <el-menu-item index="4" v-if="demo">
      <i class="el-icon-edit"></i>
      <a href="/blog/">Development Blog</a>
    </el-menu-item>
  </el-menu>
</template>

<style module>
</style>
