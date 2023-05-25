<template>
  <myNav />
  <div class="view_container">
    <form id="contact">
      <h3>{{ board_data.subject }}.</h3>
      <h4>{{ name }}</h4>
      <h4>{{ board_data.registerTime }}</h4>
      <fieldset>{{ board_data.content }}</fieldset>
    </form>
  </div>
  <div class="btn_container">
    <button
      type="button"
      id="btn-list"
      class="custom-btn btn-16"
      @click="$router.go(-1)">
      글목록
    </button>
    <button
      type="button"
      id="btn-mv-modify"
      class="custom-btn btn-16"
      @click="goUpdate()">
      글수정
    </button>
    <button
      type="button"
      id="btn-delete"
      class="custom-btn btn-16"
      @click="deleteBoard()">
      글삭제
    </button>
    <vue-basic-alert :duration="1500" :closeIn="1500" ref="alert" />
  </div>
</template>

<script>
import myNav from "@/views/includes/myNav.vue";
import axios from "@/service/axios";
import VueBasicAlert from "vue-basic-alert";
import { useStore } from "vuex";

export default {
  components: {
    myNav,
    VueBasicAlert,
  },
  data() {
    return {
      board_data: {},
      name: "",
      boardtype: "",
      articleNo: "",
    };
  },
  setup() {
    const store = useStore();
    const setShow = () => store.commit("setShow", true);
    const setText = () => store.commit("setText", "성공적으로 삭제하였습니다.");
    const setColor = () => store.commit("setColor", "#f44040");

    return { setShow, setText, setColor };
  },
  created() {
    this.boardtype = this.$route.params.type;
    this.articleNo = this.$route.params.articleNo;

    const url = `http://192.168.210.61:8080/${this.boardtype}/${this.articleNo}`;
    axios.get(url).then((res) => {
      this.board_data = res.data;
      this.name = this.board_data.member.name;
    });
  },
  methods: {
    deleteBoard() {
      const no = this.board_data.articleNo;
      const url = `http://192.168.210.61:8080/${this.boardtype}/${no}`;

      axios
        .delete(url)
        .then(() => {
          this.setShow();
          this.setText();
          this.setColor();
          this.$router.go(-1);
        })
        .catch(() => {
          this.$refs.alert.showAlert(
            "error",
            "본인의 계정만 삭제가 가능합니다.",
            "삭제 실패"
          );
        });
    },
    goUpdate() {
      const no = this.board_data.articleNo;
      this.$router.push({
        name: "boardwritepage",
        params: { type: this.boardtype, articleNo: no },
      });
    },
  },
};
</script>

<style scoped>
.view_container {
  max-width: 1000px;
  width: 100%;
  margin: 0 auto;
  position: relative;
  box-shadow: 0px 5px 15px 0px rgba(0, 0, 0, 0.35);
}

.btn_container {
  max-width: 1000px;
  width: 100%;
  margin: 0 auto;
  position: relative;
  margin-bottom: 100px;
}

#contact input[type="text"],
#contact input[type="email"],
#contact input[type="tel"],
#contact input[type="url"],
#contact textarea,
#contact button[type="submit"] {
  font: 400 12px/16px "Open Sans", Helvetica, Arial, sans-serif;
}

#contact {
  background: #f9f9f9;
  padding: 40px;
  margin: 110px 0px 30px;
}

#contact h3 {
  color: #00859c;
  display: block;
  font-size: 30px;
  font-weight: 400;
  margin: 0px 0px 20px;
}

#contact h4 {
  margin: 5px 0 15px;
  display: block;
  font-size: 13px;
}

fieldset {
  border: medium none !important;
  margin: 50px 0 10px;
  min-width: 100%;
  min-height: 400px;
  padding: 0;
  width: 100%;
}

#contact textarea {
  height: 100px;
  max-width: 100%;
  resize: none;
}

#contact input:focus,
#contact textarea:focus {
  outline: 0;
  border: 1px solid #999;
}
::-webkit-input-placeholder {
  color: #888;
}
:-moz-placeholder {
  color: #888;
}
::-moz-placeholder {
  color: #888;
}
:-ms-input-placeholder {
  color: #888;
}

#contact button[type="button"] {
  cursor: pointer;
  width: 100%;
  border: none;
  background: #0cf;
  color: #fff;
  margin: 0 0 5px;
  padding: 10px;
  font-size: 15px;
}

#contact button[type="button"]:hover {
  background: #09c;
  -webkit-transition: background 0.3s ease-in-out;
  -moz-transition: background 0.3s ease-in-out;
  transition: background-color 0.3s ease-in-out;
}

#contact button[type="button"]:active {
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
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
  color: #000;
  margin: 0px 20px 0px 0px;
}
.btn-16:after {
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
