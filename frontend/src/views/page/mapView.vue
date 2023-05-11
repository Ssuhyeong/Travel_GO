<template>
  <div>
    <myNav />
    <div id="map">
      <mapCard
        style="z-index: 2"
        @setContentList="setContentList"
        @setCategoryNum="setCategoryNum" />
    </div>
  </div>
</template>

<script>
import myNav from "../includes/myNav.vue";
import mapCard from "@/components/mapCard.vue";

export default {
  name: "KakaoMap",
  components: {
    myNav,
    mapCard,
  },
  data() {
    return {
      map: null,
      markerData: [],
      categoryData: [],
      markers: [],
      infowindow: null,
      categoryNum: 0,
      categoryCode: ["PM9", "OL7", "CE7", "AD5", "CS2", "MT1"],
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

      console.log(value);

      this.displayMarkers(value);
    },
    setCategoryNum(value) {
      this.categoryNum = value;
      if (value != 0) {
        this.displayCategory(value);
      } else {
        this.categoryData = [];
      }

      this.displayMarkers(this.categoryData);
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

      // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
      var mapTypeControl = new kakao.maps.MapTypeControl();

      // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
      // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
      this.map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

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

          this.infowindow.setContent(content);
          const level = 4;
          this.map.setLevel(level);
          this.map.setCenter(latlng);

          this.infowindow.open(this.map, marker);
        });

        this.markers.push(marker);
      });

      const bounds = positions.reduce(
        (bounds, position) =>
          bounds.extend(
            new kakao.maps.LatLng(position.latitude, position.longitude)
          ),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    },
    displayCategory(categoryNum) {
      const categoryCode = this.categoryCode[categoryNum - 1];

      for (let i = 1; i < 46; i++) {
        this.$axios({
          url: `https://dapi.kakao.com/v2/local/search/category.json?category_group_code=${categoryCode}&page=${i}`,
          headers: {
            Authorization: `KakaoAK 68435c839ca94f29d68df0ab5c378c16`,
          },
        }).then((res) => {
          res.data.documents.forEach((data) => this.categoryData.push(data));
        });
      }

      this.displayCategoryMarkers(this.categoryData);
    },
    displayCategoryMarkers(positions) {
      // 여러개 마커를 정보를 보여줄 info window

      if (this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        });
      }

      console.log(positions[1]);

      // 마커를 생성하고 지도 위에 마커를 표시
      positions.forEach((pos) => {
        console.log(pos);

        this.infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content: `<div>안녕<div>`,
        });

        const latlng = new kakao.maps.LatLng(pos.x, pos.y);

        const marker = new kakao.maps.Marker({
          map: this.map,
          position: latlng,
        });

        this.markers.push(marker);
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map {
  align-items: center;
  background-color: var(--white);
  background-attachment: fixed;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
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
</style>
