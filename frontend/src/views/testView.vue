<template>
  <form @submit="uploadFile">
    <input
      type="file"
      id="File"
      class="Button"
      name="files"
      multiple="multiple"
      hidden />
    <label for="File">업로드</label>
    <div @click="uploadFile">전송</div>
  </form>
</template>

<script>
import axios from "@/service/axios";

export default {
  name: "PhotoUpload",
  data() {
    return {};
  },
  components: {},
  methods: {
    async uploadFile(event) {
      event.preventDefault();
      const FileElement = document.querySelector("#File");

      console.log(FileElement.files[0]);

      const file = FileElement.files[0];
      const formData = new FormData();
      formData.append("files", file);

      console.log(formData);

      try {
        const response = await axios.post("/member/photo", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        });

        // Handle the response from the server
        console.log(response.data);
      } catch (error) {
        // Handle any errors that occur during the upload
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
.Button {
  border: none;
  color: #fff;
  background: #00859c;
  appearance: none;
  font: inherit;
  font-size: 1.1rem;
  padding: 0.4em 0.6em;
  border-radius: 0.3em;
  cursor: pointer;
  margin: 20px 10px 10px;
}
.profile_pic {
  width: 200px;
  max-height: 200px;
  display: inline-block;
}

.file-upload {
  display: none;
}

.circle {
  border-radius: 100% !important;
  overflow: hidden;
  width: 128px;
  height: 128px;
  border: 2px solid rgba(255, 255, 255, 0.2);
  position: absolute;
  top: 72px;
  position: relative;
}

img {
  max-width: 100%;
  height: auto;
}

.p-image {
  position: absolute;
  top: 167px;
  left: 90px;
  color: #666666;
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.p-image:hover {
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
}

.upload-button {
  font-size: 1.2em;
}

.upload-button:hover {
  transition: all 0.3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  color: #999;
}
</style>
