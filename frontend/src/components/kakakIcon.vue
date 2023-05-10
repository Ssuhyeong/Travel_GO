<template>
  <span class="markerbg" :class="marker_num"></span>
</template>

<script>
export default {
  props: {
    marker_num: {
      type: String,
    },
  },
};
</script>

<style scoped>
.markerbg {
  float: left;
  width: 36px;
  height: 38px;
  margin-top: 5px;
  margin-right: 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
.marker_1 {
  background-position: 0 -10px;
}
.marker_2 {
  background-position: 0 -56px;
}
.marker_3 {
  background-position: 0 -102px;
}
.marker_4 {
  background-position: 0 -148px;
}
.marker_5 {
  background-position: 0 -194px;
}
.marker_6 {
  background-position: 0 -240px;
}
.marker_7 {
  background-position: 0 -286px;
}
.marker_8 {
  background-position: 0 -332px;
}
.marker_9 {
  background-position: 0 -378px;
}
.marker_10 {
  background-position: 0 -423px;
}
.marker_11 {
  background-position: 0 -470px;
}
.marker_12 {
  background-position: 0 -516px;
}
.marker_13 {
  background-position: 0 -562px;
}
.marker_14 {
  background-position: 0 -608px;
}
.marker_15 {
  background-position: 0 -654px;
}
</style>
