<template>
  <div class="card">
    <div class="card-body">
      <div class="search_container">
        <h1 class="heading">여행지를 검색합니다</h1>
        <div class="searchInputWrapper">
          <form id="search_form">
            <input
              class="searchInput"
              type="text"
              placeholder="여행지를 검색해주세요"
              v-model="keyword"
              v-on:keyup.enter="searchcontent" />
            <font-awesome-icon
              :icon="['fas', 'search']"
              class="searchInputIcon"
              @click="searchcontent()" />
          </form>
        </div>
        <div id="select_container">
          <div>검색</div>
          <div>추천</div>
          <div>거리순</div>
          <div>MY</div>
        </div>
      </div>
      <div class="card_content">
        <div id="info_around">
          <p style="margin: 10px 0px">주변탐색</p>
          <div id="info_list">
            <infoIcon icon_type="wallet" icon_name="은행" ref="activeIcon" />
            <infoIcon icon_type="cart-shopping" icon_name="마트" />
            <infoIcon icon_type="house-medical" icon_name="약국" />
            <infoIcon icon_type="utensils" icon_name="주유소" />
            <infoIcon icon_type="mug-hot" icon_name="카페" />
            <infoIcon icon_type="store" icon_name="편의점" />
          </div>
        </div>
        <hr />
        <div class="place_num">
          <p>장소</p>
          <p style="margin-left: 10px; color: #696969">
            {{ trip_list.length }}건
          </p>
        </div>
        <div
          id="content_list"
          v-for="(content, index) in trip_list"
          :key="content.content_id">
          <placeContent :trip_content="content" :marker_num="index + 1" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import infoIcon from "./infoIcon.vue";
import placeContent from "./placeContent.vue";

export default {
  name: "cardMap",
  components: {
    infoIcon,
    placeContent,
  },
  data() {
    return {
      trip_list: [],
      keyword: "",
      page: "2",
    };
  },
  mounted() {
    const searchForm = document.getElementById("search_form");
    searchForm.addEventListener("submit", (e) => e.preventDefault());
  },
  methods: {
    searchcontent() {
      const keyword = this.keyword;
      const page = this.page;
      console.log(this.keyword);
      const url = `http://localhost:8080/attraction/search-list?keyword=${keyword}&page=${page}`;

      this.$axios
        .get(url)
        .then((res) => {
          this.trip_list = res.data.content;
          this.$emit("setContentList", res.data.content);

          console.log(res.data.content);
        })
        .catch((error) => {
          console.log("검색 실패" + error.data);
        });
    },
  },
};
</script>

<style scoped>
hr {
  background: #dbdbdb;
  height: 1px;
  border: 0;
}
.card {
  position: relative;
  width: 400px;
  height: 97%;
  background-color: #fff;
  border-radius: 5px;
  margin: 20px 10px;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
}

.card_content {
  position: absolute;
  height: calc(100% - 160.86px);
  width: 100%;
  overflow-y: auto;
}

.card_content::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  background-color: #f5f5f5;
}

.card_content::-webkit-scrollbar {
  width: 8px;
  background-color: #f5f5f5;
}

.card_content::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #888888;
}

#info_around {
  padding: 30px;
}

#info_list {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 15px;
}

.card img {
  height: 200px;
  object-fit: cover;
}

.place_num {
  display: flex;
  align-items: center;
  padding: 10px 30px;
}
.card-title {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}

.card-text {
  font-size: 1rem;
  color: #555;
  margin-bottom: 1rem;
}

/* 검색 관련 */
/* Quick and dirty normalize hax */
#select_container {
  display: flex;
  align-items: center;
  justify-content: space-between;
  text-align: center;
  width: 93%;
}

#select_container > div {
  padding: 10px 20px;
  margin: 10px;
  border-radius: 10px;
  cursor: pointer;
}

#select_container > div:hover {
  background-color: #017e94;
}

.search_container {
  height: 0%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: #fff;
  background-color: #0097b2;
  border-radius: 10px 10px 0px 0px;
}

.heading {
  margin-bottom: 1.5rem;
  font-size: 1.2rem;
}

.searchInputWrapper {
  position: relative;
}

.searchInput {
  width: 18rem;
  height: 2rem;
  padding: 0.2rem 3rem 0 1rem;
  border-radius: 0.4rem;
  border: none;
  transition: transform 0.1s ease-in-out;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  font-family: cookierunotf;
}

::placeholder {
  color: #a1a1a1;
}

/* hide the placeholder text on focus */
:focus::placeholder {
  text-indent: -999px;
}

.searchInput:focus {
  outline: none;
  transform: scale(1.1);
  transition: all 0.1s ease-in-out;
}

.searchInputIcon {
  position: absolute;
  right: 0.5rem;
  top: 0.7rem;
  color: #a1a1a1;
  transition: all 0.1s ease-in-out;
}

.container:focus-within > .searchInputWrapper > .searchInputIcon {
  right: 0.2rem;
}
</style>
