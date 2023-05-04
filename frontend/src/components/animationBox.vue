<template>
  <div class="container">
    <div class="box">
      <video
        src="../assets/video/waterfall-158229.mp4"
        autoplay
        muted
        loop
        id="video1"
        style="width: 100%; height: 100%; border-radius: 25px"
      ></video>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.container {
  box-sizing: border-box;
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}
.box {
  border-radius: 20px;
  width: 530px;
  height: 300px;
  background: #f0f0f0;
  /* box-shadow: 0 0 0 #cccccc, 0 0 0 #ffffff, 10px 10px 10px #cccccc inset,
    -10px -10px 10px #ffffff inset; */
  box-shadow: 40px 40px 40px #cccccc, 0 0 0 #ffffff, 0 0 0 #cccccc inset,
    2px 2px 2px #ffffff inset;
  /* animation: anime 3s cubic-bezier(0.16, 1, 0.3, 1) forwards; */
  /* animation-fill-mode: forwards; */
}

/* @keyframes anime {
  0% {
    width: 60px;
    height: 60px;
    background: #f0f0f0;
    box-shadow: 0 0 0 #cccccc, 0 0 0 #ffffff, 10px 10px 10px #cccccc inset,
      -10px -10px 10px #ffffff inset;
  }
  25% {
    width: 60px;
    height: 60px;
    background: #f8f8f8;
    box-shadow: 10px 10px 10px #cccccc, 10px 10px 10px #ffffff,
      0 0 0 #cccccc inset, 0 0 0 #ffffff inset;
  }
  50% {
    width: 60px;
    height: 360px;
    background: #f8f8f8;
    box-shadow: 10px 10px 10px #cccccc, 10px 10px 10px #ffffff,
      0 0 0 #cccccc inset, 0 0 0 #ffffff inset;
  }
  100% {
    width: 530px;
    height: 300px;
    background: url("https://images.unsplash.com/photo-1575936123452-b67c3203c357?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1170&q=80");
    background-position: center;
    box-shadow: 40px 40px 40px #cccccc, 0 0 0 #ffffff, 0 0 0 #cccccc inset,
      2px 2px 2px #ffffff inset;
  }
} */
</style>

