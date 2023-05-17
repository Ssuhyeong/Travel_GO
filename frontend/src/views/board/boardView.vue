<template>
  <myNav />
  <toastNotice :message="toastText" v-if="toastShow" />
  <!-- partial:index.partial.html -->
  <section class="notice">
    <div class="page-title">
      <div class="container">
        <h3 style="font-size: 45px; color: #00859c">
          {{ boardtitle[boardtype] }}
        </h3>
      </div>
      <div id="categoryBoard">
        <div
          class="board_page"
          :class="{ category_active: active_list[0] }"
          @click="
            [
              select(0),
              $router.push({ name: 'boardView', params: { type: 'board' } }),
            ]
          ">
          공지사항
        </div>
        <div
          class="board_page"
          :class="{ category_active: active_list[1] }"
          @click="
            [
              select(1),
              $router.push({
                name: 'boardView',
                params: { type: 'open-board' },
              }),
            ]
          ">
          열린 게시판
        </div>
        <div
          class="board_page"
          :class="{ category_active: active_list[2] }"
          @click="
            [
              select(2),
              $router.push({ name: 'boardView', params: { type: 'faq' } }),
            ]
          ">
          FAQ
        </div>
      </div>
    </div>

    <div v-if="boardtype == 'faq'"><faqBoard /></div>
    <div v-else>
      <!-- board seach area -->
      <div id="board-search">
        <div class="container">
          <div class="search-window">
            <div class="search-wrap">
              <label for="search" class="blind">공지사항 내용 검색</label>
              <input
                id="search"
                type="search"
                name=""
                placeholder="검색어를 입력해주세요."
                v-model="searchKeyword"
                @keyup.enter="search()" />
              <button class="btn btn-dark" @click="search()">검색</button>
            </div>
          </div>
        </div>
      </div>

      <div id="board-list">
        <div class="container">
          <table class="board-table">
            <thead>
              <tr>
                <th scope="col" class="th-num">번호</th>
                <th scope="col" class="th-title">제목</th>
                <th scope="col" class="th-date">등록일</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(board, idx) in board_list" :key="board.articleNo">
                <td>{{ idx + 1 }}</td>
                <th>
                  <a @click="goDetail(board.articleNo)">{{ board.subject }}</a>
                </th>
                <td>{{ board.registerTime }}</td>
              </tr>
            </tbody>
          </table>
          <paginationComponent
            :totalPage="totalpage"
            @setboardList="setboardList"
            :keyword="boardtype"
            type="board" />
          <div id="submit_btn">
            <button
              @click="
                $router.push({
                  name: 'boardwritepage',
                  params: { type: boardtype, articleNo: 5000 },
                })
              "
              type="button"
              id="btn-list"
              class="custom-btn btn-16">
              글등록
            </button>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import myNav from "@/views/includes/myNav.vue";
import toastNotice from "@/components/toastNotice.vue";
import paginationComponent from "@/components/paginationComponent.vue";
import faqBoard from "@/components/faqBoard.vue";
import { useStore } from "vuex";
import { computed } from "vue";

export default {
  name: "boardView",
  components: {
    myNav,
    toastNotice,
    paginationComponent,
    faqBoard,
  },
  data() {
    return {
      board_list: [],
      active_list: [true, false, false],
      toastShow: false,
      toastText: "",
      searchKeyword: "",
      totalpage: 0,
      boardtype: "",
      boardtitle: {
        board: "공지사항",
        "open-board": "열린 게시판",
        faq: "FAQ",
      },
    };
  },
  setup() {
    const store = useStore();
    const toastTextResult = computed(() => store.state.text);
    const toastShowResult = computed(() => store.state.toastShow);

    return { toastTextResult, toastShowResult };
  },
  created() {
    this.boardtype = this.$route.params.type;
    if (this.boardtype == "board") {
      this.select(0);
    } else if (this.boardtype == "open-board") {
      this.select(1);
    } else if (this.boardtype == "faq") {
      this.select(2);
    }
    this.createBoard(this.boardtype);
  },
  methods: {
    setboardList(value) {
      this.board_list = value;
    },
    createBoard(type) {
      this.toastText = this.toastTextResult;
      this.toastShow = this.toastShowResult;

      this.$axios.get(`http://localhost:8080/${type}/search`).then((res) => {
        this.board_list = res.data.content;
        this.totalpage = res.data.totalPages;
      });
    },
    goDetail(articleNo) {
      this.$router.push({
        name: "boardcontentpage",
        params: { type: this.boardtype, articleNo: articleNo },
      });
    },
    search() {
      const keyword = this.searchKeyword;
      this.sendKeyword = keyword;

      this.$axios
        .get(
          `http://localhost:8080/${this.boardtype}/search?keyword=${keyword}`
        )
        .then((res) => {
          this.board_list = res.data.content;
          this.totalpage = res.data.totalPages;
        });
    },
    select(idx) {
      if (this.active_list[idx]) {
        console.log("none");
      } else {
        for (let i = 0; i < this.active_list.length; i++) {
          if (i != idx) {
            this.active_list[i] = this.active_list[idx];
          }
        }
        this.active_list[idx] = !this.active_list[idx];
      }
    },
  },
  watch: {
    "$route.params.type"(value) {
      this.boardtype = value;
      if (this.boardtype == "board") {
        this.select(0);
      } else if (this.boardtype == "open-board") {
        this.select(1);
      } else if (this.boardtype == "faq") {
        this.select(2);
      }
      console.log(value);
      this.createBoard(value);
    },
  },
};
</script>

<style scoped>
table {
  border-collapse: collapse;
  border-spacing: 0;
}
section.notice {
  padding: 80px 0;
}

a {
  cursor: pointer;
}

.page-title {
  margin-bottom: 60px;
}
.page-title h3 {
  font-size: 28px;
  color: #333333;
  font-weight: 400;
  text-align: center;
  margin: 0px;
}

#categoryBoard {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 40px 0px 0px;
  width: 1100px;
  margin: 0 auto;
}
.board_page {
  padding: 10px 20px;
  margin: 10px;
  background-color: #b1b1b1;
  color: #fff;
  border-radius: 20px;
  font-weight: 400;
}

#categoryBoard > div:hover {
  background-color: #4f4f4f;
  cursor: pointer;
}

.category_active {
  background-color: #4f4f4f;
}

#board-search .search-window {
  padding: 15px 0;
  background-color: #f9f7f9;
}
#board-search .search-window .search-wrap {
  position: relative;
  /*   padding-right: 124px; */
  margin: 0 auto;
  width: 80%;
  max-width: 564px;
}
#board-search .search-window .search-wrap input {
  height: 40px;
  width: 100%;
  font-size: 14px;
  font-family: cookierun;
  padding: 7px 115px 7px 14px;
  border: 1px solid #ccc;
}
#board-search .search-window .search-wrap input:focus {
  border-color: #333;
  outline: 0;
  border-width: 1px;
}
#board-search .search-window .search-wrap .btn {
  position: absolute;
  right: 0;
  top: 0;
  bottom: 0;
  width: 108px;
  padding: 0;
  font-size: 16px;
}

#submit_btn {
  width: 1100px;
  display: flex;
  align-items: center;
  justify-content: right;
}

.board-table {
  font-size: 13px;
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.board-table a {
  color: #333;
  display: inline-block;
  line-height: 1.4;
  word-break: break-all;
  vertical-align: middle;
}
.board-table a:hover {
  text-decoration: underline;
}
.board-table th {
  text-align: center;
}

.board-table .th-num {
  width: 100px;
  text-align: center;
}

.board-table .th-date {
  width: 200px;
}

.board-table th,
.board-table td {
  padding: 14px 0;
}

.board-table tbody td {
  border-top: 1px solid #e7e7e7;
  text-align: center;
}

.board-table tbody th {
  padding-left: 28px;
  padding-right: 14px;
  border-top: 1px solid #e7e7e7;
  text-align: left;
}

.board-table tbody th p {
  display: none;
}

.btn {
  display: inline-block;
  padding: 0 30px;
  font-size: 15px;
  font-weight: 400;
  font-family: cookierun;
  background: transparent;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  -ms-touch-action: manipulation;
  touch-action: manipulation;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  border: 1px solid transparent;
  text-transform: uppercase;
  -webkit-border-radius: 0;
  -moz-border-radius: 0;
  border-radius: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -ms-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-sub {
  height: 40px;
  margin: 20px;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

.btn-dark {
  background: #555;
  color: #fff;
}

.btn-dark:hover,
.btn-dark:focus {
  background: #373737;
  border-color: #373737;
  color: #fff;
}

/* reset */
.clearfix:after {
  content: "";
  display: block;
  clear: both;
}
.container {
  width: 1100px;
  margin: 0 auto;
}
.blind {
  position: absolute;
  overflow: hidden;
  clip: rect(0 0 0 0);
  margin: -1px;
  width: 1px;
  height: 1px;
}

.custom-btn {
  width: 100px;
  height: 40px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: cookierun;
  font-weight: 600;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
}

/* 16 */
.btn-16 {
  border: none;
  margin-top: 10px;
  color: #000;
}
ㅋㅊㅌㅋㅌㅊㅋㅌㅊㅋㅌㅊ .btn-16:after {
  position: absolute;
  content: "";
  width: 0;
  height: 100%;
  top: 0;
  left: 0;
  direction: rtl;
  z-index: -1;
  box-shadow: -7px -7px 20px 0px #fff9, -4px -4px 5px 0px #fff9,
    7px 7px 20px 0px #0002, 4px 4px 5px 0px #0001;
  transition: all 0.3s ease;
}
.btn-16:hover {
  color: #000;
}
.btn-16:hover:after {
  left: auto;
  right: 0;
  width: 100%;
}
.btn-16:active {
  top: 2px;
}
</style>
