<template>
  <footer class="footer">
    <div class="container">
      <div class="row">
        <div class="footer-col">
          <h4>SSAFY</h4>
          <ul>
            <li><a href="#">about us</a></li>
            <li>
              <a
                href="https://www.ssafy.com/ksp/servlet/swp.content.controller.SwpContentServlet"
                target="_blank"
                >our SSAFY</a
              >
            </li>
            <li><a href="#">더 알아보기</a></li>
          </ul>
        </div>
        <div class="footer-col">
          <h4>get help</h4>
          <ul>
            <li>
              <router-link :to="{ name: 'boardView', params: { type: 'faq' } }"
                >FAQ</router-link
              >
            </li>
            <li>
              <router-link
                :to="{ name: 'boardView', params: { type: 'open-board' } }"
                >열린 게시판</router-link
              >
            </li>
            <li>
              <router-link
                :to="{ name: 'boardView', params: { type: 'board' } }"
                >공지사항</router-link
              >
            </li>
          </ul>
        </div>
        <div class="footer-col">
          <h4>Travel</h4>
          <ul>
            <li><a href="#">지역별 여행지</a></li>
            <li><a href="#">카테고리별</a></li>
            <li><a href="#">즐겨찾기</a></li>
            <li><a href="#">유명한 관광지</a></li>
          </ul>
        </div>
        <div class="footer-col">
          <h4>follow us</h4>
          <div class="social-links">
            <a href="#"><font-awesome-icon :icon="['fas', 'phone']" /></a>
            <a href="#"><font-awesome-icon :icon="['fas', 'heart']" /></a>
            <a href="#"><font-awesome-icon :icon="['fas', 'envelope']" /></a>
          </div>
          <div class="social-links">
            <a href="#"><font-awesome-icon :icon="['fas', 'phone']" /></a>
            <a href="#"><font-awesome-icon :icon="['fas', 'heart']" /></a>
            <a href="#"><font-awesome-icon :icon="['fas', 'envelope']" /></a>
          </div>
        </div>
      </div>
    </div>
  </footer>
</template>

<script>
export default {};
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.container {
  max-width: 1170px;
  margin: auto;
}
.row {
  display: flex;
  flex-wrap: wrap;
}
ul {
  list-style: none;
}
.footer {
  background-color: #24262b;
  padding: 50px 0;
}
.footer-col {
  width: 25%;
  padding: 0 15px;
}
.footer-col h4 {
  font-size: 18px;
  color: #ffffff;
  text-transform: capitalize;
  margin-bottom: 35px;
  font-weight: 500;
  position: relative;
}
.footer-col h4::before {
  content: "";
  left: 0;
  bottom: -10px;
  background-color: #e91e63;
  height: 2px;
  box-sizing: border-box;
  width: 50px;
}
.footer-col ul li:not(:last-child) {
  margin-bottom: 10px;
}
.footer-col ul li a {
  font-size: 16px;
  text-transform: capitalize;
  color: #ffffff;
  text-decoration: none;
  font-weight: 300;
  color: #bbbbbb;
  display: block;
  transition: all 0.3s ease;
}
.footer-col ul li a:hover {
  color: #ffffff;
  padding-left: 8px;
}
.footer-col .social-links a {
  display: inline-block;
  height: 40px;
  width: 40px;
  background-color: rgba(255, 255, 255, 0.2);
  margin: 0 10px 10px 0;
  text-align: center;
  line-height: 40px;
  border-radius: 50%;
  color: #ffffff;
  transition: all 0.5s ease;
}
.footer-col .social-links a:hover {
  color: #24262b;
  background-color: #ffffff;
}

/*responsive*/
@media (max-width: 767px) {
  .footer-col {
    width: 50%;
    margin-bottom: 30px;
  }
}
@media (max-width: 574px) {
  .footer-col {
    width: 100%;
  }
}
</style>
