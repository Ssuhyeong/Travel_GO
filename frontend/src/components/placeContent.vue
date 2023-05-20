<template>
  <div class="place_content">
    <kakakIcon :marker_num="'marker_' + marker_num" />
    <div class="place_title">
      <h3>{{ trip_content.title }}</h3>
      <p>{{ catagory_spec[trip_content.content_type_id] }}</p>
    </div>
    <div class="review_num">
      <p>리뷰 0</p>
    </div>
    <div class="addr_main">{{ trip_content.addr1 }}</div>
    <div class="addr_sub">{{ sido_code[trip_content.sido_code] }}</div>
    <div class="detail">
      <a class="detail_btn" @click="goDetail()">상세보기</a>
    </div>
  </div>
</template>

<script>
import kakakIcon from "./kakakIcon.vue";

export default {
  name: "placeContent",
  components: {
    kakakIcon,
  },
  props: {
    trip_content: {
      type: Object,
    },
    marker_num: {
      type: String,
    },
  },
  data() {
    return {
      catagory_spec: {
        12: "관광지",
        14: "문화시설",
        15: "축제공연행사",
        25: "여행코스",
        28: "레포츠",
        32: "숙박",
        38: "쇼핑",
        39: "음식점",
      },
      sido_code: {
        1: "서울특별시",
        2: "인천광역시",
        3: "대전광역시",
        4: "대구광역시",
        5: "광주광역시",
        6: "부산광역시",
        7: "울산광역시",
        8: "세종특별자치시",
        31: "경기도",
        32: "강원도",
        33: "충청북도",
        34: "충청남도",
        35: "경상북도",
        36: "경상남도",
        37: "전라북도",
        38: "전라남도",
        39: "제주도",
      },
    };
  },
  methods: {
    goDetail() {
      const content_id = this.trip_content.content_id;
      location.href = `/detailpage?content_id=${content_id}`;
    },
  },
};
</script>

<style scoped>
p,
h3 {
  margin: 2px 0px;
  padding: 0px;
}

a {
  text-decoration-line: none;
  cursor: pointer;
}

a:hover {
  text-decoration-line: underline;
}

a:visited {
  color: #5398dd;
}

.addr_main,
.addr_sub,
.detail_btn {
  margin-left: 45px;
}

.place_content {
  padding: 10px 30px;
  border-bottom: 1px solid #dbdbdb;
}
.place_content:hover {
  background-color: #d9ecf0;
}

.place_title {
  display: flex;
  align-items: center;
}

.place_title > p {
  color: #9c9c9c;
  font-size: 12px;
  margin-left: 6px;
}

.review_num {
  color: #9c9c9c;
  font-size: 12px;
}

.addr_sub {
  font-size: 12px;
  color: #9c9c9c;
  margin-top: 5px;
  margin-bottom: 5px;
}
</style>
