<template>
  <v-app>
    <TodoMenu></TodoMenu>
    <TodoHeader></TodoHeader>
    <TodoPost></TodoPost>
    <TodoFooter></TodoFooter>
  </v-app>
</template>

<script>
  import TodoPost from "../components/TodoPost";
  import TodoHeader from "../components/common/TodoHeader";
  import TodoFooter from "../components/common/TodoFooter";
  import TodoMenu from "../components/common/TodoMenu";

  export default {
    components: {
      TodoPost,
      TodoHeader,
      TodoFooter,
      TodoMenu
    }
  }
</script>

<style>

</style>
