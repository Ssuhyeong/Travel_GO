<template>
  <myNav />
  <div id="category">
    <div id="icon_container">
      <div id="icon_list">
        <categoryIcon
          icon_type="icons"
          icon_name="관광지"
          @click="[select(12, 0, area[0]), reset()]" />
        <categoryIcon
          icon_type="place-of-worship"
          icon_name="문화시설"
          @click="[select(14, 0, area[0]), reset()]" />
        <categoryIcon
          icon_type="champagne-glasses"
          icon_name="축제공연"
          @click="[select(15, 0, area[0]), reset()]" />
        <categoryIcon
          icon_type="person-swimming"
          icon_name="레포츠"
          @click="[select(28, 0, area[0]), reset()]" />
        <categoryIcon
          icon_type="hotel"
          icon_name="숙박"
          @click="[select(32, 0, area[0]), reset()]" />
        <categoryIcon
          icon_type="bag-shopping"
          icon_name="쇼핑"
          @click="[select(38, 0, area[0]), reset()]" />
        <categoryIcon
          icon_type="utensils"
          icon_name="음식점"
          @click="[select(39, 0, area[0]), reset()]" />
      </div>
    </div>

    <h2 style="padding: 0px">최고의 즐길 거리,<br />모두 여기에</h2>
    <p style="margin-bottom: 40px; font-size: 14px">
      최고의 즐길 거리, 여러분들을 위해 준비했습니다. <br />
      여기에는 방대한 컨텐츠 별 정리가, 또한 아이콘 클릭 시 찾기 편하도록 카드
      배열이<br />
      최고의 여행 사이트 경험을 직접 만나세요
    </p>
    <div class="order">
      <p :class="{ active: sort_active[0] }" @click="select_active(0)">#전체</p>
      <p :class="{ active: sort_active[1] }" @click="select_active(1)">#서울</p>
      <p :class="{ active: sort_active[2] }" @click="select_active(2)">#인천</p>
      <p :class="{ active: sort_active[3] }" @click="select_active(3)">#대전</p>
      <p :class="{ active: sort_active[4] }" @click="select_active(4)">#대구</p>
      <p :class="{ active: sort_active[5] }" @click="select_active(5)">#광주</p>
      <p :class="{ active: sort_active[6] }" @click="select_active(6)">#부산</p>
      <p :class="{ active: sort_active[7] }" @click="select_active(7)">#울산</p>
      <p :class="{ active: sort_active[8] }" @click="select_active(8)">#세종</p>
      <p :class="{ active: sort_active[9] }" @click="select_active(9)">#경기</p>
      <p :class="{ active: sort_active[10] }" @click="select_active(10)">
        #강원
      </p>
      <p :class="{ active: sort_active[11] }" @click="select_active(11)">
        #충북
      </p>
      <p :class="{ active: sort_active[12] }" @click="select_active(12)">
        #충남
      </p>
      <p :class="{ active: sort_active[13] }" @click="select_active(13)">
        #경북
      </p>
      <p :class="{ active: sort_active[14] }" @click="select_active(14)">
        #경남
      </p>
      <p :class="{ active: sort_active[15] }" @click="select_active(15)">
        #전북
      </p>
      <p :class="{ active: sort_active[16] }" @click="select_active(16)">
        #전남
      </p>
      <p :class="{ active: sort_active[17] }" @click="select_active(17)">
        #제주
      </p>
    </div>
    <cardFlip :category_list="category_list" />
  </div>
</template>

<script>
import cardFlip from "@/components/cardFlip.vue";
import categoryIcon from "@/components/categoryIcon.vue";
import myNav from "../includes/myNav.vue";
import axios from "@/service/axios";

export default {
  data() {
    return {
      category_list: [],
      sort_active: [
        true,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
      ],
      contentType: "",
      page: 0,
      flag: false,
      sidoCode: "",
      area: {
        0: "",
        1: "1",
        2: "2",
        3: "3",
        4: "4",
        5: "5",
        6: "6",
        7: "7",
        8: "8",
        9: "31",
        10: "32",
        11: "33",
        12: "34",
        13: "35",
        14: "36",
        15: "37",
        16: "38",
        17: "39",
      },
    };
  },
  components: {
    cardFlip,
    categoryIcon,
    myNav,
  },
  methods: {
    select(num, current_page, sidoCode) {
      this.contentType = num;
      axios(
        `http://192.168.210.61:8080/attraction/like?contentTypeId=${num}&sidoCode=${sidoCode}&page=${current_page}`
      ).then((res) => {
        // console.log(res.data.content);
        this.category_list.push(...res.data.content);
      });
      this.flag = false;
    },
    scrollEvents() {
      let isBottom =
        document.documentElement.scrollTop + window.innerHeight + 300 >
        document.documentElement.offsetHeight;
      if (isBottom && !this.flag) {
        this.page = this.page + 1;
        this.flag = true;
        setTimeout(
          () => this.select(this.contentType, this.page, this.sidoCode),
          1000
        );
      }
    },
    reset() {
      this.category_list = [];
      for (let i = 0; i < 18; i++) {
        if (this.sort_active[i] == true) {
          this.sort_active[i] = false;
        }
      }
      this.sort_active[0] = true;
      this.sidoCode = "";
    },
    select_active(idx) {
      this.category_list = [];
      this.sidoCode = this.area[idx];

      console.log(this.sidoCode);

      if (this.sort_active[idx]) {
        this.sort_active[idx] = !this.sort_active[idx];
      } else {
        for (let i = 0; i < this.sort_active.length; i++) {
          if (i != idx) {
            this.sort_active[i] = this.sort_active[idx];
          }
        }
        this.sort_active[idx] = !this.sort_active[idx];
      }

      this.select(this.contentType, 0, this.sidoCode);
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
  font-size: 0.7rem;
  line-height: 2rem;
  font-weight: 300;
}

.order {
  display: flex;
  align-items: center;
  justify-content: center;
}

.order > p {
  padding: 0px 7.5px;
  border-radius: 10px;
  margin: 0px 5px;
}

.order > p:hover {
  background-color: #4f4f4f;
  color: #fff;
  cursor: pointer;
}

.active {
  background-color: #4f4f4f;
  color: #fff;
}
</style>
