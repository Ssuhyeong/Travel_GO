<template>
  <myNav />
  <div id="detail_container">
    <section>
      <font-awesome-icon
        :icon="['fass', 'left-long']"
        size="3x"
        style="margin: 20px 10px; cursor: pointer"
        @click="$router.go(-1)" />
    </section>
    <section id="title_sec">
      <img
        :src="detail_data.first_image"
        alt=""
        style="width: 40%; height: 400px; border-radius: 10px" />
      <div class="description">
        <div
          style="
            display: flex;
            align-items: center;
            justify-content: space-between;
          ">
          <h3 style="display: inline; margin: 0px; color:#777777">
            {{ catagory_spec[detail_data.content_type_id] }}
          </h3>
          <div
            style="display: flex; align-items: center; justify-content: center">
              <font-awesome-icon v-if="test == false"
                id="empty_heart"
                :icon="['far', 'heart']"
                size="xl" @click="like_add()" />
              <font-awesome-icon v-else
              :icon="['fas', 'heart']"
              size="xl"
              style="color: #ff0000; padding: 5px; cursor: pointer;" @click="like_delete()" />
            <img
              src="@/assets/img/kakao_talk.png"
              alt=""
              style="width: 30px; padding: 5px; cursor: pointer"
              @click="kakaoLink()" />
          </div>
        </div>

        <h2 style="font-size: 40px; margin: 0px">{{ detail_data.title }}</h2>
        <h3>{{ detail_data.addr1 }}</h3>
        <p>
          {{ textLengthOverCut(detail_data.overview) }}
        </p>
      </div>
    </section>
    <section>
      <div
        id="map"
        style="width: 100%; height: 400px; border-radius: 10px"></div>
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
          style="margin-right: 10px; cursor: pointer"
          @click="$router.push({name: 'reviewpage', params: {contentId : content_id, title:detail_data.title}})" />
      </div>
      <div class="review_content" v-for="review in review_data.slice(0, 2)" :key="review.articleNo">
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
                {{ review.star }}.0
              </div>
              <div id="review_content">{{ review.content }}</div>
              <div id="review_time">
                {{ review.registerTime }}
              </div>
            </div>
      </div>
    </section>
  </div>
</template>

<script>
import myNav from "../includes/myNav.vue";
import axios from "@/service/axios";

export default {
  name: "detailView",
  components: { myNav },
  data() {
    return {
      detail_data: {
        overview: "",
      },
      map: null,
      catagory_spec: {
        12: "관광지",
        14: "문화시설",
        15: "축제공연행사",
        25: "여행코스",
        28: "레포츠",
        32: "숙박",
        38: "쇼핑",
        39: "음식점",
      },
      length: 0,
      content_id:"",
      review_data: [],
      test: false
    };
  },
  created() {
    //const params = new URL(document.location).searchParams;
    //const content_id = params.get("content_id");
    this.content_id = this.$route.params.contentId;

    const url = `http://localhost:8080/attraction/search-list?contentId=${this.content_id}`;
    axios.get(url).then((res) => {
      this.detail_data = res.data.content[0];
      console.log(res);

      if (window.kakao && window.kakao.maps) {
        this.initMap(this.detail_data.latitude, this.detail_data.longitude);
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=811a7460154557e361e1a1839f2697c5&libraries=services";
        document.head.appendChild(script);
      }
    });
  },
  mounted() {
    const content_id = this.$route.params.contentId;

    axios.get(`http://localhost:8080/review?attractionId=${content_id}`).then((res) => {
      this.review_data = res.data;
    })
    .catch((err) => console.log(err) )
  },
  methods: {
    initMap(latitude, longitude) {
      console.log(this.detail_data.latitude);
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(latitude, longitude),
        level: 3,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      const markerPosition = new kakao.maps.LatLng(latitude, longitude);

      const marker = new kakao.maps.Marker({
        position: markerPosition,
      });

      marker.setMap(this.map);
    },
    kakaoLink() {
      window.Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: this.detail_data.title,
          description: this.detail_data.overview,
          imageUrl: this.detail_data.first_image,
          link: {
            mobileWebUrl: `http://localhost:5000/detailpage?${this.detail_data.content_id}`,
            webUrl: `http://localhost:5000/detailpage?content_id=${this.detail_data.content_id}`,
          },
        },
      });
    },
    textLengthOverCut(txt) {
      if (txt.length > 600) {
        txt = txt.substr(0, 600) + "...";
      }
      return txt;
    },
    like_add() {
      axios.post(`http://localhost:8080/like?attractionId=${this.$route.params.contentId}`).then(() => {
        console.log('등록 성공');
      })
    },
    like_delete() {
      axios.delete(`http://localhost:8080/like?attractionId=${this.$route.params.contentId}`).then(() => {
        console.log('삭제 성공');
      })
    }
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

a {
  text-decoration-line: none;
  color: #2c3e50;
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

#review_content {
  font-size: 28px;
  margin: 20px 0px;
}

#review_time{
  font-size: 14px;
}

#empty_heart {
  padding: 5px;
  cursor: pointer;
}

#empty_heart:hover {
  color: #ff0000;
}


.description {
  margin-left: 30px;
}

.review_content {
  display: flex;
  padding: 40px;
}

.review_description {
  padding: 50px;
}

.review_description > h1 {
  font-size: 40px;
  margin: 0px;
}

</style>
