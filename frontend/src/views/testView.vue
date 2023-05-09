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

<style></style>
