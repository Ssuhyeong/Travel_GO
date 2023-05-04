<template>
  <div id="category_icon">
    <font-awesome-icon :icon="['fas', 'phone']" size="2x" />
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#category_icon {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  width: 50px;
  height: 50px;
  border-radius: 10px;
  box-shadow: rgba(0, 0, 0, 0.19) 0px 10px 20px, rgba(0, 0, 0, 0.23) 0px 6px 6px;
}
</style>
