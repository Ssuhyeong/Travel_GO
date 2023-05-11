<template>
  <div id="main_body" @scroll="handleScroll">
    <myNav />
    <!-- partial:index.partial.html -->
    <section id="top">
      <img
        src="https://images.unsplash.com/photo-1593726222205-21404ff4e5fd?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1073&q=80"
        id="bg" />
      <h2 id="text">Travel</h2>
      <img
        src="https://aryan-tayal.github.io/Mountains-Parallax/clouds_1.png"
        id="clouds_1" />
      <img
        src="https://aryan-tayal.github.io/Mountains-Parallax/clouds_2.png"
        id="clouds_2" />
      <img
        src="https://aryan-tayal.github.io/Mountains-Parallax/clouds_1.png"
        id="mountain_left" />
      <img
        src="https://aryan-tayal.github.io/Mountains-Parallax/clouds_2.png"
        id="mountain_right" />
    </section>
    <cardSlider style="padding-top: 40px; margin-bottom: 0px" />
    <section id="sec" style="padding-top: 0px">
      <h2 style="padding: 0px">Welcome to the 여행가자</h2>
      <p>
        여러분들의 여행의 도우미 <br />
        여러분들의 여행을 돕기 위해 어쩌구 저쩌구
      </p>
      <animationBox style="margin: 30px" />
    </section>
    <section id="sec" style="padding-bottom: 0px">
      <contentStoryToggle />
    </section>
    <section id="sec" style="padding-top: 0px; margin-bottom: 40px">
      <div id="board_title">
        <h1 style="display: inline">공지사항</h1>
        <boardBox />
      </div>
      <div id="board_contents">
        <div class="board_content">
          <h3>{{ board_list[0].subject.substr(0, 15) }}</h3>
          <p>{{ board_list[0].registerTime }}</p>
        </div>
        <div class="board_content">
          <h3>{{ board_list[1].subject.substr(0, 15) }}</h3>
          <p>{{ board_list[1].registerTime }}</p>
        </div>
        <div class="board_content">
          <h3>{{ board_list[2].subject.substr(0, 15) }}</h3>
          <p>{{ board_list[2].registerTime }}</p>
        </div>
        <div class="board_content" style="border-right: 3px solid #b5b5b5">
          <h3>{{ board_list[3].subject.substr(0, 15) }}</h3>
          <p>{{ board_list[3].registerTime }}</p>
        </div>
      </div>
    </section>
    <myFooter />
  </div>
</template>

<script>
import animationBox from "@/components/animationBox.vue";
import contentStoryToggle from "@/components/contentStoryToggle.vue";
import cardSlider from "@/components/cardSlider.vue";
import boardBox from "@/components/boardBox.vue";
import myFooter from "@/views/includes/myFooter.vue";
import myNav from "@/views/includes/myNav.vue";

export default {
  name: "mainView",
  components: {
    animationBox,
    contentStoryToggle,
    cardSlider,
    boardBox,
    myFooter,
    myNav,
  },
  data() {
    return {
      board_list: [],
    };
  },
  created() {
    this.$axios.get(`http://localhost:8080/board`).then((res) => {
      console.log(res.data);
      this.board_list = res.data;
    });
  },
  methods: {},
};
// 구름 이동 관련
//   const mountainLeft = document.querySelector("#mountain_left");
//   const mountainRight = document.querySelector("#mountain_right");
//   const cloud1 = document.querySelector("#clouds_1");
//   const cloud2 = document.querySelector("#clouds_2");
//   const text = document.querySelector("#text");
//   const man = document.querySelector("#man");

//   window.addEventListener("scroll", () => {
//     let value = scrollY;
//     mountainLeft.style.left = `-${value / 0.7}px`;
//     cloud2.style.left = `-${value * 2}px`;
//     mountainRight.style.left = `${value / 0.7}px`;
//     cloud1.style.left = `${value * 2}px`;
//     text.style.bottom = `-${value}px`;
//     man.style.height = `${window.innerHeight - value}px`;
//   });
// },
</script>

<style scoped>
#main_body {
  text-align: center;
  /* height: calc(100vh - 3.5em); */
  /* overflow-x: hidden; */
  /* overflow-y: auto; */
}

#main_body::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  background-color: #f5f5f5;
}

#main_body::-webkit-scrollbar {
  width: 8px;
  background-color: #f5f5f5;
}

#main_body::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #555;
}

#top {
  position: relative;
  height: 100vh;
  width: 100vw;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

#top::before {
  content: "";
  position: absolute;
  bottom: 0;
  left: 0;
  height: 200px;
  width: 100%;
  background: linear-gradient(to top, #fff, transparent);
  z-index: 1000;
}

section img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  pointer-events: none;
}
#text {
  position: relative;
  color: white;
  font-size: 10rem;
}
#sec {
  padding: 50px;
}

#sec h1 {
  font-size: 40px;
}

#sec h2 {
  font-size: 4rem;
  margin-bottom: 30px;
}
#sec p {
  font-size: 1.2rem;
  line-height: 2rem;
  font-weight: 300;
  color: #20496a;
}

#board_title {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin: 30px;
}

#board_contents {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 300px;
}

.board_content {
  text-align: left;
  padding: 0px 30px;
  border-left: 3px solid #b5b5b5;
  width: 24%;
  height: 300px;
}

.board_content > h3 {
  font-size: 1.5em;
}

.board_content > p {
  font-size: 0.5em;
  margin-top: 190px;
}
</style>
