<template>
  <div>
    <div id="category_icon" @mouseover="showText" @mouseout="deleteText">
      <div>
        <font-awesome-icon :icon="['fas', icon_type]" size="2x" />
      </div>
    </div>
    <p>{{ text }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      text: "",
    };
  },
  props: {
    icon_type: {
      type: String,
      default: "",
    },
    icon_name: {
      type: String,
      default: "",
    },
  },
  methods: {
    showText() {
      this.text = this.icon_name;
    },
    deleteText() {
      this.text = "";
    },
  },
};
</script>

<style scoped>
p {
  margin: 2px;
}

#category_icon {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  width: 40px;
  height: 40px;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.1) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
  cursor: pointer;
  color: #2c3e50;
}

#category_icon:hover {
  background-color: #2c3e50;
  color: #fff;
}
</style>
