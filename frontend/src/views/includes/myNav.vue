<template>
  <nav class="navbar">
    <div class="container">
      <div class="navbar-header">
        <button class="navbar-toggler" data-toggle="open-navbar1">
          <span></span>
          <span></span>
          <span></span>
        </button>

        <img
          src="../../assets/img/logo.png"
          style="width: 60px; cursor: pointer"
          alt="실패"
          @click="$router.push('/mainpage')" />
      </div>

      <div class="navbar-menu" id="open-navbar1">
        <ul class="navbar-nav">
          <li class="navbar-dropdown">
            <a class="dropdown-toggler" data-dropdown="my-dropdown-id">
              여행을 떠나요
              <font-awesome-icon :icon="['fas', 'angle-down']" />
            </a>
            <ul class="dropdown" id="my-dropdown-id">
              <li><router-link to="/">여행지 검색</router-link></li>
              <li><router-link to="/categorypage">핫플레이스</router-link></li>
              <li class="separator"></li>
              <li>
                <router-link to="/tripScheduleView">나의 여행경로</router-link>
              </li>
            </ul>
          </li>
          <li class="navbar-dropdown">
            <a class="dropdown-toggler" data-dropdown="blog">
              커뮤니티
              <font-awesome-icon :icon="['fas', 'angle-down']" />
            </a>
            <ul class="dropdown" id="blog">
              <li>
                <router-link
                  :to="{ name: 'boardView', params: { type: 'board' } }"
                  >공지사항</router-link
                >
              </li>
              <li class="separator"></li>
              <li>
                <router-link
                  :to="{ name: 'boardView', params: { type: 'open-board' } }"
                  >열린 게시판</router-link
                >
              </li>
              <li>
                <router-link
                  :to="{ name: 'boardView', params: { type: 'faq' } }"
                  >FAQ</router-link
                >
              </li>
            </ul>
          </li>
          <li><router-link to="/profilepage">프로필</router-link></li>
          <li v-if="login">
            <router-link to="/loginpage">로그인</router-link>
          </li>
          <li v-else>
            <router-link to="/loginpage" @click="logout()"
              >로그아웃</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
import VueCookies from "vue-cookies";

export default {
  data() {
    return {
      navActive: true,
      dropdownIsOpen: false,
      dropdowns: null,
      login: false,
    };
  },
  created() {
    if (VueCookies.get("accessToken")) {
      this.login = false;
    } else {
      this.login = true;
    }
  },
  methods: {
    ClickEvent(event) {
      console.log("AddClick");
      let target = null;
      target = document.querySelector(`#${event.target.dataset.dropdown}`);

      if (target) {
        if (target.classList.contains("show")) {
          target.classList.remove("show");
          this.dropdownIsOpen = false;
        } else {
          target.classList.add("show");
          this.dropdownIsOpen = true;
        }
      }
    },
    mouseEvent(event) {
      console.log("Addmouseup");
      if (this.dropdownIsOpen) {
        this.dropdowns.forEach((dropdownButton) => {
          let dropdown = document.querySelector(
            `#${dropdownButton.dataset.dropdown}`
          );
          let targetIsDropdown = dropdown == event.target;

          if (dropdownButton == event.target) {
            return;
          }

          if (!targetIsDropdown && !dropdown.contains(event.target)) {
            dropdown.classList.remove("show");
          }
        });
      }
    },
    logout() {
      VueCookies.remove("accessToken");
    },
  },
  mounted() {
    this.dropdowns = document.querySelectorAll(".navbar .dropdown-toggler");

    // Handle dropdown menues
    if (this.dropdowns.length) {
      this.dropdowns.forEach((dropdown) => {
        dropdown.addEventListener("click", this.ClickEvent);
      });
    }

    window.addEventListener("mouseup", this.mouseEvent);

    // Open links in mobiles
    function handleSmallScreens() {
      document
        .querySelector(".navbar-toggler")
        .addEventListener("click", () => {
          let navbarMenu = null;
          navbarMenu = document.querySelector(".navbar-menu");

          if (!navbarMenu.classList.contains("active")) {
            navbarMenu.classList.add("active");
          } else {
            navbarMenu.classList.remove("active");
          }
        });
    }

    handleSmallScreens();
  },
  unmounted() {
    // Handle dropdown menues
    if (this.dropdowns.length) {
      this.dropdowns.forEach((dropdown) => {
        dropdown.removeEventListener("click", this.ClickEvent);
      });
    }

    window.removeEventListener("mouseup", this.mouseEvent);
  },
};
</script>

<style scoped>
a {
  text-decoration: none;
  text-align: center;
}

ul {
  padding: 0px;
}

ul > li:hover {
  color: #00859c;
}

.container {
  width: 1170px;
  position: relative;
  margin-left: auto;
  margin-right: auto;
  padding-left: 15px;
  padding-right: 15px;
}

.navbar,
.navbar > .container {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  justify-content: space-between;
}
@media (max-width: 768px) {
  .navbar,
  .navbar > .container {
    display: block;
  }
}

.dropdown-toggler {
  cursor: pointer;
}

.navbar {
  box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
  background-color: #fff;
  padding: 0.5rem 1.15rem;
  border-bottom: 1px solid #eceef3;
}
@media (min-width: 576px) {
  .navbar .container {
    max-width: 540px;
  }
}
@media (min-width: 768px) {
  .navbar .container {
    max-width: 720px;
  }
}
@media (min-width: 992px) {
  .navbar .container {
    max-width: 960px;
  }
}
@media (min-width: 1200px) {
  .navbar .container {
    max-width: 1140px;
  }
}
.navbar .navbar-header {
  display: flex;
  align-items: center;
}
@media (max-width: 768px) {
  .navbar .navbar-header {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-direction: row-reverse;
  }
}
.navbar .navbar-header .navbar-toggler {
  cursor: pointer;
  border: none;
  display: none;
  outline: none;
}
@media (max-width: 768px) {
  .navbar .navbar-header .navbar-toggler {
    display: block;
  }
}
.navbar .navbar-header .navbar-toggler span {
  height: 2px;
  width: 22px;
  background-color: #929aad;
  display: block;
}
.navbar .navbar-header .navbar-toggler span:not(:last-child) {
  margin-bottom: 0.2rem;
}
.navbar .navbar-header > a {
  font-weight: 500;
  color: #3c4250;
}
.navbar .navbar-menu {
  display: flex;
  align-items: center;
  flex-basis: auto;
  flex-grow: 1;
}
@media (max-width: 768px) {
  .navbar .navbar-menu {
    display: none;
    text-align: center;
  }
}
.navbar .navbar-menu.active {
  display: flex !important;
}
.navbar .navbar-menu .navbar-nav {
  margin-left: auto;
  flex-direction: row;
  display: flex;
  padding-left: 0;
  margin-bottom: 0;
  margin-top: 0;
  list-style: none;
}
@media (max-width: 768px) {
  .navbar .navbar-menu .navbar-nav {
    width: 100%;
    display: block;
    border-top: 1px solid #eee;
  }
}
.navbar .navbar-menu .navbar-nav > li > a {
  color: #3c4250;
  text-decoration: none;
  display: inline-block;
  padding: 0.5rem 1rem;
}
.navbar .navbar-menu .navbar-nav > li > a:hover {
  color: #00859c;
}
@media (max-width: 768px) {
  .navbar .navbar-menu .navbar-nav > li > a {
    border-bottom: 1px solid #eceef3;
  }
}
.navbar .navbar-menu .navbar-nav > li.active a {
  color: #00859c;
}
.navbar .navbar-menu .navbar-nav .navbar-dropdown .dropdown {
  list-style: none;
  position: absolute;
  top: 150%;
  left: 0;
  background-color: #fff;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  min-width: 160px;
  width: auto;
  white-space: nowrap;
  box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.1);
  z-index: 99999;
  border-radius: 0.75rem;
  display: none;
}
@media (max-width: 768px) {
  .navbar .navbar-menu .navbar-nav .navbar-dropdown .dropdown {
    position: relative;
    box-shadow: none;
  }
}
.navbar .navbar-menu .navbar-nav .navbar-dropdown .dropdown li a {
  color: #3c4250;
  padding: 0.25rem 1rem;
  display: block;
}
.navbar .navbar-menu .navbar-nav .navbar-dropdown .dropdown.show {
  display: block !important;
}
.navbar .navbar-menu .navbar-nav .dropdown > .separator {
  height: 1px;
  width: 100%;
  margin-top: 9px;
  margin-bottom: 9px;
  background-color: #eceef3;
}
.navbar .navbar-dropdown {
  position: relative;
}

.navbar .navbar-header > a span {
  color: #00859c;
}

.navbar .navbar-header h4 {
  font-weight: 500;
  font-size: 1.25rem;
}
@media (max-width: 768px) {
  .navbar .navbar-header h4 {
    font-size: 1.05rem;
  }
}
</style>
