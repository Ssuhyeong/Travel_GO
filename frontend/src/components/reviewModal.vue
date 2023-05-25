<template>
  <button @click="showModal = true" class="button">리뷰 작성</button>
  <transition name="fade" appear>
    <div
      class="modal-overlay"
      v-if="showModal"
      @click="showModal = false"></div>
  </transition>
  <transition name="pop" appear>
    <div class="modal" role="dialog" v-if="showModal">
      <h1>{{ $route.params.title }}</h1>
      <p>
        <starComponentVue @setScore="setScore" />
      </p>
      <textarea
        spellcheck="false"
        placeholder="Type something here..."
        required
        v-model="review.content"></textarea>
      <button @click="review_regist" class="button">리뷰 평가완료</button>
    </div>
  </transition>
</template>

<script>
import starComponentVue from "./starComponent.vue";
import axios from "@/service/axios";

export default {
  name: "testView",
  components: { starComponentVue },
  data() {
    return {
      showModal: false,
      review: {
        content: "",
        star: 0,
      },
    };
  },
  methods: {
    setScore(value) {
      this.review.star = value;
    },
    review_regist() {
      this.showModal = false;
      axios
        .post(
          `http://192.168.210.61:8080/review?attractionId=${this.$route.params.contentId}`,
          this.review
        )
        .then(() => {
          console.log("등록성공");
          this.review.content = "";
          this.$router.go(0);
        });
      console.log(this.review);
    },
  },
};
</script>

<style scoped>
textarea {
  width: 90%;
  resize: none;
  height: 100px;
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
