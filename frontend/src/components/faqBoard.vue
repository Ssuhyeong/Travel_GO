<template>
  <div class="faq-container">
    <div
      class="faq"
      :class="{ active: on[idx] }"
      v-for="(faq, idx) in faq_list"
      :key="faq.articleNo">
      <h3 class="faq-title">{{ faq.subject }}</h3>
      <p class="faq-text">
        {{ faq.content }}
      </p>
      <button class="faq-toggle" @click="toggleEvent(idx)">
        <font-awesome-icon :icon="['fas', 'angle-down']" />
      </button>
    </div>
  </div>
</template>

<script>
import axios from "@/service/axios";

export default {
  data() {
    return {
      faq_list: [],
      on: [false, false, false, false],
    };
  },
  created() {
    axios.get(`http://192.168.210.61:8080/faq/search`).then((res) => {
      this.faq_list = res.data.content;
      console.log(this.faq_list);
    });
  },
  methods: {
    toggleEvent(idx) {
      this.on[idx] = !this.on[idx];
    },
  },
};
</script>

<style scoped>
.faq-container {
  max-width: 1000px;
  margin: 0 auto;
  border-radius: 10px;
  background-color: #fff;
  overflow: hidden;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 4px 12px;
}

.faq {
  box-sizing: border-box;
  background: transparent;
  padding: 30px;
  position: relative;
  overflow: hidden;
}

.faq:not(:first-child) {
  border-top: 1px solid #e6e6e6;
}

.faq-text {
  margin: 30px 0 0;
  display: none;
  line-height: 1.5rem;
}

.faq.active {
  background-color: #f8f8f8;
  box-shadow: inset 4px 0px 0px 0px #2c3e50;
}

.faq.active .faq-title {
  color: #2c3e50;
}

.faq.active .faq-text {
  display: block;
}

.faq-toggle {
  background-color: transparent;
  border: 1px solid #e6e6e6;
  color: inherit;
  border-radius: 50%;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 16px;
  padding-top: 3px;
  position: absolute;
  top: 30px;
  right: 30px;
  height: 30px;
  width: 30px;
  transition: 0.3s ease;
}

.faq-toggle:focus {
  outline: none;
}

.faq.active .faq-toggle {
  transform: rotate(180deg);
  background-color: #2c3e50;
  border-color: #2c3e50;
  color: #fff;
}
</style>
