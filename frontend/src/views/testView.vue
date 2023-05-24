<template>
  <div class="container">
    <div class="delete-button">
      <button>Delete</button>
    </div>
    <p>Hover over this div to see the delete button.</p>
  </div>
</template>

<script>
// import axios from "@/service/axios";

export default {
  name: "PhotoUpload",
  components: {},
  methods: {},
};
</script>

<style scoped>
.container {
  position: relative;
  width: 200px;
  height: 200px;
  background-color: lightgray;
  padding: 20px;
}

.delete-button {
  position: absolute;
  top: 10px;
  right: 10px;
  display: none;
}

.container:hover .delete-button {
  display: block;
}
</style>
