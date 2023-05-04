<template>
  <div class="card">
    <div class="card-body">
      
    </div>
  </div>
</template>

<script>
export default {
  name: "cardMap",
};
</script>

<style scoped>
.card {
  width: 25%;
  min-width: 350px;
  height: 92%;
  border: 1px solid #ddd;
  background-color: #fff;
  border-radius: 5px;
  overflow: hidden;
  margin: 20px 40px;
  box-shadow: 0 3px 5px rgba(0, 0, 0, 0.3);
}

.card img {
  height: 200px;
  object-fit: cover;
}

.card-body {
  padding: 10px;
}

.card-title {
  font-size: 1.2rem;
  margin-bottom: 0.5rem;
}

.card-text {
  font-size: 1rem;
  color: #555;
  margin-bottom: 1rem;
}
</style>
