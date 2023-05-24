<template>
  <myNav />
  <div id="map">
    <div class="card">
      <div
        class="place_content"
        v-for="data in list_data"
        :key="data.content_id"
        @click="add_schedule(data)">
        <div class="place_title">
          <h3>{{ data.title }}</h3>
          <p>{{ catagory_spec[data.content_type_id] }}</p>
          <div id="check_icon"></div>
        </div>
        <div class="addr_main">{{ data.addr1 }}</div>
      </div>
    </div>
  </div>
  <div class="btn_container">
    <button @click="regist">등록하기</button>
  </div>
  <VueDraggableNext :list="list" @change="log" id="Drag_container" :options="{animation:300, handle:'.handle'}">
      <div
        v-for="element in list"
        :key="element.content_id"
        id="element"
        v-bind:style="{
          backgroundImage: 'url(' + element.first_image + ')'
        }"
      >
        {{ element.title }}
      </div>
    </VueDraggableNext>
  <scheduleDivisionUpdate :list_data="day_list" @current_day="current_day"/>
</template>

<script>
import scheduleDivisionUpdate from "@/components/scheduleDivisionUpdate.vue";
import { VueDraggableNext } from 'vue-draggable-next'
import myNav from "../includes/myNav.vue";
import axios from "@/service/axios";

export default {
  components: {
    scheduleDivisionUpdate,
    myNav,
    VueDraggableNext
  },
  data() {
    return {
      map: null,
      markers: [],
      infowindow: null,
      list_data: [
      ],
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
      day_list: [
        null,
        [],
        [],
        [],
        [],
        [],
        [],
        []
      ],
      list: [],
      enabled: true,
      dragging: false,
      active_day: 0
    };
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=811a7460154557e361e1a1839f2697c5&libraries=services";
      document.head.appendChild(script);
    }

    axios
      .get(`http://localhost:8080/like`)
      .then((res) => {
        for (let i = 0; i < res.data.length; i++) {
          const url = `http://localhost:8080/attraction/search-list?contentId=${res.data[i]}`;
          axios.get(url).then((res) => {
            this.list_data.push(res.data.content[0]);
          });
        }
      })
      .catch((err) => {
        console.log(err);
      });
  },
  methods: {
    current_day(value) {
      this.active_day = value;
      this.list = [];
    },  
    initMap() {
      const container = document.getElementById("map");

      const options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      //지도 객체를 등록합니다.
      //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
      this.map = new kakao.maps.Map(container, options);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },
    displayMarkers(positions) {
      console.log(positions);
      // 여러개 마커를 정보를 보여줄 info window
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      // 마커를 생성하고 지도 위에 마커를 표시
      positions.forEach((pos, idx) => {
        this.infowindow = new kakao.maps.InfoWindow({
          content: `<div style="width:200px; text-align:center">${positions[idx].title}</div>`,
        });

        // 이미지 초기화
        const imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png"; // 마커 이미지 url, 스프라이트 이미지를 씁니다
        const imageSize = new kakao.maps.Size(36, 37); // 마커 이미지의 크기
        const imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        };
        const markerImage = new kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        );

        const latlng = new kakao.maps.LatLng(pos.latitude, pos.longitude);

        const marker = new kakao.maps.Marker({
          map: this.map,
          position: latlng,
          image: markerImage,
        });

        this.infowindow.open(this.map, marker);
        this.markers.push(marker);
      });
      if (positions.length != 0) {
        const bounds = positions.reduce(
          (bounds, position) =>
            bounds.extend(
              new kakao.maps.LatLng(position.latitude, position.longitude)
            ),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    log(event) {
        console.log(event)
    },
    add_schedule(data) {
      console.log(data);
      this.list.push(data);
    },
    regist() {
      console.log(this.list);
      this.day_list[this.active_day + 1] = this.list;
    }
  },
};
</script>

<style scoped>
h3 {
  margin: 0px
}

#map {
  height: 500px;
  width: 1200px;
  margin: 0 auto;
  border-radius: 20px;
}

#Drag_container {
  display: flex;
  align-items: center;
  width: 100%;
  max-width: 1200px;
  height: 150px;
  border-radius: 10px;
  box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px,
    rgba(60, 64, 67, 0.15) 0px 1px 3px 1px;
  margin: 20px auto;
}

#element {
  height: 60%;
  width: 9%;
  padding: 20px; 
  border-radius: 20px;
  font-weight: 600;
  margin: 5px;  
  cursor: move;
  background-color: #fff;
  color:#fff;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-color : rgba(0,0,0,0.4);
  text-shadow: rgb(0, 0, 0) 1px 0 10px;
}

button {
  border: none;
  color: #fff;
  background: #00859c;
  appearance: none;
  font: inherit;
  font-size: 1.1rem;
  padding: 0.4em 0.6em;
  border-radius: 0.3em;
  cursor: pointer;
  margin: 20px 10px 10px;
}

.btn_container{
  width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: right;
}

.card {
  position: relative;
  width: 300px;
  height: 97%;
  background-color: #fff;
  border-radius: 5px;
  margin: 20px 10px;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  z-index: 2;
  overflow: auto;
}

.place_content {
  padding: 10px 20px;
  border-bottom: 1px solid #dbdbdb;
}
.place_content:hover {
  background-color: #d9ecf0;
  cursor: pointer;
}

.place_title {
  display: flex;
  align-items: center;
}

.place_title > p {
  color: #9c9c9c;
  font-size: 10px;
  margin-left: 6px;
}

.addr_main {
  font-size: 14px
}
</style>
