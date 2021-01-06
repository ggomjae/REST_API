import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

// View Component
import Login from "../views/Login";
import SignUp from "../views/SignUp";
import Me from "../views/Me";
import Post from "../views/Post";
import Posts from "../views/Posts";

export const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', component: Login, name: 'Login'},
    { path: '/signup', component: SignUp, name: 'SignUp'},
    { path: '/me', component: Me, name: 'Me'},
    { path: '/post', component: Post, name: 'Post'},
    { path: '/posts', component: Posts, name: 'Posts'}
  ]
});
