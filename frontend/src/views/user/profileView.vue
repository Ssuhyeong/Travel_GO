<template>
  <!-- 프로필 변경 모달 -->
  <transition name="fade" appear>
    <div
      class="modal-overlay"
      v-if="showModal"
      @click="showModal = false"></div>
  </transition>
  <transition name="pop" appear>
    <div class="modal" role="dialog" v-if="showModal">
      <h1>프로필 변경</h1>
      <textarea
        spellcheck="false"
        placeholder="변경할 이름을 입력해주세요..."
        v-model="changeName.name"
        required></textarea>
      <button @click="UpdateUser()" class="button">프로필 변경 완료</button>
    </div>
  </transition>

  <!-- 여행코스 타이틀 등록 부분 -->
  <transition name="fade" appear>
    <div
      class="modal-overlay"
      v-if="showModal_route"
      @click="showModal_route = false"></div>
  </transition>
  <transition name="pop" appear>
    <div class="modal" role="dialog" v-if="showModal_route">
      <h1>여행코스 등록</h1>
      <textarea
        spellcheck="false"
        placeholder="여행코스 타이틀을 입력해주세요"
        v-model="route_title"
        required></textarea>
      <button @click="route_regist()" class="button">여행코스 등록 이동</button>
    </div>
  </transition>

  <div class="background">
    <div id="profile_container">
      <div class="outer-div">
        <div class="inner-div">
          <div class="front">
            <div class="front__bkg-photo"></div>
            <div class="front__face-photo"></div>
            <div class="front__text">
              <h3 class="front__text-header">{{ user.name }}</h3>
              <p class="front__text-para">
                <i class="fas fa-map-marker-alt front-icons"></i
                >{{ user.email }}
              </p>

              <span class="front__text-hover">Hover For Edit</span>
            </div>
          </div>
          <div class="back">
            <div class="social-media-wrapper">
              <a @click="showModal = true" class="social-icon"
                ><font-awesome-icon
                  :icon="['fas', 'pen']"
                  size="20"
                  style="margin: 10px"
              /></a>
              <a @click="deleteUser" class="social-icon"
                ><font-awesome-icon
                  :icon="['fas', 'trash-can']"
                  size="20"
                  style="margin: 10px"
              /></a>
            </div>
          </div>
        </div>
      </div>
      <div style="margin-bottom: 23px">
        <div id="category_box">
          <div
            class="profile_category"
            :class="{ active: btn_active[0] }"
            @click="select_active(0)">
            즐겨찾기
          </div>
          <div
            class="profile_category"
            :class="{ active: btn_active[1] }"
            @click="select_active(1)">
            여행경로
          </div>
          <div
            class="profile_category"
            :class="{ active: btn_active[2] }"
            @click="select_active(2)">
            팔로우
          </div>
        </div>
        <div v-if="btn_active[0]" class="profile_content">
          <div id="route_header">
            <p id="profile_type">즐겨찾기</p>
            <font-awesome-icon
              :icon="['fass', 'plus']"
              size="2x"
              style="cursor: pointer; margin-right: 30px"
              @click="
                $router.push({
                  path: '/',
                })
              " />
          </div>
          <div id="content_container">
            <div
              class="card"
              v-for="like in like_list"
              :key="like.content_id"
              @click="
                $router.push({
                  name: 'detailpage',
                  params: { contentId: like.content_id },
                })
              ">
              <img
                :src="like.first_image"
                onerror="this.src= 'https://www.control.vg/wp-content/themes/crystalskull/img/defaults/default.jpg'" />
              <h3>{{ like.title }}</h3>
              <p>{{ like.addr1 }}</p>
            </div>
          </div>
        </div>
        <div v-if="btn_active[1]" class="profile_content">
          <div id="route_header">
            <p id="profile_type">여행경로</p>
            <font-awesome-icon
              :icon="['fass', 'plus']"
              size="2x"
              style="cursor: pointer; margin-right: 30px"
              @click="showModal_route = true" />
          </div>
          <div id="content_container">
            <div
              class="card"
              v-for="route in route_list"
              :key="route.title"
              @click="
                $router.push({
                  name: 'tripScheduleView',
                  params: {
                    title: route.title,
                    user: route.member.name,
                    scheduleInfo: route.scheduleInfo,
                  },
                })
              ">
              <img
                src="https://cdn.pixabay.com/photo/2019/05/01/22/38/plan-4172283_960_720.jpg" />
              <h3>{{ route.title }}</h3>
              <p>{{ route.member.name }}</p>
            </div>
          </div>
        </div>
        <div v-if="btn_active[2]" class="profile_content"></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "@/service/axios";
import VueCookies from "vue-cookies";
import swal from "sweetalert";

export default {
  name: "profileView",
  data() {
    return {
      user: {
        email: "",
        name: "",
      },
      changeName: {
        name: "",
      },
      updateData: {},
      like_list: [],
      route_list: [],
      showModal: false,
      showModal_route: false,
      btn_active: [true, false, false],
      route_title: "",
      scheduleInfo: 0,
    };
  },
  components: {},
  mounted() {
    axios
      .get(`http://localhost:8080/like`)
      .then((res) => {
        for (let i = 0; i < res.data.length; i++) {
          const url = `http://localhost:8080/attraction/search-list?contentId=${res.data[i]}`;
          axios.get(url).then((res) => {
            this.like_list.push(res.data.content[0]);
          });
        }
      })
      .catch((err) => {
        console.log(err);
      });

    console.log(this.like_list);

    axios.get(`http://localhost:8080/member`).then((res) => {
      console.log(res.data);

      this.user.email = res.data[0];
      this.user.name = res.data[1];
    });

    axios.get(`http://localhost:8080/travel`).then((res) => {
      this.scheduleInfo = res.data.length + 1;
      this.route_list = res.data;
    });
  },
  methods: {
    deleteUser() {
      swal({
        title: "정말 탈퇴하겠습니까?",
        text: "일단, 한번 삭제하면 되돌릴 수 없습니다!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      }).then((willDelete) => {
        if (willDelete) {
          axios.put(`http://localhost:8080/member/delete`).then(() => {
            console.log("삭제 성공");
            VueCookies.remove("accessToken");
          });
          swal("계정이 성공적으로 삭제되었습니다!", {
            icon: "success",
          });
        } else {
          swal("계정 삭제를 취소했습니다.");
        }
      });
    },
    UpdateUser() {
      this.showModal = false;
      const url = `http://localhost:8080/member/modify`;

      console.log(this.changeName.name);

      axios.put(url, this.changeName).then(() => {
        console.log("등록성공");
        this.changeName = "";
        this.$router.go(0);
      });
    },
    route_regist() {
      console.log(this.scheduleInfo);

      this.$router.push({
        name: "updateScheduleView",
        params: {
          title: this.route_title,
          scheduleInfo: this.scheduleInfo,
        },
      });
    },
    select_active(idx) {
      for (let i = 0; i < 3; i++) {
        this.btn_active[i] = false;
      }

      this.btn_active[idx] = true;
    },
  },
};
</script>

<style scoped>
h2#swal-title {
  font-family: cookierun;
}
div#swal-html-container {
  font-family: cookierun;
}

img {
  filter: brightness(50%);
}

textarea {
  width: 80%;
  resize: none;
  height: 20px;
  outline: none;
  padding: 15px;
  font-size: 16px;
  margin: 20px 0px;
  border-radius: 5px;
  max-height: 500px;
  font-family: Cookierun;
  border: 1px solid #bfbfbf;
}

textarea::placeholder {
  color: #b3b3b3;
}

textarea:is(:focus, :valid) {
  padding: 14px;
  border: 2px solid #00859c;
}

textarea::-webkit-scrollbar {
  width: 0px;
}

h1 {
  font-size: 40px;
}
.button {
  border: none;
  color: #fff;
  background: #00859c;
  appearance: none;
  font: inherit;
  font-size: 1.1rem;
  padding: 0.4em 0.6em;
  border-radius: 0.3em;
  cursor: pointer;
}

#category_box {
  display: flex;
  align-items: center;
  justify-content: right;
  height: 25px;
  margin: 0px 10px;
  padding: 0px 20px;
}

#profile_type {
  font-weight: 600;
  font-size: 24px;
  margin-left: 33px;
}

#profile_container {
  position: relative;
  height: 100vh;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 0 auto;
  z-index: 3;
}

#content_container {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  grid-gap: 2rem;
  margin: 0px 30px;
  padding: 20px;
}

#route_header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 0px;
}
.profile_category {
  background-color: #fff;
  margin: 5px 5px;
  box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px,
    rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
  padding: 3.5px 10px 0px 10px;
  border-radius: 10px 10px 0px 0px;
  z-index: 3;
}

.profile_category:hover {
  background-color: #d9ecf0;
  cursor: pointer;
}

.active {
  background-color: #d9ecf0;
}

.card {
  height: 155px;
  position: relative;
  overflow: hidden;
  border-radius: 8px;
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.25);
  display: grid;
  grid-template-rows: 1fr 1fr;
  transition: 0.8s cubic-bezier(0.2, 0.8, 0.2, 1);
  cursor: pointer;
  z-index: 2;
}

.card:hover {
  transform: scale(1.035, 1.035);
  box-shadow: 0 15px 30px rgba(0, 0, 0, 0.5);
}

.card:hover img {
  transform: translateY(-10px);
}

.card > h3 {
  color: white;
  font-size: 20px;
  margin: 20px 20px 20px 20px;
}

.card > p {
  color: white;
  font-weight: 400;
  font-size: 12px;
  align-self: end;
  margin: 20px 20px 20px 20px;
  letter-spacing: 0.5px;
}

.card > img {
  position: absolute;
  top: 0;
  height: 110%;
  width: 100%;
  z-index: -1;
  transition: 0.8s cubic-bezier(0.2, 0.8, 0.2, 1);
}

.profile_content {
  width: 800px;
  min-width: 800px;
  height: 450px;
  background-color: #fff;
  border-radius: 5px;
  margin: 0px 10px;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  overflow-y: auto;
}

.background {
  position: absolute;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100vw;
  background-size: cover;
  background: #ccc;

  background-position: 0 50%;
  background: url("https://images.unsplash.com/photo-1533035353720-f1c6a75cd8ab?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1974&q=80");
}
.background:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: rgba(0, 0, 0, 0);
}

.outer-div,
.inner-div {
  height: 450px;
  max-width: 1000px;
  position: relative;
  z-index: 9999;
}

.outer-div {
  perspective: 900px;
  perspective-origin: 50% calc(50% - 18em);
}

.inner-div {
  margin: 0 auto;
  border-radius: 5px;
  font-weight: 400;
  color: #071011;
  font-size: 1rem;
  text-align: center;
  transition: all 0.6s cubic-bezier(0.8, -0.4, 0.2, 1.7);
  transform-style: preserve-3d;
}

.social-icon {
  cursor: pointer;
}

.inner-div:hover .social-icon {
  opacity: 1;
  top: 0;
}

.outer-div:hover .inner-div {
  transform: rotateY(180deg);
}

.front,
.back {
  position: relative;
  top: 0;
  left: 0;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

.front {
  cursor: pointer;
  height: 100%;
  background: #fff;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  border-radius: 5px;
  box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3),
    0 0 40px rgba(0, 0, 0, 0.1) inset;
}

.front__bkg-photo {
  position: relative;
  height: 150px;
  width: 300px;
  background: url("https://images.unsplash.com/photo-1511207538754-e8555f2bc187?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=88672068827eaeeab540f584b883cc66&auto=format&fit=crop&w=1164&q=80")
    no-repeat;
  background-size: cover;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  overflow: hidden;
  border-top-right-radius: 5px;
  border-top-left-radius: 5px;
}
.front__bkg-photo:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}

.front__face-photo {
  position: relative;
  top: -60px;
  height: 120px;
  width: 120px;
  margin: 0 auto;
  border-radius: 50%;
  border: 5px solid #fff;
  background: url("https://images.unsplash.com/photo-1679926820639-56c6f62e516e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80")
    no-repeat;
  background-size: cover;
  overflow: hidden;
}

.front__text {
  position: relative;
  top: -55px;
  margin: 0 auto;
  font-size: 18px;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}
.front__text .front__text-header {
  font-weight: 700;
  text-transform: uppercase;
  font-size: 20px;
}
.front__text .front__text-para {
  position: relative;
  top: -5px;
  color: #000;
  font-size: 14px;
  letter-spacing: 0.4px;
  font-weight: 400;
}
.front__text .front-icons {
  position: relative;
  top: 0;
  font-size: 14px;
  margin-right: 6px;
  color: gray;
}
.front__text .front__text-hover {
  position: relative;
  top: 10px;
  font-size: 10px;
  color: tomato;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.4px;
  border: 2px solid #00859c;
  padding: 8px 15px;
  border-radius: 30px;
  background: #00859c;
  color: #fff;
}

.back {
  transform: rotateY(180deg);
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-color: #071011;
  border-radius: 5px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.social-media-wrapper {
  font-size: 36px;
}
.social-media-wrapper .social-icon {
  position: relative;
  top: 20px;
  margin-left: 5px;
  margin-right: 5px;
  opacity: 0;
  color: #fff;
  transition: all 0.4s cubic-bezier(0.3, 0.7, 0.1, 1.9);
}

.social-media-wrapper .social-icon:nth-child(1) {
  transition-delay: 0.6s;
}
.social-media-wrapper .social-icon:nth-child(2) {
  transition-delay: 0.7s;
}

.fab,
.fas {
  position: relative;
  top: 0;
  left: 0;
  transition: all 200ms ease-in-out;
}

.fab:hover,
.fas:hover {
  top: -5px;
}

.modal {
  position: absolute;
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  margin: auto;
  text-align: center;
  width: fit-content;
  height: fit-content;
  max-width: 25em;
  padding: 2rem;
  border-radius: 1rem;
  box-shadow: 0 5px 5px rgba(0, 0, 0, 0.2);
  background: #fff;
  z-index: 999;
  transform: none;
}
.modal h1 {
  margin: 0 0 1rem;
}

.modal-overlay {
  content: "";
  position: absolute;
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 998;
  background: #2c3e50;
  opacity: 0.6;
  cursor: pointer;
}

/* ---------------------------------- */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.4s linear;
}

.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.pop-enter-active,
.pop-leave-active {
  transition: transform 0.4s cubic-bezier(0.5, 0, 0.5, 1), opacity 0.4s linear;
}

.pop-enter,
.pop-leave-to {
  opacity: 0;
  transform: scale(0.3) translateY(-50%);
}
</style>
