<template>
  <div class="place_content">
    <div class="place_title">
        <h3>영광빌라</h3>
        <p>빌라, 연립주택</p>
    </div>
    <div class="review_num">
        <p>리뷰 0</p>
    </div>
    <div class="addr_main">
        대전 서구 신갈마로211번길 63
    </div>
    <div class="addr_sub">
        (지번) 갈마동 297-28
    </div>
    <div class="detail">
        <a href="/detailpage">상세보기</a>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
    p, h3 {
        margin: 2px 0px;
        padding: 0px;
    }

    a {
        text-decoration-line: none;
    }

    a:hover {
        text-decoration-line: underline;
    }

    a:visited {
        color: #5398dd;
    }

    .place_content {
        padding: 10px 30px;
        border-bottom: 1px solid #dbdbdb;
    }
    .place_content:hover {
        background-color:#d9ecf0;
    }

    .place_title {
        display: flex;
        align-items: center;
    }

    .place_title > p {
        color: #9c9c9c;
        font-size: 12px;
        margin-left: 6px;
    }

    .review_num {
        color: #9c9c9c;
        font-size: 12px;
    }

    .addr_sub {
        font-size: 12px;
        color: #9c9c9c;
        margin: 5px 0px;
    }
</style>