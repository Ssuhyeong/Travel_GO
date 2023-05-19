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
      <h1>서울랜드</h1>
      <p>
        <font-awesome-icon :icon="['fas', 'star']" />
        <font-awesome-icon :icon="['fas', 'star']" />
        <font-awesome-icon :icon="['fas', 'star']" />
        <font-awesome-icon :icon="['faR', 'star-half-stroke']" />
      </p>
      <textarea></textarea>
      <button @click="showModal = false" class="button">리뷰 평가완료</button>
    </div>
  </transition>
</template>

<script>
export default {
  name: "testView",
  components: {},
  data() {
    return {
      showModal: false,
    };
  },
};
</script>

<style scoped>
.button {
  border: none;
  color: #fff;
  background: #42b983;
  appearance: none;
  font: inherit;
  font-size: 1.8rem;
  padding: 0.5em 1em;
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
  max-width: 22em;
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
