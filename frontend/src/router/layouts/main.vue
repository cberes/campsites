<script>
import NavBar from '@/components/nav-bar'

export default {
  components: { NavBar }
}
</script>

<template>
  <div>
    <NavBar/>
    <slot/>
  </div>
</template>

<style module>
</style>
