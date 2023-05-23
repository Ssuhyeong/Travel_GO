<template>
  <myNav />
  <div id="map">
    <div class="card">
      <div
        class="place_content"
        v-for="data in list_data"
        :key="data.content_id">
        <div class="place_title">
          <h3>{{ data.title }}</h3>
          <p>{{ catagory_spec[data.content_type_id] }}</p>
        </div>
        <div class="addr_main">{{ data.addr1 }}</div>
      </div>
    </div>
  </div>
  <scheduleDivisionUpdate :list_data="list_data" />
</template>

<script>
import scheduleDivisionUpdate from "@/components/scheduleDivisionUpdate.vue";
import myNav from "../includes/myNav.vue";
import axios from "@/service/axios";

export default {
  components: {
    scheduleDivisionUpdate,
    myNav,
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

    console.log(this.list_data);
  },
  methods: {
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
  },
};
</script>

<style>
#map {
  height: 500px;
  width: 1200px;
  margin: 0 auto;
  border-radius: 20px;
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
  padding: 10px 30px;
  border-bottom: 1px solid #dbdbdb;
}
.place_content:hover {
  background-color: #d9ecf0;
}

.place_title {
  display: flex;
  align-items: center;
}

.place_title > p {
  color: #9c9c9c;
  font-size: 12px;
  margin-left: 6px;
}
</style>
