<template>
  <section class="game-section" style="padding-bottom: 0px">
    <h2 class="line-title">한국 관광지 추천</h2>
    <div class="Card_main">
      <div
        class="item"
        v-bind:class="{ active: section_active[0] }"
        v-on:click="select(0)"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/10051g000001h3cqs1344_Z_640_10000_R5.jpg_.webp?proc=autoorient);
        ">
        <div class="item-desc">
          <h3>센트럴파크</h3>
          <p>
            꽉 막힌 교통체증이 두렵다면 바로 여기! 지하철로도 갈 수 있는 송도
            센트럴 파크를 추천드려요. 송도 센트럴파크가 대한민국 최초의 해수
            공원인 것 알고 계셨나요? 크기도 여의도 공원의 2배나 되어서 센트럴
            8경 코스가 있을 정도로 볼거리가 풍부하다는 사실! 인천의 공유 자전거
            쿠키를 타고 주변을 천천히 돌아보면 완전 힐링~
          </p>
        </div>
      </div>
      <div
        class="item"
        v-bind:class="{ active: section_active[1] }"
        v-on:click="select(1)"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/10021g000001h7xezCDEB_Z_640_10000_R5.jpg_.webp?proc=autoorient);
        ">
        <div class="item-desc">
          <h3>프로방스</h3>
          <p>
            알록달록한 예쁜 건물들에 각종 벽화와 포토존이 가득해서 어디서 사진
            찍던 인생샷 남기기 가능! 이렇게 예쁜 곳은 가기만 해도 힐링 되지
            않나요? 야경도 너무 예뻐서 밤에 가도 좋은 곳이에요. 동화 속 한
            장면에 들어온 듯한 파주 프로방스에서 유럽의 정취를 느껴보세요~
          </p>
        </div>
      </div>
      <div
        class="item"
        v-bind:class="{ active: section_active[2] }"
        v-on:click="select(2)"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/10011g000001hds073A02_Z_640_10000_R5.jpg_.webp?proc=autoorient);
        ">
        <div class="item-desc">
          <h3>남이섬</h3>
          <p>
            남이섬에 길게 뻗은 메타세콰이어길, 한번쯤은 보신 적 있으실텐데요.
            너무 유명해서 웨딩 촬영도 많이 하는 곳이에요. 어느 계절에 와도
            고즈넉하게 즐기기 좋은 남이섬은 계절의 특성을 잘 느낄 수 있는
            곳이죠. 자연으로 둘러쌓인 남이섬은 봄에 오면 분홍색의 수놓은
            벚꽃들을 즐길 수 있고 푸른 계절에 오면 싱그러움을 느낄 수 있는
            곳이에요.
          </p>
        </div>
      </div>
      <div
        class="item"
        v-bind:class="{ active: section_active[3] }"
        v-on:click="select(3)"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/10091g000001h3992EB1B_Z_640_10000_R5.png_.webp?proc=autoorient);
        ">
        <div class="item-desc">
          <h3>스카이워크</h3>
          <p>
            높은 다리에 바닥이 투명으로 되어있어서 하늘 위를 걷는 듯 짜릿한
            스카이워크를 걸어보셨나요? 소양강 스카이워크는 국내 최장 길이의
            스카이워크 시설인데요. 춘천의 랜드마크인 소양 2교와 소양강 처녀상
            옆에 위치해있어, 한번에 춘천에 랜드마크를 모두 볼 수 있어요!
          </p>
        </div>
      </div>
      <div
        class="item"
        v-bind:class="{ active: section_active[4] }"
        v-on:click="select(4)"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/100f1g000001h0w1t6897_Z_640_10000_R5.jpg_.webp?proc=autoorient);
        ">
        <div class="item-desc">
          <h3>별빛정원우주</h3>
          <p>
            별빛정원우주는 낮과 밤의 매력이 다른데요. 낮에는 자연의 정취를 느낄
            수 있고, 밤에는 신비한 조명으로 가득한 우주를 체험할 수 있어요!
            신비한 조명과 별빛으로 반짝이는 야외 공간과 미디어 아트를 즐길 수
            있는 실내 전시공간을 감상하면 이색적인 경험이 될거요~
          </p>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "cardSlider",
  data() {
    return {
      section_active: [true, false, false, false, false],
      active_range: ["60px", "120px", "180px", "240px", "300px"],
      current_range: "60px",
      past_range: "0px",
      line_flag: false,
    };
  },
  methods: {
    select(idx) {
      this.line_flag = true;
      if (this.section_active[idx]) {
        console.log("none");
      } else {
        for (let i = 0; i < this.section_active.length; i++) {
          if (i != idx) {
            this.section_active[i] = this.section_active[idx];
          }
        }
        this.section_active[idx] = !this.section_active[idx];
      }
      this.current_range = this.active_range[idx];
    },
  },
  watch: {
    current_range(current, past) {
      console.log(current);
      this.past_range = past;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.clear {
  clear: both;
}
img {
  max-width: 100%;
  border: 0px;
}
ul,
ol {
  list-style: none;
}

h2 {
  margin-bottom: 48px;
  padding-bottom: 16px;
  font-size: 20px;
  line-height: 28px;
  font-weight: 700;
  position: relative;
  text-transform: capitalize;
}
h3 {
  margin: 0 0 10px;
  font-size: 24px;
  line-height: 36px;
}
/******* Common Element CSS End *********/

/* -------- title style ------- */

.Card_main {
  display: flex;
  align-items: center;
  justify-content: center;
}

.line-title {
  position: relative;
  width: 300px;
}
.line-title::before,
.line-title::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  height: 4px;
  border-radius: 2px;
}
.line-title::before {
  width: 100%;
  background: #f2f2f2;
}

.line-title::after {
  background: #e73700;
  width: v-bind(current_range);
}

/******* Middle section CSS Start ******/
/* -------- Landing page ------- */
.game-section {
  padding: 60px 50px;
}
.game-section .owl-stage {
  margin: 15px 0;
  display: flex;
  display: -webkit-flex;
}
.game-section .item {
  margin: 0 15px 60px;
  width: 15%;
  height: 400px;
  display: flex;
  display: -webkit-flex;
  align-items: flex-end;
  -webkit-align-items: flex-end;
  background: #343434 no-repeat center center / cover;
  border-radius: 16px;
  overflow: hidden;
  position: relative;
  transition: all 0.4s ease-in-out;
  -webkit-transition: all 0.4s ease-in-out;
  cursor: pointer;
}
.game-section .item.active {
  width: 40%;
  box-shadow: 12px 40px 40px rgba(0, 0, 0, 0.25);
  -webkit-box-shadow: 12px 40px 40px rgba(0, 0, 0, 0.25);
}
.game-section .item:after {
  content: "";
  display: block;
  position: absolute;
  height: 100%;
  width: 100%;
  left: 0;
  top: 0;
  background-image: linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 1));
}
.game-section .item-desc {
  padding: 0 24px 12px;
  color: #fff;
  position: relative;
  z-index: 1;
  overflow: hidden;
  transform: translateY(calc(100% - 54px));
  -webkit-transform: translateY(calc(100% - 54px));
  transition: all 0.4s ease-in-out;
  -webkit-transition: all 0.4s ease-in-out;
}
.game-section .item.active .item-desc {
  transform: none;
  -webkit-transform: none;
}
.game-section .item-desc p {
  opacity: 0;
  -webkit-transform: translateY(32px);
  transform: translateY(32px);
  transition: all 0.4s ease-in-out 0.2s;
  -webkit-transition: all 0.4s ease-in-out 0.2s;
}
.game-section .item.active .item-desc p {
  opacity: 1;
  -webkit-transform: translateY(0);
  transform: translateY(0);
}
.game-section .owl-theme.custom-carousel .owl-dots {
  margin-top: -20px;
  position: relative;
  z-index: 5;
}
/******** Middle section CSS End *******/

/***** responsive css Start ******/

@media (min-width: 1200px) and (max-width: 1400px) {
  .game-section {
    padding: 100px 30px;
  }
}

@media (min-width: 992px) and (max-width: 1199px) {
  h2 {
    margin-bottom: 32px;
  }
  h3 {
    margin: 0 0 8px;
    font-size: 20px;
    line-height: 32px;
  }

  /* -------- Landing page ------- */
  .game-section {
    padding: 50px 30px;
  }
  .game-section .item {
    margin: 0 12px 60px;
    width: 260px;
    height: 360px;
  }
  .game-section .item.active {
    width: 400px;
  }
  .game-section .item-desc {
    transform: translateY(calc(100% - 46px));
    -webkit-transform: translateY(calc(100% - 46px));
  }
}

@media (min-width: 768px) and (max-width: 991px) {
  p {
    font-size: 5px;
  }
  h2 {
    margin-bottom: 32px;
  }
  h3 {
    margin: 0 0 8px;
    font-size: 18px;
    line-height: 32px;
  }
  .line-title {
    width: 330px;
  }

  /* -------- Landing page ------- */
  .game-section {
    padding: 50px 30px 40px;
  }
  .game-section .item {
    margin: 0 12px 60px;
    width: 240px;
    height: 330px;
  }
  .game-section .item.active {
    width: 360px;
  }
  .game-section .item-desc {
    transform: translateY(calc(100% - 42px));
    -webkit-transform: translateY(calc(100% - 42px));
  }
}

@media (max-width: 767px) {
  body {
    font-size: 14px;
  }
  p {
    font-size: 5px;
  }
  h2 {
    margin-bottom: 20px;
  }
  h3 {
    margin: 0 0 8px;
    font-size: 12px;
    line-height: 24px;
  }
  .line-title {
    width: 250px;
  }

  /* -------- Landing page ------- */
  .game-section {
    padding: 30px 15px 20px;
  }
  .game-section .item {
    margin: 0 10px 40px;
    width: 200px;
    height: 280px;
  }
  .game-section .item.active {
    width: 270px;
    box-shadow: 6px 10px 10px rgba(0, 0, 0, 0.25);
    -webkit-box-shadow: 6px 10px 10px rgba(0, 0, 0, 0.25);
  }
  .game-section .item-desc {
    padding: 0 14px 5px;
    transform: translateY(calc(100% - 42px));
    -webkit-transform: translateY(calc(100% - 42px));
  }
}
</style>
