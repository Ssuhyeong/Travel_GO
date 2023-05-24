<template>
  <myNav />
  <section id="header_container">
    <div id="schedule_title">
      <div style="display: flex">
        <font-awesome-icon
          :icon="['fass', 'left-long']"
          size="3x"
          style="margin: 20px 10px; cursor: pointer"
          @click="$router.go(-1)" />
        <div style="padding: 30px; font-weight: 600; font-size: 20px">
          {{ this.$route.params.title }}
        </div>
      </div>
      <font-awesome-icon
        :icon="['fas', 'upload']"
        style="
          padding: 12.5px;
          background-color: #00859c;
          border-radius: 100px;
          color: #fff;
          cursor: pointer;
        "
        size="lg"
        @click="route_upload()" />
    </div>
  </section>
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
    <button class="update_btn" @click="regist">편집</button>
  </div>
  <VueDraggableNext
    :list="list"
    @change="log"
    id="Drag_container"
    :options="{ animation: 300, handle: '.handle' }">
    <div
      v-for="(element, idx) in list"
      :key="element.content_id"
      id="element"
      v-bind:style="{
        backgroundImage: 'url(' + element.first_image + ')',
      }">
      {{ element.title }}
      <div class="delete-button">
        <font-awesome-icon
          :icon="['fas', 'x']"
          size="xs"
          style="
            padding: 8px;
            margin-left: 15px;
            border-radius: 100px;
            background-color: #ff4769;
          "
          @click="list_delete(idx)" />
      </div>
    </div>
  </VueDraggableNext>
  <scheduleDivisionUpdate :list_data="day_list" @current_day="current_day" />
</template>

<script>
import scheduleDivisionUpdate from "@/components/scheduleDivisionUpdate.vue";
import { VueDraggableNext } from "vue-draggable-next";
import myNav from "../includes/myNav.vue";
import swal from "sweetalert";
import axios from "@/service/axios";

export default {
  components: {
    scheduleDivisionUpdate,
    myNav,
    VueDraggableNext,
  },
  data() {
    return {
      map: null,
      markers: [],
      infowindow: null,
      list_data: [],
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
      day_list: [[], [], [], [], [], [], [], []],
      list: [],
      enabled: true,
      dragging: false,
      active_day: 0,
      customOverlay_list: [],
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

      if (this.customOverlay_list.length > 0) {
        this.customOverlay_list.forEach((item) => {
          item.setMap(null);
        });
      }

      // 마커를 생성하고 지도 위에 마커를 표시
      positions.forEach((pos, idx) => {
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

        var content = `<div class ="label"><span class="left"></span><span class="center">${positions[idx].title}</span><span class="right"></span></div>`;

        // 커스텀 오버레이를 생성합니다
        var customOverlay = new kakao.maps.CustomOverlay({
          position: latlng,
          content: content,
        });

        // 커스텀 오버레이를 지도에 표시합니다
        customOverlay.setMap(this.map);

        this.customOverlay_list.push(customOverlay);
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
    log() {
      this.displayMarkers(this.list);
    },
    add_schedule(data) {
      if (!this.list.includes(data) && !(this.list.length > 10)) {
        this.list.push(data);
        this.displayMarkers(this.list);
      }
    },
    regist() {
      console.log(this.list);
      this.day_list[this.active_day + 1] = this.list;
    },
    route_upload() {
      axios
        .post(
          `http://localhost:8080/schedule?title=${this.$route.params.title}&scheduleInfo=${this.$route.params.scheduleInfo}`,
          this.day_list
        )
        .then(() => {
          swal("등록 성공!", "", "success");
          this.$router.go(-1);
        });
    },
    list_delete(idx) {
      this.list.splice(idx, 1);
      this.displayMarkers(this.list);
    },
  },
  watch: {
    list(value) {
      this.displayMarkers(value);
    },
  },
};
</script>

<style scoped>
h3 {
  margin: 0px;
}

#header_container {
  display: flex;
  align-items: center;
  justify-content: center;
}

#schedule_title {
  display: flex;
  width: 1200px;
  align-items: center;
  justify-content: space-between;
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
  position: relative;
  height: 60%;
  width: 9%;
  padding: 20px;
  border-radius: 20px 0px 20px 0px;
  font-weight: 600;
  margin: 5px;
  cursor: pointer;
  background-color: #fff;
  color: #fff;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  background-color: rgba(0, 0, 0, 0.4);
  text-shadow: rgb(0, 0, 0) 1px 0 10px;
}

.delete-button {
  position: absolute;
  width: 30px;
  height: 30px;
  top: -10px;
  right: 0px;
  display: none;
}

#element:hover .delete-button {
  display: block;
}

.update_btn {
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

.btn_container {
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
  font-size: 14px;
}
</style>
