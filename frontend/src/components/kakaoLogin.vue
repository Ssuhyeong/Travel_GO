<template>
  <section class="test">
    <div v-on:click="kakaoLoginBtn">카카오 계정으로 로그인</div>
  </section>
</template>

<script>
export default {
  name: "testView",
  mounted() {
    this.kakaoinit();
  },
  methods: {
    kakaoinit: function () {
      window.Kakao.init("811a7460154557e361e1a1839f2697c5"); // Kakao Developers에서 요약 정보 -> JavaScript 키
    },

    kakaoLoginBtn: function () {
      if (window.Kakao.Auth.getAccessToken()) {
        window.Kakao.API.request({
          url: "/v1/user/unlink",
          success: function (response) {
            console.log(response);
            console.log("성공");
          },
          fail: function (error) {
            console.log(error);
          },
        });
        window.Kakao.Auth.setAccessToken(undefined);
      }

      window.Kakao.Auth.login({
        success: function () {
          window.Kakao.API.request({
            url: "/v2/user/me",
            data: {
              property_keys: ["kakao_account.email"],
            },
            success: async function (response) {
              console.log(response);
            },
            fail: function (error) {
              console.log(error);
            },
          });
        },
        fail: function (error) {
          console.log(error);
        },
      });
    },
  },
};
</script>

<style scoped>
.test {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
div {
  width: 200px;
  height: 40px;
  background-color: #fdd101;
  color: white;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}
</style>
