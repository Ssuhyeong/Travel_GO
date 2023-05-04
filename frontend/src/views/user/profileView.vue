<template>
  <div class="background">
    <div id="profile_container">
      <div class="outer-div">
        <div class="inner-div">
          <div class="front">
            <div class="front__bkg-photo"></div>
            <div class="front__face-photo"></div>
            <div class="front__text">
              <h3 class="front__text-header">손수형</h3>
              <p class="front__text-para">
                <i class="fas fa-map-marker-alt front-icons"></i
                >tngud124@kakao.com
              </p>

              <span class="front__text-hover">Hover For Edit</span>
            </div>
          </div>
          <div class="back">
            <div class="social-media-wrapper">
              <a href="#" class="social-icon"
                ><font-awesome-icon
                  :icon="['fas', 'pen']"
                  size="20"
                  style="margin: 10px"
              /></a>
              <a href="#" class="social-icon"
                ><font-awesome-icon
                  :icon="['fas', 'trash-can']"
                  size="20"
                  style="margin: 10px"
              /></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#profile_container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
}

.background {
  position: absolute;
  top: 0;
  left: 0;
  height: 100vh;
  width: 100vw;
  background-size: cover;
  background: #ccc;
  background-position: 0 50%;
  background: url("https://images.unsplash.com/photo-1533035353720-f1c6a75cd8ab?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1974&q=80");
}
.background:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background: rgba(0, 0, 0, 0);
}

.outer-div,
.inner-div {
  height: 450px;
  max-width: 300px;
  margin: 0 auto;
  position: relative;
  z-index: 9999;
}

.outer-div {
  perspective: 900px;
  perspective-origin: 50% calc(50% - 18em);
}

.inner-div {
  margin: 0 auto;
  border-radius: 5px;
  font-weight: 400;
  color: #071011;
  font-size: 1rem;
  text-align: center;
  transition: all 0.6s cubic-bezier(0.8, -0.4, 0.2, 1.7);
  transform-style: preserve-3d;
}
.inner-div:hover .social-icon {
  opacity: 1;
  top: 0;
}

.outer-div:hover .inner-div {
  transform: rotateY(180deg);
}

.front,
.back {
  position: relative;
  top: 0;
  left: 0;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

.front {
  cursor: pointer;
  height: 100%;
  background: #fff;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  border-radius: 5px;
  box-shadow: 0 15px 10px -10px rgba(0, 0, 0, 0.5), 0 1px 4px rgba(0, 0, 0, 0.3),
    0 0 40px rgba(0, 0, 0, 0.1) inset;
}

.front__bkg-photo {
  position: relative;
  height: 150px;
  width: 300px;
  background: url("https://images.unsplash.com/photo-1511207538754-e8555f2bc187?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=88672068827eaeeab540f584b883cc66&auto=format&fit=crop&w=1164&q=80")
    no-repeat;
  background-size: cover;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  overflow: hidden;
  border-top-right-radius: 5px;
  border-top-left-radius: 5px;
}
.front__bkg-photo:after {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
}

.front__face-photo {
  position: relative;
  top: -60px;
  height: 120px;
  width: 120px;
  margin: 0 auto;
  border-radius: 50%;
  border: 5px solid #fff;
  background: url("https://images.unsplash.com/photo-1679926820639-56c6f62e516e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80")
    no-repeat;
  background-size: cover;
  overflow: hidden;
}

.front__text {
  position: relative;
  top: -55px;
  margin: 0 auto;
  font-size: 18px;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}
.front__text .front__text-header {
  font-weight: 700;
  text-transform: uppercase;
  font-size: 20px;
}
.front__text .front__text-para {
  position: relative;
  top: -5px;
  color: #000;
  font-size: 14px;
  letter-spacing: 0.4px;
  font-weight: 400;
}
.front__text .front-icons {
  position: relative;
  top: 0;
  font-size: 14px;
  margin-right: 6px;
  color: gray;
}
.front__text .front__text-hover {
  position: relative;
  top: 10px;
  font-size: 10px;
  color: tomato;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0.4px;
  border: 2px solid #00aacf;
  padding: 8px 15px;
  border-radius: 30px;
  background: #00aeff;
  color: #fff;
}

.back {
  transform: rotateY(180deg);
  position: absolute;
  top: 0;
  left: 0;
  height: 100%;
  width: 100%;
  background-color: #071011;
  border-radius: 5px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}

.social-media-wrapper {
  font-size: 36px;
}
.social-media-wrapper .social-icon {
  position: relative;
  top: 20px;
  margin-left: 5px;
  margin-right: 5px;
  opacity: 0;
  color: #fff;
  transition: all 0.4s cubic-bezier(0.3, 0.7, 0.1, 1.9);
}

.social-media-wrapper .social-icon:nth-child(1) {
  transition-delay: 0.6s;
}
.social-media-wrapper .social-icon:nth-child(2) {
  transition-delay: 0.7s;
}

.fab,
.fas {
  position: relative;
  top: 0;
  left: 0;
  transition: all 200ms ease-in-out;
}

.fab:hover,
.fas:hover {
  top: -5px;
}
</style>
