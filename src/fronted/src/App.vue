<template>
  <v-app>
    <TodoMenu></TodoMenu>

    <TodoHeader></TodoHeader>

    <TodoMain></TodoMain>

    <TodoFooter></TodoFooter>
  </v-app>
</template>

<script>
  import TodoMain from "./components/TodoMain";
  import TodoHeader from "./components/TodoHeader";
  import TodoFooter from "./components/TodoFooter";
  import TodoMenu from "./components/TodoMenu";

  export default {
    components: {
      TodoMain,
      TodoHeader,
      TodoFooter,
      TodoMenu
    }
  }
</script>

<style>

</style>
