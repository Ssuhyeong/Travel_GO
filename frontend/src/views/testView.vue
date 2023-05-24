<template>
  <div>
    <draggable :list="list" @change="log" style="display: flex;" :options="{animation:300, handle:'.handle'}">
      <div
        v-for="element in list"
        :key="element.name"
        id="element"
      >
        {{ element.name }}
      </div>
    </draggable>
  </div>
</template>
<script>
  import { VueDraggableNext } from 'vue-draggable-next'
  export default ({
    components: {
      draggable: VueDraggableNext,
    },
    data() {
      return {
        enabled: true,
        list: [
          { name: 'John', id: 1 },
          { name: 'Joao', id: 2 },
          { name: 'Jean', id: 3 },
          { name: 'Gerard', id: 4 },
        ],
        dragging: false,
      }
    },
    methods: {
      log(event) {
        console.log(event)
      },
    },
  })
</script>
<style scoped>
#element {
  padding: 20px; 
  border-radius: 20px; 
  margin: 5px;  
  cursor: move;
  background-color: #fff;
  box-shadow: rgba(0, 0, 0, 0.24) 0px 3px 8px;
}
</style>