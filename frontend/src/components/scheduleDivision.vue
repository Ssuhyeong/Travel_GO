<template>
  <div class="steps" id="steps">
    <template v-for="idx in active_count" :key="idx">
      <button
        :class="{ active: content_active[idx - 1] }"
        @click="select(idx - 1)">
        {{ idx }}일차
      </button>
      <div v-if="idx != active_count"></div>
    </template>
  </div>

  <div class="contents" id="contents">
    <div class="content" :class="{ active: content_active[0] }" data-step="1">
      <div class="content__box">
        <timeLine :list_data="list_data[1]" />
      </div>
    </div>
    <div class="content" :class="{ active: content_active[1] }" data-step="2">
      <div class="content__box">
        <timeLine :list_data="list_data[2]" />
      </div>
    </div>
    <div class="content" :class="{ active: content_active[2] }" data-step="3">
      <div class="content__box">
        <timeLine :list_data="list_data[3]" />
      </div>
    </div>
    <div class="content" :class="{ active: content_active[3] }" data-step="4">
      <div class="content__box">
        <timeLine :list_data="list_data[4]" />
      </div>
    </div>
    <div class="content" :class="{ active: content_active[4] }" data-step="5">
      <div class="content__box"><timeLine :list_data="list_data[5]" /></div>
    </div>
    <div class="content" :class="{ active: content_active[5] }" data-step="6">
      <div class="content__box"><timeLine :list_data="list_data[6]" /></div>
    </div>
    <div class="content" :class="{ active: content_active[6] }" data-step="7">
      <div class="content__box"><timeLine :list_data="list_data[7]" /></div>
    </div>
  </div>
</template>

<script>
import timeLine from "./timeLine.vue";

export default {
  name: "scheduleDivision",
  props: {
    list_data: {
      type: Array,
    },
    day_cnt: {
      type: Number,
      default: 1,
    },
  },
  data() {
    return {
      content_active: [true, false, false, false, false, false],
      active_count: 1,
    };
  },
  components: { timeLine },
  methods: {
    select(idx) {
      this.$emit("day_list", idx + 1);
      if (this.content_active[idx]) {
        console.log("none");
      } else {
        for (let i = 0; i < this.content_active.length; i++) {
          if (i != idx) {
            this.content_active[i] = this.content_active[idx];
          }
        }
        this.content_active[idx] = !this.content_active[idx];
      }
    },
  },
  watch: {
    day_cnt(value) {
      this.active_count = value;
    },
  },
};
</script>

<style scoped>
.steps {
  display: flex;
  width: 100%;
  max-width: 1000px;
  justify-content: space-around;
  align-items: center;
  margin: 50px auto;
}

.steps div {
  width: 100%;
  height: 2px;
  background: rgba(49, 140, 252, 0.25);
  position: relative;
}
.steps div::after {
  content: "";
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background: #00859c;
  transform-origin: left;
  transform: scaleX(0);
  transition: transform 0.5s ease-in-out;
}
.steps div.active::after {
  transform: scaleX(1);
}
.steps button {
  cursor: pointer;
  background: transparent;
  border: 2px solid #00859c;
  color: #00859c;
  font-weight: 900;
  border-radius: 10px;
  min-width: 60px;
  height: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 14px;
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px,
    rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
  background: white;
  transition: all 0.3s ease;
}

.plus_btn:hover {
  background-color: #00859c;
  color: #fff;
}

.steps button.active {
  background: #00859c;
  color: white;
}

.contents {
  position: relative;
  width: 100%;
  height: 100%;
  min-height: 200px;
  box-sizing: border-box;
}

.content {
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  opacity: 0;
  z-index: 0;
  box-sizing: border-box;
  transition: all 0.3s ease-out;
}
.content.active {
  opacity: 1;
  z-index: 10;
}
.content__box {
  background: white;
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px,
    rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
  border-radius: 20px;
  padding: 50px;
  max-width: 1000px;
  margin: 0 auto;
}
</style>
