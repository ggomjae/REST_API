<template>
  <div id="app">
    <TodoInput></TodoInput>
  </div>
</template>

<script>
  import TodoInput from './components/TodoInput.vue'

  export default {
    components: {
      TodoInput
    }
  }
</script>

<style>
  body {
    text-align: center;
    background-color: #F6F6F8;
  }
  input {
    border-style: groove;
    width: 200px;
  }
  button {
    border-style: groove;
  }
  .shadow {
    box-shadow: 5px 10px 10px rgba(0, 0, 0, 0.03)
  }
</style>
