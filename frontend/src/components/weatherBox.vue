<template>
  <div class="dropdown" id="dropdown" data-open="false">
    <label for="dropdown-toggle" class="dropdown-active">
      <span id="dropdown-selected">서울</span>
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
  </div>
</template>

<script>
export default {
  mounted() {
    const dropdownElem = document.getElementById("dropdown");
    const dropdownListElem = document.getElementById("dropdown-list");
    const dropdownSelectElem = document.getElementById("dropdown-selected");
    const dropdownOptionElems = dropdownListElem.querySelectorAll("li");
    const dropdownToggle = document.getElementById("dropdown-toggle");


    dropdownListElem.addEventListener("click", (e) => {
      // check if user clicked on options
      if (e.target.dataset.option) {
        // first, remove all highlighted option
        dropdownOptionElems.forEach((option) => {
          option.classList.remove("active");
        });

        // ...then, highlight the selected option
        e.target.classList.add("active");
        console.log(e.target.getAttribute("data-option"));

        // update the text to selected option
        dropdownSelectElem.innerHTML = e.target.textContent;

        // close the dropdown
        dropdownToggle.checked = false;
        dropdownElem.setAttribute("data-open", false);
      }
    });
  },
  methods: {
    weather() {
      this.$axios
        .get(
          `http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=384a415ea57be7bea2f1fd5bd6f55f7e`
        )
        .then((res) => {
          console.log(res);
          console.log("현재온도 : " + (res.data.main.temp - 273.15));
          console.log("현재습도 : " + res.data.main.humidity);
          console.log("날씨 : " + res.data.weather[0].main);
          console.log("상세날씨설명 : " + res.data.weather[0].description);
          console.log("날씨 이미지 : " + res.data.weather[0].icon);
          console.log("바람   : " + res.data.wind.speed);
          console.log("나라   : " + res.data.sys.country);
          console.log("도시이름  : " + res.data.name);
          console.log("구름  : " + res.data.clouds.all + "%");
        });
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
  position: relative;
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
  box-shadow: 0px 16px 15px rgba(17, 16, 60, 0.1);
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
