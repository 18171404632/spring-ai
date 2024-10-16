<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <ChatWindows msg="Welcome to Your Vue.js App"/>
</template>

<script>
import ChatWindows from './components/ChatWindows.vue'

export default {
  name: 'App',
  components: {
    ChatWindows
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
