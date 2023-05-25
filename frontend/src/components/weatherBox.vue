<template>
  <div class="dropdown" id="dropdown" data-open="false">
    <label for="dropdown-toggle" class="dropdown-active">
      <span id="dropdown-selected">{{ areaName }}</span>
      <svg
        class="dropdown-icon"
        viewBox="0 0 448 512"
        width="100"
        title="chevron-down">
        <path
          d="M207.029 381.476L12.686 187.132c-9.373-9.373-9.373-24.569 0-33.941l22.667-22.667c9.357-9.357 24.522-9.375 33.901-.04L224 284.505l154.745-154.021c9.379-9.335 24.544-9.317 33.901.04l22.667 22.667c9.373 9.373 9.373 24.569 0 33.941L240.971 381.476c-9.373 9.372-24.569 9.372-33.942 0z" />
      </svg>
    </label>
    <input
      class="dropdown-toggle"
      type="checkbox"
      name="dropdown-toggle"
      id="dropdown-toggle"
      aria-controls="dropdown-list" />
    <ul class="dropdown-list" id="dropdown-list">
      <li data-option="Seoul" class="active">서울</li>
      <li data-option="Gyeonggi-do">경기도</li>
      <li data-option="Gyeongsangnam-do">경상남도</li>
      <li data-option="Gyeongsangbuk-do">경상북도</li>
      <li data-option="Jeollanam-do">전라남도</li>
      <li data-option="Jeollabuk-do">전라북도</li>
      <li data-option="Chungcheongnam-do">충청남도</li>
      <li data-option="Chungcheongbuk-do">충청북도</li>
      <li data-option="Gangwon-do">강원도</li>
      <li data-option="Jeju-do">제주도</li>
    </ul>
    <div
      style="
        border-left: 1px solid #edeffb;
        padding: 10px;
        display: flex;
        align-items: center;
        justify-content: space-between;
      ">
      <font-awesome-icon :icon="['fas', weather_icon]" style="padding: 5px" />
      {{ Math.round((weather_temp + Number.EPSILON) * 100) / 100 }}°C
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "weatherBox",
  props: ["weatherArea"],
  data() {
    return {
      weatherSelect: "Seoul",
      weather_temp: 0,
      weather_icon: "sun",
      weatherIcon: {
        "01": "sun",
        "02": "cloud-sun",
        "03": "cloud",
        "04": "cloud-meatball",
        "09": "cloud-sun-rain",
        10: "cloud-showers-heavy",
        11: "poo-storm",
        13: "snowflake",
        50: "smog",
      },
      areaName: "서울",
    };
  },
  created() {
    this.weather(this.weatherSelect);
  },
  mounted() {
    const dropdownElem = document.getElementById("dropdown");
    const dropdownListElem = document.getElementById("dropdown-list");
    const dropdownSelectElem = document.getElementById("dropdown-selected");
    const dropdownOptionElems = dropdownListElem.querySelectorAll("li");
    const dropdownToggle = document.getElementById("dropdown-toggle");

    dropdownListElem.addEventListener("click", (e) => {
      console.log(e.target);
      // check if user clicked on options
      if (e.target.dataset.option) {
        // first, remove all highlighted option
        dropdownOptionElems.forEach((option) => {
          option.classList.remove("active");
        });

        // ...then, highlight the selected option
        e.target.classList.add("active");
        this.weatherSelect = e.target.getAttribute("data-option");

        // update the text to selected option
        dropdownSelectElem.innerHTML = e.target.textContent;

        // close the dropdown
        dropdownToggle.checked = false;
        dropdownElem.setAttribute("data-open", false);
      }
    });
  },
  methods: {
    weather(area) {
      axios
        .get(
          `http://api.openweathermap.org/data/2.5/weather?q=${area}&appid=384a415ea57be7bea2f1fd5bd6f55f7e`
        )
        .then((res) => {
          // console.log(res);
          // console.log("현재온도 : " + (res.data.main.temp - 273.15));
          // console.log("현재습도 : " + res.data.main.humidity);
          // console.log("날씨 : " + res.data.weather[0].main);
          // console.log("상세날씨설명 : " + res.data.weather[0].description);
          // console.log("날씨 이미지 : " + res.data.weather[0].icon.substr(0, 2));
          // console.log("바람   : " + res.data.wind.speed);
          // console.log("나라   : " + res.data.sys.country);
          // console.log("도시이름  : " + res.data.name);
          // console.log("구름  : " + res.data.clouds.all + "%");

          this.weather_temp = res.data.main.temp - 273.15;
          this.weather_icon =
            this.weatherIcon[res.data.weather[0].icon.substr(0, 2)];
        });
    },
  },
  watch: {
    weatherSelect(value) {
      this.weather(value);
    },
    weatherArea(value) {
      // 좌표를 통해 해당 지역의 날씨를 파악하는 코드
      // this.areaPos.forEach((pos, idx) => {
      //   if (pos.La[0] <= value.La && pos.La[1] >= value.La) {
      //     if (pos.Ma[0] <= value.Ma && pos.Ma[1] >= value.Ma) {
      //       console.log(this.area[idx]);
      //       this.weather(this.area[idx]);
      //       return false;
      //     }
      //   }
      // });

      // 지역명을 이용한 지역의 날씨를 파악하는 코드
      if (value == "서울특별시" || value == "서울") {
        this.areaName = "서울";
        this.weather("Seoul");
      } else if (value == "경기도" || value == "인천특별시") {
        this.areaName = "경기도";
        this.weather("Gyeonggi-do");
      } else if (value == "강원도") {
        this.areaName = "강원도";
        this.weather("Gangwon-do");
      } else if (
        value == "충청남도" ||
        value == "충남" ||
        value == "대전광역시" ||
        value == "대전"
      ) {
        this.areaName = "충청남도";
        this.weather("Chungcheongnam-do");
      } else if (value == "충청북도" || value == "충북") {
        this.areaName = "충청북도";
        this.weather("Chungcheongbuk-do");
      } else if (value == "전라북도" || value == "전북") {
        this.areaName = "전라북도";
        this.weather("Jeollabuk-do");
      } else if (
        value == "전라남도" ||
        value == "전남" ||
        value == "광주광역시"
      ) {
        this.areaName = "전라남도";
        this.weather("Jeollanam-do");
      } else if (value == "경상북도" || value == "경북") {
        this.areaName = "경상북도";
        this.weather("Gyeongsangbuk-do");
      } else if (
        value == "경상남도" ||
        value == "경남" ||
        value == "부산" ||
        value == "부산광역시"
      ) {
        this.areaName = "경상남도";
        this.weather("Gyeongsangnam-do");
      } else if (value == "제주특별자치도") {
        this.areaName = "제주도";
        this.weather("Jeju-do");
      }
    },
  },
};
</script>

<style scoped>
#dropdown-selected {
  margin-right: 10px;
}

select,
input,
textarea,
button {
  line-height: 1;
  width: 100%;
}

svg {
  height: 2ex;
  width: auto;
  flex: none;
  fill: currentColor;
}

/* dropdown */
.dropdown {
  --_gap: 0.5rem;
  border-radius: 0.5rem;
  display: inline-flex;
  align-items: center;
  border: 1px solid #edeffb;
  position: fixed;
  top: 77px;
  left: 420px;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
  z-index: 2;
  background-color: #fff;
  margin: 10px;
}

.dropdown[data-open="true"] {
  background-color: #edeffb;
}

.dropdown-toggle {
  display: none;
}

.dropdown-active {
  display: inline-flex;
  align-items: center;
  justify-content: space-between;
  flex-grow: 1;
  gap: 0.25rem;
  padding: 1rem;
}

.dropdown-icon {
  transition: transform 150ms ease;
}

.dropdown[data-open="true"] .dropdown-icon {
  transform: rotate(-0.5turn);
}

.dropdown-list {
  position: absolute;
  top: calc(100%);
  left: 0;
  right: 0;
  padding: 1rem;
  list-style: none;
  background-color: #fff;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
  border-radius: 1rem;

  display: none;
}

.dropdown-toggle:checked + .dropdown-list {
  display: block;
}

.dropdown-list li {
  padding: 1rem;
  border-radius: 0.5rem;
  cursor: pointer;
}

.dropdown-list li:hover {
  background-color: #fafaff;
}

.dropdown-list li.active {
  background-color: #edeffb;
}

@media (width > 40em) {
  .wrapper {
    grid-template-columns: auto 1fr;
  }

  .wrapper::before {
    top: -50%;
    left: 10%;
    width: 60%;
    aspect-ratio: 1 / 1;
  }

  .dropdown-list {
    width: max-content;
  }
}
</style>
