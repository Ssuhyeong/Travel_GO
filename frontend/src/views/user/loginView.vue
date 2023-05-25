<template>
  <toastNotice :message="toastText" v-if="toastShow" />

    <!-- 여행코스 타이틀 등록 부분 -->
    <transition name="fade" appear>
    <div
      class="modal-overlay"
      v-if="showModal_find"
      @click="showModal_find = false"></div>
  </transition>
  <transition name="pop" appear>
    <div class="modal" role="dialog" v-if="showModal_find">
      <h1>비밀번호 변경</h1>
      <textarea
        spellcheck="false"
        placeholder="찾으시려는 아이디를 입력해주세요"
        v-model="change_data.email"
        required></textarea>
        <textarea
        spellcheck="false"
        placeholder="변경하시려는 비밀번호를 입력해주세요"
        v-model="change_data.password"
        required></textarea>
      <button @click="find_password()" class="button">비밀번호 변경완료</button>
    </div>
  </transition>

  <div class="login_body">
    <!-- partial:index.partial.html -->
    <div class="container">
      <!-- Sign Up -->
      <div class="container__form container--signup">
        <form class="form" id="form1">
          <h2 class="form__title">Sign Up</h2>
          <input
            type="text"
            placeholder="Name"
            class="input"
            name="name"
            v-model="user_data.name" />
          <input
            type="email"
            placeholder="Id"
            class="input"
            name="email"
            v-model="user_data.email" />
          <input
            type="password"
            placeholder="Password"
            class="input"
            name="password"
            v-model="user_data.password" />
          <button class="btn" @click="signup()">Sign Up</button>
        </form>
      </div>

      <!-- Sign In -->
      <div class="container__form container--signin">
        <form action="#" class="form" id="form2">
          <h2 class="form__title">Sign In</h2>
          <input
            type="email"
            placeholder="Email"
            class="input"
            v-model="login_data.email" />
          <input
            type="password"
            placeholder="Password"
            class="input"
            v-model="login_data.password" />
          <a @click="showModal_find = true" style="cursor: pointer;">Forgot your password?</a>
          <button class="btn" @click="signin()">Sign In</button>
        </form>
      </div>

      <!-- Overlay -->
      <div class="container__overlay">
        <div class="overlay">
          <div class="overlay__panel overlay--left">
            <button class="btn" id="signIn">Sign In</button>
          </div>
          <div class="overlay__panel overlay--right">
            <button class="btn" id="signUp">Sign Up</button>
          </div>
        </div>
      </div>
    </div>
    <vue-basic-alert :duration="1500" :closeIn="1500" ref="alert" />
  </div>
</template>

<script>
import toastNotice from "@/components/toastNotice.vue";
import axios from "@/service/axios";
import VueCookies from "vue-cookies";
import { useStore } from "vuex";
import VueBasicAlert from "vue-basic-alert";

export default {
  name: "loginView",
  components: {
    toastNotice,
    VueBasicAlert,
  },
  data() {
    return {
      user_data: {
        name: "",
        email: "",
        password: "",
      },
      login_data: {
        email: "",
        password: "",
      },
      toastShow: false,
      toastText: "",
      showModal_find:false,
      change_data: {
        email: "",
        password: "",
      },
    };
  },
  setup() {
    const store = useStore();
    const setSuccessColor = () => store.commit("setColor", "#0e4bf1");
    const setFailColor = () => store.commit("setColor", "#f44040");

    return { setSuccessColor, setFailColor };
  },
  mounted() {
    const signInBtn = document.getElementById("signIn");
    const signUpBtn = document.getElementById("signUp");
    const fistForm = document.getElementById("form1");
    const secondForm = document.getElementById("form2");
    const container = document.querySelector(".container");

    signInBtn.addEventListener("click", () => {
      container.classList.remove("right-panel-active");
    });

    signUpBtn.addEventListener("click", () => {
      container.classList.add("right-panel-active");
    });

    fistForm.addEventListener("submit", (e) => e.preventDefault());
    secondForm.addEventListener("submit", (e) => e.preventDefault());
  },
  methods: {
    signin() {
      const url = `http://localhost:8080/login`;
      console.log(this.login_data);

      axios
        .post(url, this.login_data)
        .then((res) => {
          VueCookies.set("accessToken", res.data.accessToken);
          VueCookies.set("refreshToken", res.data.refreshToken);

          this.$router.push({
            path: "/mainpage",
          });
        })
        .catch(() => {
          this.$refs.alert.showAlert(
            "error",
            "올바르지 않은 이메일 또는 비밀번호입니다.",
            "로그인 실패"
          );
        });
    },
    signup() {
      console.log(this.user_data);
      const url = `http://localhost:8080/member/sign-up`;

      if(this.user_data.name != "" && this.user_data.email != "" && this.user_data.password != "") {
        axios
        .post(url, this.user_data)
        .then(() => {
          // this.setSuccessColor();
          // this.toastShow = true;
          // this.toastText = "성공적으로 회원가입하셨습니다.";
          // this.user_data.email = "";
          // this.user_data.name = "";
          // this.user_data.password = "";
          this.$refs.alert.showAlert(
            "success",
            "회원가입에 성공하셨습니다!",
            "회원가입 성공"
          );
        })
        .catch(() => {
          // console.log(err);
          // this.setFailColor();
          // this.toastShow = true;
          // this.toastText = "회원가입에 실패하셨습니다. 다시 입력해주세요";
          // this.user_data.email = "";
          // this.user_data.name = "";
          // this.user_data.password = "";
          this.$refs.alert.showAlert(
            "error",
            "이미 등록되어 있는 가입자입니다.",
            "회원가입 실패"
          );
        });
      }
      else {
        this.$refs.alert.showAlert(
            "error",
            "필수적인 입력데이터를 작성해주세요",
            "입력 폼 작성 문제"
          );
      }
      
    },
    find_password() {
      console.log(this.change_data)
      axios.post(`http://localhost:8080/member/change`, this.change_data).then(() => {
        this.$refs.alert.showAlert(
            "success",
            "비밀번호 변경에 성공하셨습니다!",
            "비밀번호 변경 성공"
          );
          this.showModal_find = false;
      }).catch(() => {
        this.$refs.alert.showAlert(
            "error",
            "찾으시는 이메일이 없습니다.",
            "비밀번호 변경 실패"
          );
      })
    }
  },
  watch: {
    toastShow() {
      this.toastShow = !this.toastShow;
    },
  },
};
</script>

<style scoped>
.login_body {
  align-items: center;
  background-color: #e9e9e9;
  background: url("https://images.unsplash.com/reserve/91JuTaUSKaMh2yjB1C4A_IMG_9284.jpg?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80");
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  display: grid;
  height: 100vh;
  place-items: center;
}

.form__title {
  font-weight: 300;
  margin: 0;
  margin-bottom: 1.25rem;
}

.link {
  color: #333;
  font-size: 0.9rem;
  margin: 1.5rem 0;
  text-decoration: none;
}

.container {
  background-color: #e9e9e9;
  border-radius: 0.7rem;
  box-shadow: 0 0.9rem 1.7rem rgba(0, 0, 0, 0.25),
    0 0.7rem 0.7rem rgba(0, 0, 0, 0.22);
  height: 420px;
  max-width: 758px;
  overflow: hidden;
  position: relative;
  width: 100%;
}

.container__form {
  height: 100%;
  position: absolute;
  top: 0;
  transition: all 0.6s ease-in-out;
}

.container--signin {
  left: 0;
  width: 50%;
  z-index: 2;
}

.container.right-panel-active .container--signin {
  transform: translateX(100%);
}

.container--signup {
  left: 0;
  opacity: 0;
  width: 50%;
  z-index: 1;
}

.container.right-panel-active .container--signup {
  -webkit-animation: show 0.6s;
  animation: show 0.6s;
  opacity: 1;
  transform: translateX(100%);
  z-index: 5;
}

.container__overlay {
  height: 100%;
  left: 50%;
  overflow: hidden;
  position: absolute;
  top: 0;
  transition: transform 0.6s ease-in-out;
  width: 50%;
  z-index: 100;
}

.container.right-panel-active .container__overlay {
  transform: translateX(-100%);
}

.overlay {
  background-color: #008997;
  background: url("https://images.unsplash.com/reserve/91JuTaUSKaMh2yjB1C4A_IMG_9284.jpg?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80");
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  height: 100%;
  left: -100%;
  position: relative;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
  width: 200%;
}

.container.right-panel-active .overlay {
  transform: translateX(50%);
}

.overlay__panel {
  align-items: center;
  display: flex;
  flex-direction: column;
  height: 100%;
  justify-content: center;
  position: absolute;
  text-align: center;
  top: 0;
  transform: translateX(0);
  transition: transform 0.6s ease-in-out;
  width: 50%;
}

.overlay--left {
  transform: translateX(-20%);
}

.container.right-panel-active .overlay--left {
  transform: translateX(0);
}

.overlay--right {
  right: 0;
  transform: translateX(0);
}

.container.right-panel-active .overlay--right {
  transform: translateX(20%);
}

.btn {
  background-color: #0367a6;
  background-image: linear-gradient(90deg, #0367a6 0%, #008997 74%);
  border-radius: 20px;
  border: 1px solid #0367a6;
  color: #e9e9e9;
  cursor: pointer;
  font-size: 0.8rem;
  font-weight: bold;
  letter-spacing: 0.1rem;
  padding: 0.9rem 4rem;
  text-transform: uppercase;
  transition: transform 80ms ease-in;
}

.form > .btn {
  margin-top: 1.5rem;
}

.btn:active {
  transform: scale(0.95);
}

.btn:focus {
  outline: none;
}

.form {
  background-color: #e9e9e9;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  padding: 0 3rem;
  height: 100%;
  text-align: center;
}

.input {
  background-color: #fff;
  border: none;
  padding: 0.9rem 0.9rem;
  margin: 0.5rem 0;
  width: 100%;
}

@-webkit-keyframes show {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
}

@keyframes show {
  0%,
  49.99% {
    opacity: 0;
    z-index: 1;
  }

  50%,
  100% {
    opacity: 1;
    z-index: 5;
  }
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

textarea {
  width: 80%;
  resize: none;
  height: 20px;
  outline: none;
  padding: 15px;
  font-size: 16px;
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
  font-size: 32px;
}
.button {
  border: none;
  color: #fff;
  background: #00859c;
  appearance: none;
  font: inherit;
  font-size: 1.1rem;
  padding: 0.4em 0.8em;
  border-radius: 0.3em;
  cursor: pointer;
  margin-top: 20px
}

</style>
