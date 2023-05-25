<template>
  <myNav />
  <div class="write_container" v-on:submit.prevent>
    <form id="contact">
      <input type="hidden" name="action" value="write" />
      <h3>공지사항</h3>
      <h4>괸리자님 공지사항을 알려주세요</h4>
      <fieldset>
        <input
          placeholder="title"
          type="text"
          tabindex="1"
          id="subject"
          name="subject"
          v-model="board_data.subject"
          required
          autofocus />
      </fieldset>
      <fieldset>
        <textarea
          id="content"
          name="content"
          placeholder="Type your text..."
          tabindex="5"
          v-model="board_data.content"
          required></textarea>
      </fieldset>
      <fieldset style="display: flex; justify-content: space-between">
        <button name="submit" type="submit" id="btn-register" @click="regist()">
          글작성
        </button>
        <button
          name="submit"
          type="submit"
          id="contact -submit"
          @click="$router.go(-1)">
          취소
        </button>
      </fieldset>
    </form>
  </div>
</template>

<script>
import myNav from "@/views/includes/myNav.vue";
import { useStore } from "vuex";
import axios from "@/service/axios";

export default {
  components: {
    myNav,
  },
  data() {
    return {
      board_data: {
        user_id: "교수님",
        subject: "",
        content: "",
      },
      boardtype: "",
      articleNo: "",
      type: "",
    };
  },
  setup() {
    const store = useStore();
    const setShow = () => store.commit("setShow", true);
    const setText = () => store.commit("setText", "성공적으로 등록하였습니다.");
    const setColor = () => store.commit("setColor", "#0e4bf1");

    return { setShow, setText, setColor };
  },
  created() {
    this.boardtype = this.$route.params.type;
    this.articleNo = this.$route.params.articleNo;

    console.log(this.articleNo);

    if (this.articleNo != "5000") {
      this.type = "update";
      const url = `http://localhost:8080/${this.boardtype}/${this.articleNo}`;
      axios.get(url).then((res) => {
        this.board_data = res.data;
      });
    }
  },
  methods: {
    regist() {
      let url;
      if (this.boardtype == "update") {
        url = `http://localhost:8080/${this.boardtype}/update/${this.articleNo}`;
      } else {
        url = `http://localhost:8080/${this.boardtype}`;
      }

      axios
        .post(url, this.board_data)
        .then(() => {
          this.setShow();
          this.setText();
          this.setColor();
          this.$router.go(-1);
        })
        .catch((error) => {
          console.log(error);
          alert("등록 실패");
        });
    },
  },
};
</script>

<style scoped>
.write_container {
  max-width: 500px;
  width: 100%;
  margin: 0 auto;
  position: relative;
  box-shadow: 0px 5px 15px 0px rgba(0, 0, 0, 0.35);
}

#contact input[type="text"],
#contact input[type="email"],
#contact input[type="tel"],
#contact input[type="url"],
#contact textarea,
#contact button[type="submit"] {
  font: 400 14px/16px CookieRun_Regular;
}

#contact {
  background: #f9f9f9;
  padding: 25px;
  margin: 150px 0;
}

#contact h3 {
  color: #0097b2;
  display: block;
  font-size: 35px;
  font-weight: 600;
  margin: 15px 0px;
}

#contact h4 {
  margin: 5px 0 15px;
  display: block;
  font-size: 13px;
}

fieldset {
  border: medium none !important;
  margin: 0 0 10px;
  min-width: 100%;
  padding: 0;
  width: 100%;
}

#contact input[type="text"],
#contact input[type="email"],
#contact input[type="tel"],
#contact input[type="url"],
#contact textarea {
  width: 95%;
  border: 1px solid #ccc;
  background: #fff;
  margin: 0 0 5px;
  padding: 10px;
}

#contact input[type="text"]:hover,
#contact input[type="email"]:hover,
#contact input[type="tel"]:hover,
#contact input[type="url"]:hover,
#contact textarea:hover {
  -webkit-transition: border-color 0.3s ease-in-out;
  -moz-transition: border-color 0.3s ease-in-out;
  transition: border-color 0.3s ease-in-out;
  border: 1px solid #aaa;
}

#contact textarea {
  height: 200px;
  max-width: 100%;
  resize: none;
}

#contact button[type="submit"] {
  cursor: pointer;
  width: 45%;
  border: none;
  background: #0097b2;
  color: #fff;
  margin: 0 0 5px;
  padding: 10px;
  border-radius: 5px;
  font-size: 15px;
}

#contact button[type="submit"]:hover {
  background: #00859c;
  -webkit-transition: background 0.3s ease-in-out;
  -moz-transition: background 0.3s ease-in-out;
  transition: background-color 0.3s ease-in-out;
}

#contact button[type="submit"]:active {
  box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.5);
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
</style>
