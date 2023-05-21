<template>
  <myNav />
  <div id="detail_container">
    <section id="detail_top">
      <font-awesome-icon
        :icon="['fass', 'left-long']"
        size="3x"
        style="margin: 0px 10px; cursor: pointer"
        @click="$router.go(-1)" />
      <reviewModalVue />
    </section>
    <section>
      <div id="review_title">
        <div id="title">{{ $route.params.title }}</div>
        <h3>{{ total_score.toFixed(1) }}</h3>
        <div >
          <font-awesome-icon :icon="['fas', 'star']" size="2x" style="color:#f29d00" v-for="star in parseInt(total_score)" :key="star"/>
          <font-awesome-icon :icon="['far', 'star-half-stroke']" size="2x" style="color:#f29d00" v-if="total_score % 1 != 0"/>
        </div>
      </div>
      <div id="review_select">
        <p style="color: #696969; font-size: 18px">리뷰 {{ review_num }}</p>
        <div class="order">
          <p @click="recommand" :class="{active : sort_active[0]}">추천순</p>
          <p @click="registTime" :class="{active : sort_active[1]}">최신순</p>
        </div>
      </div>
    </section>

    <section>
      <div class="review_content">
        <div v-for="(review) in review_data.slice(0, review_range)" :key="review.articleNo">
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
              <h3 style="width: 150px">{{review.member.name}}</h3>
            </div>
            <div class="review_description">
              <div>
                <font-awesome-icon :icon="['fas', 'star']" v-for="star in review.star" :key="star"/>
                <font-awesome-icon :icon="['far', 'star']" v-for="star in 5 - review.star" :key="star"/>
                {{ review.star }}
              </div>
              <div id="review_content">{{ review.content }}</div>
              <div id="review_time">
                {{ review.registerTime }}
              </div>
            </div>
          </div>
          <hr />
        </div>
      </div>
      <div id="more_container">
        <div id="more_btn" @click="more">더보기</div>
      </div>
    </section>
  </div>
</template>

<script>
import reviewModalVue from "@/components/reviewModal.vue";
import myNav from "../includes/myNav.vue";
import axios from "@/service/axios";


export default {
  name: "detailView",
  components: { myNav, reviewModalVue },
  data() {
    return {
      review_data: [],
      review_num: 0,
      total_score: 0,
      review_range: 5,
      sort_active: [false, true]
    };
  },
  mounted() {
    const content_id = this.$route.params.contentId;

    axios.get(`http://localhost:8080/review?attractionId=${content_id}`).then((res) => {
      this.review_data = res.data;
      this.review_num = res.data.length;
    
      
      for(let i = 0;i<this.review_num;i++) {
        this.total_score += this.review_data[i].star;
      }

      this.total_score = this.total_score / this.review_num;

      if(isNaN(this.total_score)) {
        this.total_score = 0;
      }
    })
    .catch((err) => console.log(err) )
  },
  methods: {
    recommand() {
      if(this.review_data != undefined) {
        this.review_data = this.review_data.sort((a, b) => (b.star - a.star));
      }
      this.sort_active[0] = true;
      this.sort_active[1] = false;
    },
    registTime() {
      if(this.review_data != undefined) {
        this.review_data = this.review_data.sort((a, b) => new Date(a.registerTime) - new Date(b.registerTime));
      }
      this.sort_active[0] = false;
      this.sort_active[1] = true;
    },
    more() {
      this.review_range += 5;
    }
  }
};
</script>

<style scoped>

hr {
  border: 0;
  margin: 1.35em auto;
  max-width: 100%;
  background-position: 50%;
  box-sizing: border-box;
  width: 100%;
  height: 30px;
  border-style: solid;
  border-color: hsla(0, 0%, 75%, 0.9);
  border-width: 1px 0 0 0;
  border-radius: 15px;
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
  margin: 20px auto;
}

#review_content {
  font-size: 28px;
  margin: 20px 0px;
}

#title {
  font-size: 60px;
}

#review_time{
  font-size: 14px;
}

#more_container {
  display: flex;
  align-items: center;
  justify-content: center;
}

#more_btn {
  padding: 10px 20px;
  margin: 20px 0px;
  border-radius: 20px;
  background-color: #f0efef;
}

#more_btn:hover {
  background-color: #4f4f4f;
  color: #fff;
  cursor: pointer;
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
