<template>
  <div id="detail_container">
    <section>
      <font-awesome-icon
        :icon="['fass', 'left-long']"
        size="3x"
        style="margin: 20px 0px"
      />
    </section>
    <section id="title_sec">
      <img
        src="@/assets/img/서울.jpg"
        alt=""
        style="width: 40%; height: 400px; border-radius: 10px"
      />
      <div class="description">
        <div
          style="
            display: flex;
            align-items: center;
            justify-content: space-between;
          "
        >
          <h3 style="display: inline; margin: 0px">카테고리 명</h3>
          <div
            style="display: flex; align-items: center; justify-content: center"
          >
            <font-awesome-icon
              :icon="['fas', 'heart']"
              size="xl"
              style="color: #ff0000; padding: 5px"
            />
            <img
              src="@/assets/img/kakao_talk.png"
              alt=""
              style="width: 30px; padding: 5px"
            />
          </div>
        </div>

        <h2>타이틀</h2>
        <h3>00도 00시 00동 111-111</h3>
        <h3>010-1234-1234</h3>
        <p>
          경기도 파주시 서패동에 있는 베이커리 카페다. 근처에 심학산 야영장
          글램핑 가든이 있어 글램핑을 하러 온 사람들이 많이 방문하는 곳이다.
          야외, 지하, 지상 등 다양한 좌석이 마련되어 있어 원하는 자리에 앉을 수
          있다. 정원에 있는 식물과 자연을 바라보며 힐링할 수 있다. 내부가 넓은
          창고형 베이커리 카페와 달리, 포근하고 아늑하며, 조용하고 한적한
          분위기를 느낄 수 있다. 날씨가 좋으면 밖에 누워서 잠들어 있는 고양이를
          볼 수도 있다. 많은 사람이 모여 왁자지껄한 카페와 달리 고요한 감성
          카페다. 가까이에 파주출판단지, 심학산이 있다.
        </p>
      </div>
    </section>
    <section>
      <img
        src="@/assets/img/map.png"
        alt=""
        style="width: 100%; height: 400px; border-radius: 10px"
      />
    </section>
    <section>
      <h2>이런 곳은 어때요?</h2>
      <div id="recommendation_list">
        <div>
          <img
            src="@/assets/img/부산.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px"
          />
          <h3>부평</h3>
        </div>
        <div>
          <img
            src="@/assets/img/서울.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px"
          />
          <h3>인천</h3>
        </div>
        <div>
          <img
            src="@/assets/img/전주.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px"
          />
          <h3>익산</h3>
        </div>
        <div>
          <img
            src="@/assets/img/서울.jpg"
            alt=""
            style="width: 100%; height: 200px; border-radius: 10px"
          />
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
        "
      >
        <h2 style="display: inline; font-size: 50px">리뷰</h2>
        <font-awesome-icon
          :icon="['fass', 'plus']"
          size="2x"
          style="margin-right: 10px"
        />
      </div>
      <div class="review_content">
        <div
          style="
            display: inline;
            width: 150px;
            text-align: center;
            margin-right: 50px;
          "
        >
          <img
            src="@/assets/img/man.jpg"
            alt=""
            style="width: 150px; height: 150px; border-radius: 100px"
          />
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
export default {
  name: "detailView",
  components: {},
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
