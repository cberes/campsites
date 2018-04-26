<script>
export default {
  name: 'app'
}
</script>

<template>
  <div id="app">
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
