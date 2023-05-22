<template>
  <div class="place_content">
    <kakakIcon :marker_num="'marker_' + marker_num" />
    <div class="place_title">
      <h3>{{ trip_content.title }}</h3>
      <p>{{ catagory_spec[trip_content.content_type_id] }}</p>
    </div>
    <div class="review_num">
      <p></p>
    </div>
    <div class="addr_main">{{ trip_content.addr1 }}</div>
    <div class="addr_sub">
      <font-awesome-icon
        :icon="['fas', 'star']"
        size="xs"
        style="color: #f29d00" />
      {{ total_score.toFixed(1) }} ( {{ review_num }}명 ) |
      <font-awesome-icon
        v-if="like_empty == false"
        :icon="['fas', 'heart']"
        size="xs"
        style="color: #ff0000" />
      <font-awesome-icon
        v-else
        :icon="['far', 'heart']"
        size="xs"
        style="color: #ff0000" />
      {{ trip_content.like_count }}
    </div>
    <div class="detail">
      <a
        class="detail_btn"
        @click="
          $router.push({
            name: 'detailpage',
            params: { contentId: trip_content.content_id },
          })
        "
        >상세보기</a
      >
    </div>
  </div>
</template>

<script>
import kakakIcon from "./kakakIcon.vue";
import axios from "@/service/axios";

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
      review_data: [],
      review_num: 0,
      total_score: 0,
      like_empty: true,
    };
  },
  mounted() {
    this.like_Exist();
    console.log(this.trip_content);

    axios
      .get(
        `http://localhost:8080/review?attractionId=${this.trip_content.content_id}`
      )
      .then((res) => {
        this.review_data = res.data;
        this.review_num = res.data.length;

        for (let i = 0; i < this.review_num; i++) {
          console.log(this.review_data[i].star);
          this.total_score = this.total_score + this.review_data[i].star;
        }
        console.log(this.total_score);
        this.total_score = this.total_score / this.review_num;

        if (isNaN(this.total_score)) {
          this.total_score = 0;
        }
        console.log(this.total_score);
      });
  },
  methods: {
    goDetail() {
      const content_id = this.trip_content.content_id;
      location.href = `/detailpage?content_id=${content_id}`;
    },
    like_Exist() {
      this.like_empty = true;

      axios.get(`http://localhost:8080/like`).then((res) => {
        for (let i = 0; i < res.data.length; i++) {
          if (res.data[i] == this.trip_content.content_id) {
            this.like_empty = false;
            break;
          }
        }
      });
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
