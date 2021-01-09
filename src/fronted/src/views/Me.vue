<template>
  <v-app>
    <TodoMenu></TodoMenu>
    <TodoHeader></TodoHeader>
    <TodoMe></TodoMe>
    <TodoFooter></TodoFooter>
  </v-app>
</template>

<script>
  import TodoMe from "../components/TodoMe";
  import TodoHeader from "../components/common/TodoHeader";
  import TodoFooter from "../components/common/TodoFooter";
  import TodoMenu from "../components/common/TodoMenu";
  export default {
    components: {
      TodoMe,
      TodoHeader,
      TodoFooter,
      TodoMenu
    }
  }
</script>

<style>
</style>
