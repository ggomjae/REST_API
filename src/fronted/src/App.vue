<template>
  <div id="app">
    <TodoHeader></TodoHeader>
    <TodoInput></TodoInput>
    <TodoFooter></TodoFooter>
  </div>
</template>

<script>
  import TodoInput from './components/TodoInput.vue'
  import TodoHeader from "./components/TodoHeader";
  import TodoFooter from "./components/TodoFooter";

  export default {
    components: {
      TodoInput,
      TodoHeader,
      TodoFooter
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
