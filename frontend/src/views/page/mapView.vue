<template>
  <div>
    <myNav />
    <div id="map">
      <mapCard
        style="z-index: 2"
        @setContentList="setContentList"
        @setCategoryNum="setCategoryNum" />
      <weatherBoxVue :weatherArea="weatherArea" />
    </div>
  </div>
</template>

<script>
import myNav from "../includes/myNav.vue";
import mapCard from "@/components/mapCard.vue";
import { toRaw } from "vue";
import weatherBoxVue from "@/components/weatherBox.vue";
import axios from "axios";

export default {
  name: "KakaoMap",
  components: {
    myNav,
    mapCard,
    weatherBoxVue,
  },
  data() {
    return {
      map: null,
      cnt: 0,
      markerData: [],
      categoryData: [],
      markers: [],
      categoryMarkers: [],
      infowindow: null,
      categoryNum: 0,
      categoryCode: ["PM9", "OL7", "CE7", "AD5", "CS2", "MT1"],
      weatherArea: "",
      placeOverlay: null,
      contentNode: null,
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
  },
  methods: {
    setContentList(value) {
      this.markerData = value;

      console.log(this.markerData);

      this.displayMarkers(value);
    },
    setCategoryNum(value) {
      this.categoryNum = value;

      if (this.categoryMarkers.length > 0) {
        this.categoryMarkers.forEach((item) => {
          item.setMap(null);
        });
      }

      this.categoryData = [];

      if (value != 0) {
        this.displayCategory(value);
      }
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

      const locationLoadSuccess = (pos) => {
        const currentPos = new kakao.maps.LatLng(
          pos.coords.latitude,
          pos.coords.longitude
        );
        this.map.panTo(currentPos);
      };
      navigator.geolocation.getCurrentPosition(locationLoadSuccess);

      // // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      // var mapTypeControl = new kakao.maps.MapTypeControl();

      // // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      // this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOCENTER);

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      var zoomControl = new kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
    },
    displayMarkers(positions) {
      // 여러개 마커를 정보를 보여줄 info window
      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      // 마커를 생성하고 지도 위에 마커를 표시
      positions.forEach((pos, idx) => {
        this.infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content: ``,
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

        kakao.maps.event.addListener(marker, "click", () => {
          var content = `<div style="width: 400px;
      height: 200px;
      padding: 40px;"><h2 style="margin: 10px 0px">${positions[idx].title}</h2>
    <div style="margin: 10px 0px">리뷰</div>
    <div style="display: flex; justify-content: space-between">
      <h3>${positions[idx].addr1}</h3>
      <img
        src="${positions[idx].first_image}"
        style="width: 150px" />
    </div>`;

          this.weatherArea = pos.addr1.split(" ")[0];

          this.infowindow.setContent(content);
          const level = 4;
          this.map.setLevel(level);
          this.map.setCenter(latlng);

          this.infowindow.open(this.map, marker);
        });

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
    displayCategory(categoryNum) {
      const currentPos = this.map.getCenter();
      const categoryCode = this.categoryCode[categoryNum - 1];

      for (let i = 1; i < 21; i++) {
        axios({
          url: `https://dapi.kakao.com/v2/local/search/category.json?x=${currentPos.La}&y=${currentPos.Ma}&category_group_code=${categoryCode}&page=${i}&radius=20000`,
          headers: {
            Authorization: `KakaoAK 68435c839ca94f29d68df0ab5c378c16`,
          },
        })
          .then((res) => {
            this.cnt++;
            console.log(res.data.documents);
            res.data.documents.forEach((data) => this.categoryData.push(data));
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
    displayCategoryMarkers(positions) {
      // 마커를 생성하고 지도 위에 마커를 표시
      positions.forEach((pos) => {
        const imgNum = this.categoryNum;

        // 이미지 초기화
        const imageSrc = require(`../../assets/img/markerCategory_${imgNum}.png`), // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(20, 32), // 마커 이미지의 크기
          imgOptions = {
            // spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
            // spriteOrigin: new kakao.maps.Point(46, (this.categoryNum - 1) * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 34), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
          },
          markerImage = new kakao.maps.MarkerImage(
            imageSrc,
            imageSize,
            imgOptions
          );

        const latlng = new kakao.maps.LatLng(pos.y, pos.x);

        const marker = new kakao.maps.Marker({
          map: this.map,
          position: latlng,
          image: markerImage,
        });

        this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });

        this.contentNode = document.createElement("div");

        this.contentNode.className = "placeinfo_wrap";

        this.placeOverlay.setContent(this.contentNode);

        this.placeOverlay.setMap(null);

        kakao.maps.event.addListener(marker, "mouseover", () => {
          this.displayPlaceInfo(pos);
        });
        kakao.maps.event.addListener(marker, "mouseout", () => {
          this.placeOverlay.setMap(null);
        });

        this.categoryMarkers.push(marker);
      });
    },
    displayPlaceInfo(place) {
      var content =
        '<div class="placeinfo">' +
        '   <a class="title" href="' +
        place.place_url +
        '" target="_blank" title="' +
        place.place_name +
        '">' +
        place.place_name +
        "</a>";

      if (place.road_address_name) {
        content +=
          '    <span title="' +
          place.road_address_name +
          '">' +
          place.road_address_name +
          "</span>" +
          '  <span class="jibun" title="' +
          place.address_name +
          '">(지번 : ' +
          place.address_name +
          ")</span>";
      } else {
        content +=
          '    <span title="' +
          place.address_name +
          '">' +
          place.address_name +
          "</span>";
      }

      content +=
        '    <span class="tel">' +
        place.phone +
        "</span>" +
        "</div>" +
        '<div class="after"></div>';

      this.contentNode.innerHTML = content;
      this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
      this.placeOverlay.setMap(this.map);
    },
  },
  watch: {
    cnt(current) {
      if (current == 20) {
        this.displayCategoryMarkers(toRaw(this.categoryData));
        this.cnt = 0;
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
#map {
  height: calc(100vh - 4.1em);
  place-items: center;
  overflow: hidden;
  display: flex;
  align-items: center;
  position: relative;
  align-items: center;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}

div {
  border: none;
}

.placeinfo_wrap {
  position: absolute;
  bottom: 28px;
  left: -150px;
  width: 300px;
}

.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 2px;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.placeinfo a,
.placeinfo a:hover,
.placeinfo a:active {
  color: #fff;
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 5px 5px 0 5px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 2px 2px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: #fff;
  background: #d95050;
  background: #d95050
    /* url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) */
    no-repeat right 14px center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
