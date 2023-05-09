<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
export default {
  name: "App",
  component: {},
};
</script>

<style>
@font-face {
  font-family: "CookieRun_Regular";
  src: url("../src/assets/fonts/CookieRun\ Regular.ttf");
}

@font-face {
  font-family: "CookieRun_Bold";
  src: url("../src/assets/fonts/CookieRun\ Regular.ttf");
}

@font-face {
  font-family: "CookieRun_Black";
  src: url("../src/assets/fonts/CookieRun\ Regular.ttf");
}

body {
  margin: 0px;
  padding: 0px;
  overflow-x: hidden;
}

*::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  background-color: #f5f5f5;
}

*::-webkit-scrollbar {
  width: 8px;
  background-color: #f5f5f5;
}

*::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #888888;
}

#app {
  font-family: "CookieRun_Regular";
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* text-align: center; */
  color: #2c3e50;
}
</style>
