<template>
  <div class="card">
    <div class="card-body">
      <mapSearchbar />
      <div class="card_content">
        <div id="info_around">
          <p style="margin:10px 0px">주변탐색</p>
          <div id="info_list">
            <infoIcon/>
            <infoIcon/>
            <infoIcon/>
            <infoIcon/>
            <infoIcon/>
            <infoIcon/>
            <infoIcon/>
          </div>
        </div>
        <hr />
        <div class="place_num">
          <p>장소</p>
          <p style="margin-left:10px; color: #696969">234, 000건</p>
        </div>
        <div id="content_list">
          <placeContent/>
          <placeContent/>
          <placeContent/>
          <placeContent/>
          <placeContent/>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import infoIcon from './infoIcon.vue';
import mapSearchbar from "./mapSearchbar.vue";
import placeContent from './placeContent.vue';

export default {
  name: "cardMap",
  components: {
    mapSearchbar,
    infoIcon,
    placeContent
  },
};
</script>

<style scoped>
hr {
    background:#dbdbdb;
    height:1px;
    border:0;
}
.card {
  position: relative;
  width: 400px;
  height: 97%;
  border: 1px solid #ddd;
  background-color: #fff;
  border-radius: 5px;
  margin: 20px 10px;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
}

.card_content {
  position: absolute;
  height: calc(100% - 160.86px);
  width: 100%;
  overflow-y: auto;
}

.card_content::-webkit-scrollbar-track {
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  border-radius: 10px;
  background-color: #f5f5f5;
}

.card_content::-webkit-scrollbar {
  width: 8px;
  background-color: #f5f5f5;
}

.card_content::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
  background-color: #888888;
}

#info_around{
  padding: 30px;
}

#info_list {
  display: flex;
  align-items: center;
  justify-content: space-between;
}


.card img {
  height: 200px;
  object-fit: cover;
}

.place_num {
  display: flex;
  align-items: center;
  padding: 10px 30px;
}

.card-body {
}

.card-title {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}

.card-text {
  font-size: 1rem;
  color: #555;
  margin-bottom: 1rem;
}
</style>
