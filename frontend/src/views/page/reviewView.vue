<template>
  <myNav />
  <div id="detail_container">
    <section id="detail_top">
      <font-awesome-icon
        :icon="['fass', 'left-long']"
        size="3x"
        style="margin: 20px 10px; cursor: pointer"
        @click="$router.go(-1)" />
      <reviewModalVue />
    </section>
    <section>
      <div id="review_title">
        <h1>서울랜드</h1>
        <h3>3.8</h3>
        <div>
          <font-awesome-icon :icon="['fas', 'star']" />
          <font-awesome-icon :icon="['fas', 'star']" />
          <font-awesome-icon :icon="['fas', 'star']" />
          <font-awesome-icon :icon="['faR', 'star-half-stroke']" />
        </div>
      </div>
      <div id="review_select">
        <p style="color: #696969; font-size: 18px">리뷰 6,446</p>
        <div class="order">
          <p class="active">추천순</p>
          <p>최신순</p>
        </div>
      </div>
    </section>

    <section>
      <div class="review_content">
        <div class="content">
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
            <div>
              <font-awesome-icon :icon="['fas', 'star']" />
              <font-awesome-icon :icon="['fas', 'star']" />
              <font-awesome-icon :icon="['fas', 'star']" />
              <font-awesome-icon :icon="['faR', 'star-half-stroke']" />
              5.0
            </div>
            <h1>타이틀 제목입니다.</h1>
            <h3>
              너무너무 좋은 여행 수영장, 직원, 룸컨디션 모두 좋았습니다. 호텔 내
              음식도 개인차 있겠지만 ...
            </h3>
          </div>
        </div>
        <hr style="color: #f0efef" />
        <div class="content">
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
            <div>
              <font-awesome-icon :icon="['fas', 'star']" />
              <font-awesome-icon :icon="['fas', 'star']" />
              <font-awesome-icon :icon="['fas', 'star']" />
              <font-awesome-icon :icon="['faR', 'star-half-stroke']" />
              5.0
            </div>
            <h1>타이틀 제목입니다.</h1>
            <h3>
              너무너무 좋은 여행 수영장, 직원, 룸컨디션 모두 좋았습니다. 호텔 내
              음식도 개인차 있겠지만 ...
            </h3>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import reviewModalVue from "@/components/reviewModal.vue";
import myNav from "../includes/myNav.vue";

export default {
  name: "detailView",
  components: { myNav, reviewModalVue },
  data() {
    return {
      detail_data: {},
      map: null,
    };
  },
};
</script>

<style scoped>
h1 {
  font-size: 60px;
  margin: 0px;
}

hr {
  margin-bottom: 50px;
}

section {
  display: block;
  margin: 40px auto;
  width: 120vh;
}

#review_title {
  text-align: center;
}

#review_select {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 40px;
}

#detail_top {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.order {
  display: flex;
  align-items: center;
  justify-content: center;
}

.order > p {
  padding: 10px 15px;
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

.review_content {
  padding: 60px;
  border-radius: 20px;
  background-color: #f0efef;
}

.content {
  display: flex;
  align-items: center;
  margin: 0px 10px;
}

.review_description {
  padding: 50px;
}

.review_description > h1 {
  font-size: 40px;
  margin: 0px;
}
</style>
