<template>
  <div class="place_content">
    <kakakIcon :marker_num="'marker_' + marker_num" />
    <div class="place_title">
      <h3>{{ trip_content.title }}</h3>
      <p>{{ trip_content.content_type_id }}</p>
    </div>
    <div class="review_num">
      <p>리뷰 0</p>
    </div>
    <div class="addr_main">{{ trip_content.addr1 }}</div>
    <div class="addr_sub">{{ trip_content.sido_code }}</div>
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
