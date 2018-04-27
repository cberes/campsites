<script>
import Footer from '@/components/footer'
import Header from '@/components/header'
import NavBar from '@/components/nav-bar'

export default {
  components: { Footer, Header, NavBar },
  props: {
    title: {
      type: String,
      default: ''
    }
  }
}
</script>

<template>
  <el-container>
    <el-header>
      <Header :title="title"></Header>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <NavBar/>
      </el-aside>
      <el-container>
        <el-main>
          <slot/>
        </el-main>
        <el-footer>
          <Footer></Footer>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<style module>
</style>
