<template>
  <div>
    <form v-on:submit.prevent="regist">
      <input v-model="book.isbn" type="text" id="isbn" name="isbn" /><label
        for="isbn"></label>
      <br />
      <input v-model="book.title" type="text" id="title" name="title" /><label
        for=""></label>
      <br />
      <input
        v-model="book.author"
        type="text"
        id="author"
        name="author" /><label for=""></label>
      <br />
      <input v-model="book.price" type="number" id="price" name="price" /><label
        for=""></label>
      <br />
      <textarea
        v-model="book.description"
        type="text"
        id="description"
        name="description"></textarea
      ><label for=""></label>
      <br />
      <input type="submit" value="제출하기" @click="search" />
    </form>
    {{ book.isbn }}
  </div>
  <div
    id="info_content"
    style="
      width: 400px;
      height: 200px;
      padding: 40px;
      background-color: #c8c8c8;
    ">
    <h2 style="margin: 10px 0px">북한산둘레길 1구간소나무숲길</h2>
    <div style="margin: 10px 0px">리뷰</div>
    <div style="display: flex; justify-content: space-between">
      <h3>서울 강북구 우이동 산 40-1</h3>
      <img
        src="http://tong.visitkorea.or.kr/cms/resource/31/219131_image2_1.jpg"
        style="width: 150px" />
    </div>
    <a>상세보기</a>
  </div>
</template>

<script>
export default {
  name: "testView",
  components: {},
  data() {
    return {
      book: {
        isbn: "",
        title: "",
        author: "",
        price: 0,
        description: "",
      },
    };
  },
  mounted() {
    // [axios http 요청 수행 실시]
  },
  methods: {
    search() {
      this.$axios
        .get(`http://192.168.210.40:9000/api/books/${this.book.isbn}`)
        .then((res) => {
          console.log("응답 데이터 : " + JSON.stringify(res.data));
          this.data = res.data;
        })
        .catch((error) => {
          console.log("[MainComponent] : [axios] : [error]");
          console.log("에러 데이터 : " + error.data);
        });
    },
  },
};
</script>

<style scoped>
#info_content > * {
  margin: 0px;
  padding: 0px;
}
</style>
