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

<style lang="scss">
@import '~normalize.css/normalize.css';
@import '~element-ui/lib/theme-chalk/index.css';
@import '~element-ui/lib/theme-chalk/display.css';

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
