<template>
  <myNav />
  <div id="category">
    <div id="icon_container">
      <div id="icon_list">
        <categoryIcon
          icon_type="icons"
          icon_name="관광지"
          @click="select(12)" />
        <categoryIcon
          icon_type="place-of-worship"
          icon_name="문화시설"
          @click="select(14)" />
        <categoryIcon
          icon_type="champagne-glasses"
          icon_name="축제공연"
          @click="select(15)" />
        <categoryIcon
          icon_type="person-swimming"
          icon_name="레포츠"
          @click="select(28)" />
        <categoryIcon icon_type="hotel" icon_name="숙박" @click="select(32)" />
        <categoryIcon
          icon_type="bag-shopping"
          icon_name="쇼핑"
          @click="select(38)" />
        <categoryIcon
          icon_type="utensils"
          icon_name="음식점"
          @click="select(39)" />
      </div>
    </div>

    <h2 style="padding: 0px">최고의 즐길 거리,<br />모두 여기에</h2>
    <p style="margin-bottom: 80px">
      최고의 즐길 거리, 여러분들을 위해 준비했습니다. <br />
      여기에는 방대한 컨텐츠 별 정리가, 또한 아이콘 클릭 시 찾기 편하도록 카드
      배열이<br />
      최고의 여행 사이트 경험을 직접 만나세요
    </p>
    <cardFlip :category_list="category_list" />
  </div>
</template>

<script>
import cardFlip from "@/components/cardFlip.vue";
import categoryIcon from "@/components/categoryIcon.vue";
import myNav from "../includes/myNav.vue";
import axios from "@/service/axios"

export default {
  data() {
    return {
      category_list: [],
      contentType: "",
      page: 0,
      flag: false,
    };
  },
  components: {
    cardFlip,
    categoryIcon,
    myNav,
  },
  methods: {
    select(num, current_page) {
      this.contentType = num;
      axios(
        `http://localhost:8080/attraction/search-list?contentTypeId=${num}&page=${current_page}`
      ).then((res) => {
        // console.log(res.data.content);
        this.category_list.push(...res.data.content);
      });
      this.flag = false;
    },
    scrollEvents() {
      this.page = this.page + 1;
      let isBottom =
        document.documentElement.scrollTop + window.innerHeight + 300 >
        document.documentElement.offsetHeight;
      if (isBottom && !this.flag) {
        this.flag = true;
        setTimeout(() => this.select(this.contentType, this.page), 1000);
      }
    },
  },
  mounted() {
    window.addEventListener("scroll", this.scrollEvents);
  },
  unmounted() {
    window.removeEventListener("scroll", this.scrollEvents);
  },
  watch: {
    contentType() {
      this.page = 0;
      this.category_list = [];
    },
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
  width: 1000px;
  height: 50px;
  margin: 20px 0px;
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
