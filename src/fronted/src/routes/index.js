import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

// View Component
import Login from "../views/Login";
import Sign from "../views/Sign";
import Me from "../views/Me";
import Post from "../views/Post";
import Posts from "../views/Posts";

export const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', component: Login, name: 'Login'},
    { path: '/signup', component: Sign, name: 'Sign'},
    { path: '/me', component: Me, name: 'Me'},
    { path: '/post', component: Post, name: 'Post'},
    { path: '/posts', component: Posts, name: 'Posts'}
  ]
});
