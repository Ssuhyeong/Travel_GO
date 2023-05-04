<template>
  <div>
    <table>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작가</th>
        <th>가격</th>
        <th>설명</th>
      </tr>
      <!-- <template v-for="book in data" :key="book.isbn"> -->
      <tr v-for="(book, idx) in data" :key="book.isbn">
        <template v-if="idx % 2 == 0">
          <th>{{ book.isbn }}</th>
          <th>{{ book.title }}</th>
          <th>{{ book.author }}</th>
          <th>{{ book.price }}</th>
          <th>{{ book.description }}</th>
        </template>
      </tr>
      <!-- </template> -->
    </table>
  </div>
</template>

<script>
export default {
  name: "testView",
  data() {
    return {
      data: [],
    };
  },
  mounted() {
    console.log("");
    console.log("[MainComponent] : [mounted] : [start]");
    console.log("설 명 : DOM 렌더링 완료");
    console.log("");

    // [axios http 요청 수행 실시]
    this.$axios
      .get("http://192.168.210.40:9000/api/books")
      .then((res) => {
        console.log("[MainComponent] : [axios] : [response]");
        console.log("응답 데이터 : " + JSON.stringify(res.data));
        this.data = res.data;
        console.log("");
      })
      .catch((error) => {
        console.log("[MainComponent] : [axios] : [error]");
        console.log("에러 데이터 : " + error.data);
      });
  },
};
</script>

<style></style>
