<template>
  <div class="inner">
    <div class="star-rating">
      <div class="star" v-for="index in 5" :key="index" @click="check(index)">
        <span v-if="index < score"
          ><font-awesome-icon
            :icon="['fas', 'star']"
            size="2x"
            style="color: #f29d00"
        /></span>
        <span v-if="index >= score"
          ><font-awesome-icon :icon="['far', 'star']" size="2x"
        /></span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      score: 0,
    };
  },
  methods: {
    check(index) {
      this.score = index + 1;
      this.$emit("setScore", this.score - 1);
    },
  },
};
</script>

<style scoped>
.star-rating {
  display: flex;
  align-items: center;
  justify-content: center;
}

span:hover {
  color: #f29d00;
  cursor: pointer;
}
</style>
