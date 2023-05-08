<template>
  <myNav />
  <div id="category">
    <div id="icon_container">
      <div id="icon_list">
        <categoryIcon />
        <categoryIcon />
        <categoryIcon />
        <categoryIcon />
        <categoryIcon />
      </div>
    </div>

    <h2 style="padding: 0px">최고의 즐길 거리,<br />모두 여기에</h2>
    <p>
      최고의 즐길 거리, 여러분들을 위해 준비했습니다. <br />
      여기에는 방대한 컨텐츠 별 정리가, 또한 아이콘 클릭 시 찾기 편하도록 카드
      배열이<br />
      최고의 여행 사이트 경험을 직접 만나세요
    </p>
    <cardFlip />
  </div>
</template>

<script>
import cardFlip from "@/components/cardFlip.vue";
import categoryIcon from "@/components/categoryIcon.vue";
import myNav from "../includes/myNav.vue";

export default {
  components: {
    cardFlip,
    categoryIcon,
    myNav,
  },
};
</script>

<style scoped>
#category {
  text-align: center;
}

#icon_container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 30px;
}

#icon_list {
  width: 800px;
  display: flex;
  align-items: center;
  justify-content: space-evenly;
}

#category h2 {
  font-size: 4rem;
  margin: 30px;
  color: #20496a;
}
#category p {
  font-size: 1.2rem;
  line-height: 2rem;
  font-weight: 300;
  color: #20496a;
}
</style>
