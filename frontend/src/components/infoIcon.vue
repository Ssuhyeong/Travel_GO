<template>
  <div id="infoicon_container">
    <div id="info_icon">
      <font-awesome-icon :icon="['fas', icon_type]" size="xs" />
    </div>
    <p>{{ icon_name }}</p>
  </div>
</template>

<script>
export default {
  name: "infoIcon",
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
  data() {
    return {};
  },
};
</script>

<style scoped>
#info_icon {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  width: 10px;
  height: 10px;
  border-radius: 100px;
  box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
  cursor: pointer;
}
#infoicon_container > p {
  font-size: 12px;
  margin: 5px 0px;
  text-align: center;
  color: #7e7e7e;
}
</style>
