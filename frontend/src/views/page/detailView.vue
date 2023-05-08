<template>
  <myNav />
  <div id="detail_container">
    <section>
      <font-awesome-icon
        :icon="['fass', 'left-long']"
        size="3x"
        style="margin: 20px 0px; cursor: pointer"
        @click="$router.push('/')" />
    </section>
    <section id="title_sec">
      <img
        src="http://tong.visitkorea.or.kr/cms/resource/31/219131_image2_1.jpg"
        alt=""
        style="width: 40%; height: 400px; border-radius: 10px" />
      <div class="description">
        <div
          style="
            display: flex;
            align-items: center;
            justify-content: space-between;
          ">
          <h3 style="display: inline; margin: 0px">
            {{ detail_data.content_type_id }}
          </h3>
          <div
            style="display: flex; align-items: center; justify-content: center">
            <font-awesome-icon
              :icon="['fas', 'heart']"
              size="xl"
              style="color: #ff0000; padding: 5px" />
            <img
              src="@/assets/img/kakao_talk.png"
              alt=""
              style="width: 30px; padding: 5px; cursor: pointer"
              @click="kakaoLink()" />
          </div>
        </div>

        <h2>{{ detail_data.title }}</h2>
        <h3>{{ detail_data.addr1 }}</h3>
        <h3>010-1234-1234</h3>
        <p>
          {{ detail_data.overview }}
        </p>
      </div>
    </section>
    <section>
      <img
        src="@/assets/img/map.png"
        alt=""
        style="width: 100%; height: 400px; border-radius: 10px" />
    </section>
    <section>
      <h2>이런 곳은 어때요?</h2>
      <div id="recommendation_list">
        <div>
          <img
            src="@/assets/img/부산.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px" />
          <h3>부평</h3>
        </div>
        <div>
          <img
            src="@/assets/img/서울.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px" />
          <h3>인천</h3>
        </div>
        <div>
          <img
            src="@/assets/img/전주.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px" />
          <h3>익산</h3>
        </div>
        <div>
          <img
            src="@/assets/img/서울.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px" />
          <h3>청삼동</h3>
        </div>
      </div>
    </section>
    <section>
      <hr style="border: 1px solid #b5b5b5; margin: 20px 0px" />
    </section>
    <section>
      <div
        style="
          display: flex;
          align-items: center;
          justify-content: space-between;
        ">
        <h2 style="display: inline; font-size: 50px">리뷰</h2>
        <font-awesome-icon
          :icon="['fass', 'plus']"
          size="2x"
          style="margin-right: 10px" />
      </div>
      <div class="review_content">
        <div
          style="
            display: inline;
            width: 150px;
            text-align: center;
            margin-right: 50px;
          ">
          <img
            src="@/assets/img/man.jpg"
            alt=""
            style="width: 150px; height: 150px; border-radius: 100px" />
          <h3 style="width: 150px">알프레드</h3>
        </div>
        <div class="review_description">
          <h1>타이틀 제목입니다.</h1>
          <h3>
            너무너무 좋은 여행 수영장, 직원, 룸컨디션 모두 좋았습니다. 호텔 내
            음식도 개인차 있겠지만 ...
          </h3>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import myNav from "../includes/myNav.vue";

export default {
  name: "detailView",
  components: { myNav },
  data() {
    return {
      detail_data: {},
    };
  },
  created() {
    const params = new URL(document.location).searchParams;
    const content_id = params.get("content_id");

    const url = `http://localhost:8080/attraction/search-list?contentId=${content_id}`;
    this.$axios.get(url).then((res) => {
      this.detail_data = res.data.content[0];
    });
  },
  methods: {
    kakaoLink() {
      window.Kakao.Link.sendDefault({
        objectType: "text",
        text: "당신에게 여행지를 추천했어요 빨리와",
        link: {
          mobileWebUrl: "http://localhost:8080",
          webUrl: "http://localhost:8080",
        },
      });
    },
  },
};
</script>

<style scoped>
section {
  display: block;
  margin: 0 auto;
  width: 120vh;
}

section > h2 {
  font-size: 40px;
}

#title_sec {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

#recommendation_list {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

#recommendation_list > div {
  width: 24%;
}

.description {
  margin-left: 30px;
}

.review_content {
  display: flex;
  padding: 80px;
}

.review_description {
  padding: 50px;
}

.review_description > h1 {
  font-size: 40px;
  margin: 0px;
}
</style>
