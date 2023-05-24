<template>
  <myNav />
  <div id="schedule_container">
    <section id="schedule_header">
      <div
        style="
          padding: 30px;
          font-weight: 600;
          font-size: 20px;
          display: flex;
          align-self: center;
          justify-content: space-between;
        ">
        {{ this.$route.params.user }}
        <button class="delete_btn" @click="delete_route()">
          <font-awesome-icon :icon="['fas', 'trash-can']" />
        </button>
      </div>
    </section>
    <section id="schedule_title">
      <div id="schedule_name">
        <div id="total_day">{{ route_cnt }}코스</div>
        <h1 style="font-size: 48px; margin: 0px">
          {{ this.$route.params.title }}
        </h1>
      </div>
    </section>
    <p id="dist">코스 총거리 : {{total_dist.toFixed(2)}}km</p>
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
          <p style="font-size: 18px; font-weight: 600">
            {{ day_cnt }}박{{ day_cnt + 1 }}일
          </p>
        </div>
      </div>
    </section>
    <section>
      <div id="schedule_map"></div>
    </section>
    <section>
      <scheduleDivision
        :list_data="list_data"
        :day_cnt="day_cnt"
        @day_list="day_list" />
    </section>
  </div>
</template>

<script>
import myNav from "../includes/myNav.vue";
import scheduleDivision from "@/components/scheduleDivision.vue";
import axios from "@/service/axios";
import { useStore } from "vuex";

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
      list_data: [null, [], [], [], [], [], [], []],
      test: [],
      day_cnt: 0,
      route_cnt: 0,
      total_dist: 0
    };
  },
  setup() {
    const store = useStore();
    const setShow = () => store.commit("setShow", true);
    const setText = () => store.commit("setText", "성공적으로 삭제하였습니다.");
    const setColor = () => store.commit("setColor", "#f44040");

    return { setShow, setText, setColor };
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
      .get(`http://localhost:8080/travel/${this.$route.params.scheduleInfo}`)
      .then((res) => {
        for (let i = 0; i < res.data.length; i++) {
          this.list_data[res.data[i].day].push(res.data[i].attraction);
          
          if(i != 0 && res.data[i].day == res.data[i-1].day) {
            this.total_dist += this.calculateDistance(res.data[i-1].attraction.latitude, res.data[i-1].attraction.longitude, res.data[i].attraction.latitude, res.data[i].attraction.longitude);
          }
          
          this.route_cnt++;
          if (this.day_cnt < res.data[i].day) {
            this.day_cnt = res.data[i].day;
          }
        }
        this.displayMarkers(this.list_data[1]);
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
    delete_route() {
      axios
        .delete(
          `http://localhost:8080/travel?scheduleInfo=${this.$route.params.scheduleInfo}`
        )
        .then(() => {
          this.setShow();
          this.setText();
          this.setColor();
          this.$router.go(-1);
        });
    },
    calculateDistance(lat1, lon1, lat2, lon2) {
      const degToRad = (deg) => {
        return deg * (Math.PI / 180);
      };

      const earthRadius = 6371; // Earth's radius in kilometers

      const dLat = degToRad(lat2 - lat1);
      const dLon = degToRad(lon2 - lon1);

      const a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(degToRad(lat1)) *
          Math.cos(degToRad(lat2)) *
          Math.sin(dLon / 2) *
          Math.sin(dLon / 2);

      const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

      const distance = earthRadius * c;
      return distance;
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
  border-radius: 20px;
}

#schedule_header {
  display: flex;
  align-items: center;
  margin: auto 0;
}

#schedule_title {
  display: flex;
  align-items: center;
  justify-content: left;
}

#schedule_name {
  display: flex;
  align-items: center;
  justify-content: center;
}

#total_day {
  padding: 5px 10px;
  border-radius: 10px;
  font-size: 14px;
  margin-right: 10px;
  color: #fff;
  background-color: #ed4956;
}

#dist {
  font-size: 14px;
  color: #66666c;
  margin: 10px 50px;
  font-weight: 200;
  text-align: center;
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

.delete_btn {
  border: none;
  color: #fff;
  background: #ed4956;
  appearance: none;
  font: inherit;
  font-size: 1.1rem;
  padding: 0.4em 0.6em;
  border-radius: 0.3em;
  cursor: pointer;
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
