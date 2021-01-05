<template>
  <v-app>
    <TodoMenu></TodoMenu>
    <TodoHeader></TodoHeader>
    <TodoPosts></TodoPosts>
    <TodoFooter></TodoFooter>
  </v-app>
</template>

<script>
  import TodoPosts from "../components/TodoPosts";
  import TodoHeader from "../components/common/TodoHeader";
  import TodoFooter from "../components/common/TodoFooter";
  import TodoMenu from "../components/common/TodoMenu";

  export default {
    components: {
      TodoPosts,
      TodoHeader,
      TodoFooter,
      TodoMenu
    }
  }
</script>

<style>

</style>
