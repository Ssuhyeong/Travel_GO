<template>
  <div>
    <myNav />
    <div id="map"><mapCard style="z-index: 2" /></div>
    <button @click="displayMarkers(this.markerPositions1)">클릭</button>
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
      markerPositions1: [
        {
          latlng: new kakao.maps.LatLng(33.452278, 126.567803)
        },
        {
          latlng: new kakao.maps.LatLng(33.452671, 126.574792)
        },
        {
          latlng: new kakao.maps.LatLng(33.451744, 126.572441)
        }
      ],
      markers: [],
      infowindow: null,
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
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=915cffed372954b7b44804ed422b9cf0";
      document.head.appendChild(script);
    }
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
      // 여러개 마커를 정보를 보여줄 info window
      if(this.markers.length > 0) {
        this.markers.forEach((item) => {
          item.setMap(null);
        })
      }

      positions.forEach((pos) => {

        this.infowindow = new kakao.maps.InfoWindow({
          removable: true,
          content: `<div style="padding: 30px; width:300px; height: 200px">테스트<div>`,
        });

        const marker = new kakao.maps.Marker({
          map: this.map,
          position: pos.latlng
        });

        kakao.maps.event.addListener(marker, "click", () => {
          this.infowindow.open(this.map, marker);
        });

        this.markers.push(marker);
      })

      const bounds = positions.reduce(
        (bounds, position) => bounds.extend(position.latlng),
        new kakao.maps.LatLngBounds()
      );

      this.map.setBounds(bounds);
    }
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
