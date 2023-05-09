<template>
  <div class="toast">
    <div class="toast-content">
      <font-awesome-icon :icon="['fas', 'check']" size="xs" class="check" />

      <div class="message">
        <span class="text text-1">Success</span>
        <span class="text text-2">{{ message }}</span>
      </div>
    </div>
    <font-awesome-icon :icon="['fas', 'x']" size="xs" class="close" />

    <div class="progress"></div>
  </div>
</template>

<script>
import { useStore } from "vuex";

export default {
  name: "toastNotice",
  data() {
    return {
      toastColor: "",
    };
  },
  props: {
    message: {
      type: String,
    },
  },
  mounted() {
    const store = useStore();

    this.toastColor = store.state.toastcolor;

    const toast = document.querySelector(".toast");
    const closeIcon = document.querySelector(".close");
    const progress = document.querySelector(".progress");

    let timer1, timer2;

    toast.classList.add("active");
    progress.classList.add("active");

    timer1 = setTimeout(() => {
      toast.classList.remove("active");
    }, 5000); //1s = 1000 milliseconds

    timer2 = setTimeout(() => {
      progress.classList.remove("active");
    }, 5300);

    closeIcon.addEventListener("click", () => {
      toast.classList.remove("active");

      setTimeout(() => {
        progress.classList.remove("active");
      }, 5300);

      clearTimeout(timer1);
      clearTimeout(timer2);
    });

    store.commit("setShow", false);
  },
};
</script>

<style scoped>
.toast {
  position: absolute;
  top: 25px;
  right: 30px;
  border-radius: 10px;
  background: #fff;
  padding: 20px 35px 20px 25px;
  border-left: 4px solid v-bind(toastColor);
  border-top: 4px solid v-bind(toastColor);
  border-right: 4px solid v-bind(toastColor);
  overflow: hidden;
  transform: translateX(calc(100% + 30px));
  transition: all 0.5s cubic-bezier(0.68, -0.55, 0.265, 1.35);
}

.toast.active {
  transform: translateX(0%);
}

.toast .toast-content {
  display: flex;
  align-items: center;
}

.toast-content .check {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 35px;
  width: 35px;
  background-color: v-bind(toastColor);
  color: #fff;
  font-size: 20px;
  border-radius: 5px;
}

.toast-content .message {
  display: flex;
  flex-direction: column;
  margin: 0 20px;
}

.message .text {
  font-size: 20px;
  font-weight: 400;
  color: #666666;
}

.message .text.text-1 {
  font-weight: 600;
  color: #333;
}

.toast .close {
  position: absolute;
  top: 10px;
  right: 15px;
  padding: 5px;
  cursor: pointer;
  opacity: 0.7;
}

.toast .close:hover {
  opacity: 1;
}

.toast .progress {
  position: absolute;
  bottom: 0;
  left: 0;
  height: 4px;
  width: 100%;
  background: #ddd;
}

.toast .progress:before {
  content: "";
  position: absolute;
  bottom: 0;
  right: 0;
  height: 100%;
  width: 100%;
  background-color: v-bind(toastColor);
}

.progress.active:before {
  animation: progress 5s linear forwards;
}

@keyframes progress {
  100% {
    right: 100%;
  }
}
</style>
