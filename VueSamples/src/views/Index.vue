<template>
  <div>
    <h1>Demo</h1>
    <ul>
      <li v-for="item in samples">
        <sample-item v-bind:item="item"></sample-item>
      </li>
    </ul>
  </div>
</template>

<script>
import data from "../../data";
import Item from "../components/Item";

export default {
  components: {
     "sample-item":Item
  },
  data () {
    return {
      samples:[]
  }},
  created (){
    var me = this;
    me.samples = data.samples;
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
