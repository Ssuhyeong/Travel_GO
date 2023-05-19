<template>
  <paginate
    v-if="keyword"
    v-model="page"
    :page-count="totalPage"
    :page-range="5"
    :margin-pages="0"
    :click-handler="changePage"
    :prev-text="'<'"
    :next-text="'>'"
    :container-class="'pagination'"
    :page-class="'page-item'">
  </paginate>
</template>

<script>
import Paginate from "vuejs-paginate-next";
import axios from "@/service/axios"

export default {
  components: {
    paginate: Paginate,
  },
  props: {
    keyword: {
      type: String,
      default: "",
    },
    totalPage: {
      type: Number,
      default: 0,
    },
    type: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      selectPage: 1,
      page: 1,
    };
  },
  methods: {
    changePage(pageNum) {
      this.selectPage = pageNum - 1;
      const page = this.selectPage;
      const keyword = this.keyword;

      if (this.type == "map") {
        const url = `http://localhost:8080/attraction/search-list?keyword=${keyword}&page=${page}`;

        axios
          .get(url)
          .then((res) => {
            this.$emit("setpageList", res.data.content);
          })
          .catch((error) => {
            console.log("검색 실패" + error.data);
          });
      } else if (
        keyword == "board" ||
        keyword == "open-board" ||
        keyword == "faq"
      ) {
        axios
          .get(`http://localhost:8080/${keyword}/search?page=${page}`)
          .then((res) => {
            console.log(res);
            this.$emit("setboardList", res.data.content);
          });
      }
    },
  },
  watch: {
    keyword(cur, prev) {
      if (cur != prev) {
        this.page = 1;
      }
    },
  },
};
</script>

<style>
/* Adopt bootstrap pagination stylesheet. */

/* Write your own CSS for pagination */
.pagination {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 20px;
  margin: 24px;
}
.pagination li {
  min-width: 32px;
  padding: 2px 1px;
  text-align: center;
  margin: 0 3px;
  border-radius: 6px;
  border: 1px solid #eee;
  color: #666;
  list-style-type: none;
  cursor: pointer;
}
.pagination li:hover {
  background: #e4dbd6;
}
.page-item a {
  color: #666;
  text-decoration: none;
  padding: 2px 6px;
}
.pagination li.active {
  background-color: #a4d5df;
  color: #fff;
}
.pagination li.active a {
  color: #fff;
}

.page-item:hover {
  background-color: #31ccf3;
}
</style>
