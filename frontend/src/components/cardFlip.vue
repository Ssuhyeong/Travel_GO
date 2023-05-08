<template>
  <div class="content">
    <a class="card" href="#!">
      <div
        class="front"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/10051g000001h3cqs1344_Z_640_10000_R5.jpg_.webp?proc=autoorient);
        ">
        <p>
          꽉 막힌 교통체증이 두렵다면 바로 여기! 지하철로도 갈 수 있는 송도
          센트럴 파크를 추천드려요.
        </p>
      </div>
      <div class="back">
        <div>
          <p>센트럴파크</p>
          <p>
            송도 센트럴파크가 대한민국 최초의 해수 공원인 것 알고 계셨나요?
            크기도 여의도 공원의 2배나 되어서 센트럴 8경 코스가 있을 정도로
            볼거리가 풍부하다는 사실! 인천의 공유 자전거 쿠키를 타고 주변을
            천천히 돌아보면 완전 힐링~
          </p>
          <button class="button">Click Here</button>
        </div>
      </div></a
    >
    <a class="card" href="#!">
      <div
        class="front"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/10021g000001h7xezCDEB_Z_640_10000_R5.jpg_.webp?proc=autoorient);
        ">
        <p>
          알록달록한 예쁜 건물들에 각종 벽화와 포토존이 가득해서 어디서 사진
          찍던 인생샷 남기기 가능!
        </p>
      </div>
      <div class="back">
        <div>
          <p>프로방스</p>
          <p>
            이렇게 예쁜 곳은 가기만 해도 힐링 되지 않나요? 야경도 너무 예뻐서
            밤에 가도 좋은 곳이에요. 동화 속 한 장면에 들어온 듯한 파주
            프로방스에서 유럽의 정취를 느껴보세요~
          </p>
          <button class="button">Click Here</button>
        </div>
      </div></a
    >
    <a class="card" href="#!">
      <div
        class="front"
        style="
          background-image: url(https://ak-d.tripcdn.com/images/10011g000001hds073A02_Z_640_10000_R5.jpg_.webp?proc=autoorient);
        ">
        <p>
          어느 계절에 와도 고즈넉하게 즐기기 좋은 남이섬은 계절의 특성을 잘 느낄
          수 있는 곳이죠.
        </p>
      </div>
      <div class="back">
        <div>
          <p>남이섬</p>
          <p>
            자연으로 둘러쌓인 남이섬은 봄에 오면 분홍색의 수놓은 벚꽃들을 즐길
            수 있고 푸른 계절에 오면 싱그러움을 느낄 수 있는 곳이에요.
          </p>
          <button class="button">Click Here</button>
        </div>
      </div></a
    >
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
*,
*:before,
*:after {
  box-sizing: border-box;
}

html {
  font-size: 18px;
  line-height: 1.5;
  font-weight: 300;
  color: #333;
  font-family: "Nunito Sans", sans-serif;
}

.content {
  display: flex;
  margin: 0 auto;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  max-width: 1000px;
}

.heading {
  width: 100%;
  margin-left: 1rem;
  font-weight: 900;
  font-size: 1.618rem;
  text-transform: uppercase;
  letter-spacing: 0.1ch;
  line-height: 1;
  padding-bottom: 0.5em;
  margin-bottom: 1rem;
  position: relative;
}
.heading:after {
  display: block;
  content: "";
  position: absolute;
  width: 60px;
  height: 4px;
  background: linear-gradient(135deg, #1a9be6, #1a57e6);
  bottom: 0;
}

.description {
  width: 100%;
  margin-top: 0;
  margin-left: 1rem;
  margin-bottom: 3rem;
}

.card {
  color: inherit;
  cursor: pointer;
  width: calc(33% - 2rem);
  min-width: calc(33% - 2rem);
  height: 400px;
  min-height: 400px;
  perspective: 1000px;
  margin: 1rem;
  position: relative;
}
@media screen and (max-width: 800px) {
  .card {
    width: calc(50% - 2rem);
  }
}
@media screen and (max-width: 500px) {
  .card {
    width: 100%;
  }
}

.front,
.back {
  display: flex;
  border-radius: 6px;
  background-position: center;
  background-size: cover;
  text-align: center;
  justify-content: center;
  align-items: center;
  position: absolute;
  height: 100%;
  width: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  transform-style: preserve-3d;
  transition: ease-in-out 600ms;
}

.front {
  background-size: cover;
  padding: 2rem;
  font-size: 1.618rem;
  font-weight: 600;
  color: #fff;
  overflow: hidden;
  font-family: Poppins, sans-serif;
}
.front:before {
  position: absolute;
  display: block;
  content: "";
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: linear-gradient(135deg, #1a9be6, #1a57e6);
  opacity: 0.25;
  z-index: -1;
}
.card:hover .front {
  transform: rotateY(180deg);
}
.card:nth-child(even):hover .front {
  transform: rotateY(-180deg);
}

.back {
  background: #fff;
  transform: rotateY(-180deg);
  padding: 0 2em;
}
.back .button {
  background: linear-gradient(135deg, #1a9be6, #1a57e6);
}
.back .button:before {
  box-shadow: 0 0 10px 10px rgba(26, 87, 230, 0.25);
  background-color: rgba(26, 87, 230, 0.25);
}
.card:hover .back {
  transform: rotateY(0deg);
}
.card:nth-child(even) .back {
  transform: rotateY(180deg);
}
.card:nth-child(even) .back .button {
  background: linear-gradient(135deg, #e61a80, #e61a3c);
}
.card:nth-child(even) .back .button:before {
  box-shadow: 0 0 10px 10px rgba(230, 26, 60, 0.25);
  background-color: rgba(230, 26, 60, 0.25);
}
.card:nth-child(even):hover .back {
  transform: rotateY(0deg);
}

.button {
  transform: translateZ(40px);
  cursor: pointer;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  font-weight: bold;
  color: #fff;
  padding: 0.5em 1em;
  border-radius: 100px;
  font: inherit;
  border: none;
  position: relative;
  transform-style: preserve-3d;
  transition: 300ms ease;
}
.button:before {
  transition: 300ms ease;
  position: absolute;
  display: block;
  content: "";
  transform: translateZ(-40px);
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  height: calc(100% - 20px);
  width: calc(100% - 20px);
  border-radius: 100px;
  left: 10px;
  top: 16px;
}
.button:hover {
  transform: translateZ(55px);
}
.button:hover:before {
  transform: translateZ(-55px);
}
.button:active {
  transform: translateZ(20px);
}
.button:active:before {
  transform: translateZ(-20px);
  top: 12px;
}
</style>
