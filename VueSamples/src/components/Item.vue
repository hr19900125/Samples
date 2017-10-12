<template>
  <div>
   <a v-bind:href="item.href">{{item.name}}</a>
 </div>
</template>

<script>
  export default {
    props:["item"]
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  a {
    color: #42b983;
    display: block;
  }
</style>
