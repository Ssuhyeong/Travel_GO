<template>
  <myNav />
  <div id="schedule_container">
    <section id="schedule_header">
      <div style="padding: 30px; font-weight: 600; font-size: 20px">노호종</div>
    </section>
    <section id="schedule_title">
      <div id="schedule_name">
        <div id="total_day">2코스</div>
        <h1 style="font-size: 48px; margin: 0px">인천여행 가보자</h1>
      </div>
    </section>
    <p id="dist">코스 총거리 : 337.48km</p>
    <section>
      <div id="day_container">
        <div id="day_icon">
          <font-awesome-icon
            :icon="['fas', 'calendar-days']"
            size="lg"
            style="color: #ed4956" />
        </div>
        <div id="day_content">
          <p style="font-size: 12px; color: #777777">일정</p>
          <p style="font-size: 18px; font-weight: 600">2박3일</p>
        </div>
      </div>
    </section>
    <section>
      <div id="schedule_map"></div>
    </section>
    <section>
      <scheduleDivision :list_data="list_data" @day_list="day_list" />
    </section>
  </div>
</template>

<script>
import myNav from "../includes/myNav.vue";
import scheduleDivision from "@/components/scheduleDivision.vue";
import axios from "@/service/axios";

export default {
  components: {
    scheduleDivision,
    myNav,
  },
  data() {
    return {
      map: null,
      markers: [],
      customOverlay_list: [],
      list_data: [],
      test: [],
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

    axios.get(`http://localhost:8080/schedule`).then((res) => {
      console.log(res.data[1]);
      this.list_data = res.data;
      this.displayMarkers(res.data[1]);
    });
  },
  methods: {
    day_list(value) {
      this.displayMarkers(this.list_data[value]);
    },
    initMap() {
      const container = document.getElementById("schedule_map");

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
  },
};
</script>

<style>
#schedule_container > section {
  display: block;
  margin: 0 auto;
  width: 120vh;
}

#schedule_map {
  height: 550px;
}

#schedule_header {
  display: flex;
  align-items: center;
  margin: auto 0;
}

#schedule_title {
  display: flex;
  align-items: center;
  justify-content: center;
}

#schedule_name {
  display: flex;
  align-items: center;
  justify-content: center;
}

#total_day {
  padding: 5px 10px;
  border-radius: 10px;
  font-size: 10px;
  margin-right: 10px;
  color: #fff;
  background-color: #ed4956;
}

#dist {
  font-size: 14px;
  color: #66666c;
  margin: 10px 20px;
  font-weight: 200;
  text-align: center;
  margin: 0px;
}

#day_container {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 20px 10px 40px;
}

#day_icon {
  padding: 15px 17.5px;
  background-color: #f6f6f6;
  border-radius: 100px;
  margin: 10px;
}

#day_content {
  margin: 10px;
}

#day_content > p {
  margin: 0;
}

.label {
  margin-bottom: 96px;
}
.label * {
  display: inline-block;
  vertical-align: top;
}
.label .left {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png")
    no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  vertical-align: top;
  width: 7px;
}
.label .center {
  background: url(https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png)
    repeat-x;
  display: inline-block;
  height: 24px;
  font-size: 12px;
  line-height: 24px;
}
.label .right {
  background: url("https://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px
    0 no-repeat;
  display: inline-block;
  height: 24px;
  overflow: hidden;
  width: 6px;
}
</style>
