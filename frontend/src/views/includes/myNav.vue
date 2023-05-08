<template>
  <nav v-bind:class="{ sticky: navActive }">
    <div class="nav-content">
      <div class="logo">
        <a
          ><img
            src="../../assets/img/logo.png"
            style="width: 60px; cursor: pointer"
            alt="실패"
            @click="$router.push('/mainpage')"
        /></a>
      </div>

      <ul class="nav-links">
        <li><router-link to="/loginpage">로그인</router-link></li>
        <li><router-link to="/">여행지 검색</router-link></li>
        <li><router-link to="/boardpage">게시판</router-link></li>
        <li><router-link to="/profilepage">프로필</router-link></li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
  data() {
    return {
      navActive: true,
    };
  },
  mounted() {
    window.scrollTo(0, 0);
    document.addEventListener("scroll", this.scrollEvents);
  },
  unmounted() {
    document.removeEventListener("scroll", this.scrollEvents);
  },
  scrollEvents() {
    window.onscroll = function () {
      if (document.documentElement.scrollTop > 20) {
        this.navActive = true;
      } else {
        this.navActive = false;
      }
    };
  },
};
</script>

<style scoped>
nav {
  /* position: absolute; */
  z-index: 1;
  top: 0;
  left: 0;
  width: 100%;
  padding: 20px;
  transition: all 0.4s ease;
  height: 3.5em;
}
nav.sticky {
  padding: 5px 10px;
  background: #fff;
  box-shadow: 0 5px 10px rgba(0, 0, 0, 0.1);
}
nav .nav-content {
  max-width: 1200px;
  margin: auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

nav.sticky .logo a {
  color: #353842;
}

.nav-content .nav-links {
  display: flex;
}

.nav-content .nav-links li {
  list-style: none;
  margin: 0 8px;
}

.nav-links li a {
  text-decoration: none;
  color: #2c3e50;
  font-size: 18px;
  font-weight: 500;
  padding: 10px 4px;
  transition: all 0.3 ease;
}

.nav-links li a:hover {
  color: #0097b2;
}

.nav.sticky .nav-links li a {
  color: #fff;
  transition: all 0.4 ease;
}

.nav.sticky .nav-links li a:hover {
  color: #0e2431;
}
</style>
