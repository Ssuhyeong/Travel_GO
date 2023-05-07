<template>
  <a href="/boardpage">
    <div id="board_box">
      더 알아보기
      <font-awesome-icon
        :icon="['fas', 'add']"
        size="20"
        style="margin-left: 12px; color: #787878"
      />
    </div>
  </a>
</template>

<script>
export default {};
</script>

<style scoped>
#board_box {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 120px;
  height: 40px;
  border: 1px solid #cccccc;
  padding: 0px 8px;
}
a {
  color: #2c3e50;
  font-weight: 600;
  font-size: 14px;
  text-decoration-line: none;
}
 
</style>
