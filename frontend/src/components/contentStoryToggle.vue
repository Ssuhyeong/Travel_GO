<template>
  <section>
    <div class="row content-story-toggle--js" style="--slideDuration: 8s">
      <!-- Graphics area -->
      <div class="width--55">
        <div class="cs--graphics-area">
          <!-- Item 1 -->
          <div class="cs--graphics-sequence">
            <img src="../assets/img/서울.jpg" alt="" />
          </div>

          <!-- Item 2 -->
          <div class="cs--graphics-sequence">
            <img src="../assets/img/부산.jpg" alt="" />
          </div>

          <!-- Item 3 -->
          <div class="cs--graphics-sequence">
            <img src="../assets/img/전주.jpg" alt="" />
          </div>
        </div>
      </div>

      <!-- Content area -->
      <div class="width--45">
        <!-- Intro -->
        <div class="content--wrapper">
          <h2>Korea Trip</h2>
          <p>
            여러분들이 원하는 지역을 선택하세요 저희가 해당 지역의 사진과 관련된
            설명들을 보여드릴게요 해당 카테고리를 통해 여러분의 선택을
            도와드립니다.
          </p>
        </div>

        <!-- Item 1 -->
        <div class="cs--content-area">
          <div class="cs--content-sequence">
            <!-- Progress indicator -->
            <div class="cs--content-progress-indicator--wrapper">
              <div class="cs--content-progress-indicator--bar"></div>
            </div>

            <!-- Title -->
            <div class="cs--content-headline">
              <h3>This is Seoul</h3>
            </div>

            <!-- Desc -->
            <div class="cs--content-desc">
              <p>
                한국의 대표적인 도시 서울, 다양한 사람들과 다양한 인프라를
                즐기세요!
              </p>
            </div>
          </div>
        </div>

        <!-- Item 2 -->
        <div class="cs--content-area">
          <div class="cs--content-sequence">
            <!-- Progress indicator -->
            <div class="cs--content-progress-indicator--wrapper">
              <div class="cs--content-progress-indicator--bar"></div>
            </div>

            <!-- Title -->
            <div class="cs--content-headline">
              <h3>This is Busan</h3>
            </div>

            <!-- Desc -->
            <div class="cs--content-desc">
              <p>
                한국의 제 2의 도시 부산, 시원한 바다와 함께 이번 여행을
                즐기세요!
              </p>
            </div>
          </div>
        </div>

        <!-- Item 3 -->
        <div class="cs--content-area">
          <div class="cs--content-sequence">
            <!-- Progress indicator -->
            <div class="cs--content-progress-indicator--wrapper">
              <div class="cs--content-progress-indicator--bar"></div>
            </div>

            <!-- Title -->
            <div class="cs--content-headline">
              <h3>This is jeonju</h3>
            </div>

            <!-- Desc -->
            <div class="cs--content-desc">
              <p>
                한옥 마을 전주, 한국의 전통적인 문화를 경험하고 싶으시면 오세요!
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script scoped>
import { gsap } from "gsap";

export default {
  mounted() {
    // Get all story elements on current page
    let contentStoryToggleElement = document.querySelectorAll(
      ".content-story-toggle--js"
    );

    // For each element =>
    contentStoryToggleElement.forEach((wrapper) => {
      // Variables
      // --------------------------------------------------------------------------------
      let imgSequences = wrapper.querySelectorAll(".cs--graphics-sequence");
      let contentProgressBar = wrapper.querySelectorAll(
        ".cs--content-progress-indicator--bar"
      );
      let contentDesc = wrapper.querySelectorAll(".cs--content-desc");
      let contentArea = wrapper.querySelectorAll(".cs--content-area");
      let contentArray = Array.from(contentArea);
      var intervalId;

      // Events
      // --------------------------------------------------------------------------------

      // Give first element active class
      contentArray[0].classList.add("is--active");
      toggleSequences();

      // Autoplay
      autoplaySequence();

      // Activate manual navigation on click
      contentArea.forEach((item) => {
        item.addEventListener("click", (e) => {
          // Break autoplay function
          stopInterval();
          // Reset all opened accordion descriptions
          if (!e.currentTarget.classList.contains("is--active")) {
            gsap.to(contentDesc, { height: "0", duration: 0.25 });
            headerClicked(e);
          }
        });
      });

      // Autoplay
      function autoplaySequence() {
        let slide = 1;

        // Get duration by inline style
        let duration = wrapper.style.getPropertyValue("--slideDuration");
        // Turn duration into js usable value
        const timeout = duration.replace("s", "000");

        intervalId = setInterval(() => {
          activeSlide(slide);
        }, timeout);

        // Slide behaviors
        function activeSlide(i) {
          let activeWrapper = contentArray[i];
          let expandableItem = activeWrapper.querySelector(".cs--content-desc");

          // Reset all opened accordion descriptions
          gsap.to(contentDesc, { height: "0", duration: 0.25 });
          // Expand accordion description
          gsap.to(expandableItem, { height: "auto", duration: 0.25 });
          // Toggle active class on active element
          activeWrapper.classList.add("is--active");
          // Remove active class from all elements except current active
          contentArea.forEach((header) => {
            if (header !== activeWrapper) {
              header.classList.remove("is--active");
            }
          });
          // Toggle fitting graphic based on active class of the content area
          toggleSequences();

          // Increase value of 'slide'
          if (slide >= contentArray.length - 1) {
            slide = 0;
          } else {
            slide++;
          }
        }
      }

      function stopInterval() {
        // Clear interval
        clearInterval(intervalId);
        // Disable all progress bar animations
        contentProgressBar.forEach((p) => {
          if (!p.classList.contains("paused")) {
            p.classList.add("paused");
          }
        });
      }

      // Toggle sequences
      function headerClicked(element) {
        let expandableItem =
          element.currentTarget.querySelector(".cs--content-desc");

        // Expand accordion description
        gsap.to(expandableItem, { height: "auto", duration: 0.25 });
        // Toggle active class on clicked element
        element.currentTarget.classList.add("is--active");
        // Remove active class from all elements except current clicked
        contentArea.forEach((header) => {
          if (header !== element.currentTarget) {
            header.classList.remove("is--active");
          }
        });
        // Toggle fitting graphic based on active class of the content area
        toggleSequences();
      }

      // Toggle graphics
      function toggleSequences() {
        let imgArray = Array.from(imgSequences);
        let active = 0;

        for (let i = 0; i < contentArray.length; i++) {
          imgArray[i].classList.remove("is--active");
          if (contentArray[i].classList.contains("is--active")) {
            active = i;
          }
        }
        imgArray[active].classList.add("is--active");
      }
    });
  },
};
</script>

<style scoped>
.content-story-toggle--js .cs--graphics-area {
  display: grid;
}
.content-story-toggle--js .cs--graphics-area .cs--graphics-sequence {
  width: 100%;
  grid-column: 1;
  grid-row: 1;
  opacity: 0;
  transition-property: all;
  transition-duration: 0.25s;
  transition-timing-function: ease;
}
.content-story-toggle--js .cs--graphics-area .cs--graphics-sequence.is--active {
  opacity: 1;
}
.content-story-toggle--js .cs--content-area .cs--content-sequence {
  position: relative;
  min-height: 28px;
  padding: 0 0 0 20px;
  margin-top: 40px;
  margin-bottom: 40px;
}
.content-story-toggle--js
  .cs--content-area
  .cs--content-sequence
  .cs--content-progress-indicator--wrapper {
  position: absolute;
  display: inline-block;
  width: 3px;
  height: 100%;
  top: 0;
  left: 0;
  background-color: #dcdcdc;
  border-radius: 3px;
  will-change: max-height;
  overflow: hidden;
}
.content-story-toggle--js
  .cs--content-area
  .cs--content-sequence
  .cs--content-progress-indicator--wrapper
  .cs--content-progress-indicator--bar {
  position: absolute;
  display: inline-block;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: #3c4043;
  scale: 1 0;
  transform-origin: top;
}
.content-story-toggle--js
  .cs--content-area
  .cs--content-sequence
  .cs--content-headline {
  cursor: pointer;
}
.content-story-toggle--js
  .cs--content-area
  .cs--content-sequence
  .cs--content-headline
  h3 {
  opacity: 0.6;
  transition-property: all;
  transition-duration: 0.25s;
  transition-timing-function: ease;
}
.content-story-toggle--js
  .cs--content-area
  .cs--content-sequence
  .cs--content-headline
  h3:hover {
  opacity: 1;
}
.content-story-toggle--js
  .cs--content-area
  .cs--content-sequence
  .cs--content-desc {
  height: 0px;
  overflow: hidden;
}
.content-story-toggle--js
  .cs--content-area.is--active
  .cs--content-sequence
  .cs--content-progress-indicator--wrapper
  .cs--content-progress-indicator--bar {
  animation: fillProgress linear;
  animation-duration: var(--slideDuration, 2s);
}
.content-story-toggle--js
  .cs--content-area.is--active
  .cs--content-sequence
  .cs--content-progress-indicator--wrapper
  .cs--content-progress-indicator--bar.paused {
  animation: none;
}
.content-story-toggle--js
  .cs--content-area.is--active
  .cs--content-sequence
  .cs--content-headline
  h3 {
  color: #3c4043;
  opacity: 1;
}
.content-story-toggle--js
  .cs--content-area.is--active
  .cs--content-sequence
  .cs--content-desc {
  height: auto;
}
@keyframes fillProgress {
  0% {
    scale: 1 0;
  }
  100% {
    scale: 1 1;
  }
}
* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
h2 {
  font-size: 36px;
  font-weight: 700;
  line-height: 1.2;
  color: #3c4043;
}
h3 {
  font-size: 21px;
  font-weight: 700;
  line-height: 1.2;
  color: #3c4043;
}
p {
  font-size: 16px;
  font-weight: 300;
  line-height: 26px;
  margin-top: 12px;
  color: #3c4043;
}
img {
  display: block;
  width: 50%;
  height: 100%;
}
section {
  position: relative;
  display: flex;
  width: 100%;
  height: 100%;
  max-width: 1280px;
  margin: auto;
  padding: 0 40px;
}
section .row {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
}
section .row .width--55 {
  width: 45%;
  padding: 20px;
}
section .row .width--45 {
  width: 45%;
  padding: 20px;
}
section .row .width--45 .content--wrapper {
  padding-bottom: 12px;
}
section .row .width--45 .content--wrapper p {
  margin-top: 16px;
}

img {
  width: 100%;
}
</style>
