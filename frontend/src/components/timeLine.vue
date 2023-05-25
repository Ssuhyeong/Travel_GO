<template>
  <section class="steps steps-area">
    <div class="container">
      <!-- title-section -->
      <ul class="timeline">
        <li
          class="timeline-box wow fadeInRight"
          v-for="(data, idx) in list_data"
          :key="data.content_id"
          @click="
            $router.push({
              name: 'detailpage',
              params: { contentId: data.content_id },
            })
          ">
          <p class="timeline-title">
            {{ data.title }}
            <font-awesome-icon
              :icon="['fas', catagory_spec[data.content_type_id]]"
              size="xs"
              style="
                padding: 5px;
                border-radius: 100px;
                background-color: #fbd2da;
                margin-left: 10px;
              " />
          </p>
          <p class="timeline-details" style="width: 740px">{{ data.addr1 }}</p>
          <span>{{ idx + 1 }}</span>
        </li>
      </ul>
      <div style="display: flex; align-items: center; justify-content: center">
        <font-awesome-icon
          :icon="['fas', 'flag']"
          size="lg"
          style="
            padding: 10px;
            border-radius: 100px;
            background-color: #ff4769;
            color: #fff;
            margin-top: 10px;
            z-index: 3;
          " />
      </div>
    </div>
  </section>
</template>

<script>
export default {
  props: {
    list_data: {
      type: Array,
    },
  },
  data() {
    return {
      catagory_spec: {
        12: "icons",
        14: "place-of-worship",
        15: "축제공연행사",
        25: "champagne-glasses",
        28: "person-swimming",
        32: "hotel",
        38: "bag-shopping",
        39: "utensils",
      },
    };
  },
};
</script>

<style>
.steps {
  position: relative;
}
.steps:before {
  top: 125px;
  bottom: 0;
  position: absolute;
  content: " ";
  width: 2px;
  background-color: #ff4769;
  left: 50%;
  margin-left: -1px;
}

.timeline {
  margin: 0px auto;
  overflow: hidden;
  position: relative;
  padding: 0px;
  list-style-type: none;
}
.timeline .timeline-box {
  position: relative;
  float: left;
  clear: left;
  width: 45%;
  margin: 1em 2.5%;
  list-style-type: none;
  display: block;
  padding: 25px;
  border-radius: 20px;
  background: #f8f8f8;
  cursor: pointer;
  z-index: 15;
}

.timeline .timeline-box .timeline-title {
  color: #3c3c3c;
  font-weight: 700;
  font-size: 1em;
  float: left;
  padding-left: 25px;
  padding-top: 8px;
}
.timeline .timeline-box .timeline-details {
  clear: both;
  padding-top: 5px;
}
.timeline .timeline-box span {
  position: absolute;
  top: -15px;
  right: 24px;
  font-size: 8em;
  font-weight: 700;
  color: #ff4769;
  opacity: 0.1;
  z-index: -1;
}
@media all and (max-width: 600px) {
  .timeline .timeline-box {
    width: 100%;
    margin-left: 0;
    margin-right: 0;
  }
}
.timeline .timeline-box:hover:after {
  background: #ff4769;
}
@media all and (min-width: 650px) {
  .timeline .timeline-box:after {
    display: block;
    content: "   ";
    height: 10px;
    width: 10px;
    background: #fff;
    border-radius: 50%;
    position: absolute;
    right: -6.55%;
    top: 1.5em;
    border: 2px solid #ff4769;
  }
}
.timeline .timeline-box:first-child {
  margin-bottom: 2.5em;
}
.timeline .timeline-box:nth-of-type(1n):before {
  position: absolute;
  top: 15px;
  right: -10px;
  display: inline-block;
  border-top: 10px solid transparent;
  border-left: 10px solid #f8f8f8;
  border-right: 0 solid #f8f8f8;
  border-bottom: 10px solid transparent;
  content: " ";
}
.timeline .timeline-box:nth-of-type(2n) {
  float: right;
  clear: right;
}
.timeline .timeline-box:nth-of-type(2n):before {
  right: auto;
  left: -10px;
  position: absolute;
  top: 15px;
  display: inline-block;
  border-top: 10px solid transparent;
  border-right: 10px solid #f8f8f8;
  border-left: 0 solid #f8f8f8;
  border-bottom: 10px solid transparent;
  content: " ";
}
@media all and (min-width: 600px) {
  .timeline .timeline-box:nth-of-type(2n) {
    margin-top: 3em;
  }
}
.timeline .timeline-box:nth-child(2n):after {
  left: -6.5%;
  right: auto;
}

.timeline-box:hover,
.timeline-box:focus {
  transform: translate(0, -5px);
  -webkit-transform: translate(0, -5px);
  -ms-transform: translate(0, -5px);
}

.timeline-box:hover,
.timeline-box:focus {
  -webkit-box-shadow: 0px 3px 0px 0px #ff4769;
  -moz-box-shadow: 0px 3px 0px 0px #ff4769;
  box-shadow: 0px 3px 0px 0px #ff4769;
}

.timeline-box {
  transition: all 300ms ease-in-out;
  -webkit-transition: all 300ms ease-in-out;
  -moz-transition: all 300ms ease-in-out;
  -o-transition: all 300ms ease-in-out;
}

.timeline-title {
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>
