<template>
  <form @submit="uploadFile">
    <input type="file" id="File" name="files" multiple="multiple"/>
    <button id="Button">전송</button>
  </form>
</template>

<script>
import axios from "@/service/axios";

export default {
  name: "PhotoUpload",
  data() {
    return{

    }
  },
  components: {},

  methods: {
    async uploadFile(event) {
      event.preventDefault();
      const FileElement = document.querySelector('#File');

      console.log(FileElement.files[0])

      const file = FileElement.files[0];
      const formData = new FormData();
      formData.append('files', file);

      console.log(formData)

      try {
        const response = await axios.post('/member/photo', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        
        // Handle the response from the server
        console.log(response.data);
      } catch (error) {
        // Handle any errors that occur during the upload
        console.error(error);
      }
    }
  }
};
</script>

<style scoped>

</style>
