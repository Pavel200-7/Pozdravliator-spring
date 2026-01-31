<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <div>
      <div class="topnav">
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/friends">Friends</RouterLink>
        <RouterLink to="/subscribers">Subscribers</RouterLink>

      </div> 
    </div>
    
  </header>
  <div>
      <RouterView />
  </div>

</template>

<style scoped>
.topnav {
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: auto;
  border-bottom: 1pt solid rgba(129, 121, 121, 0.171);
}

.topnav a {
  float: left;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
  font-size: 17px;
}

.topnav a.active {
  background-color: #4CAF50;
  color: white;
}
</style>
