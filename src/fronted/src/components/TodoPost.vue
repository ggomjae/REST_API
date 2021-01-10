<template>
  <v-main>
    <v-card
      :loading="loading"
      class="mx-auto my-12"
      max-width="374"
    >
      <template slot="progress">
        <v-progress-linear
          color="deep-purple"
          height="10"
          indeterminate
        ></v-progress-linear>
      </template>

      <v-img
        v-if="img !== null"
        height="250"
        :src="img"
      ></v-img>

      <v-card-title
        :email="email"
      >
        {{email}}
      </v-card-title>

      <v-card-text>
        <v-row
          align="center"
          class="mx-0"
        >
          <v-rating
            :value="4.5"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          ></v-rating>

          <div class="grey--text ml-4">
            4.5 (413)
          </div>
        </v-row>

        <div class="my-4 subtitle-1" :email="email">
          {{email}}
        </div>

        <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.</div>
      </v-card-text>

      <v-divider class="mx-4"></v-divider>

      <v-card-title>Tonight's availability</v-card-title>

      <v-card-text>
        <v-chip-group
          v-model="selection"
          active-class="deep-purple accent-4 white--text"
          column
        >
          <v-chip>5:30PM</v-chip>

          <v-chip>7:30PM</v-chip>

          <v-chip>8:00PM</v-chip>

          <v-chip>9:00PM</v-chip>
        </v-chip-group>
      </v-card-text>

      <v-card-actions>
        <v-btn
          color="deep-purple lighten-2"
          text
          @click="reserve"
        >
          Reserve
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-main>
</template>

<script>

  import axios from 'axios';

  export default {
    data () {
      return {
        selection : "selection!",
        email : "",
        reserve : "reserve!!",
        loading : "loading!!",
        img : null
      }
    },
    /*
      csr 에서는 data created에 접근 가능 단 돔에 접근 x
      그래서 created 보단 mounted를 썼음.
     */
    mounted () {

      axios.defaults.baseURL = 'http://localhost:8082/v1/api';
      axios.defaults.headers['Content-Type'] ='application/json;charset=utf-8';
      axios.defaults.headers['Access-Control-Allow-Origin'] = '*';

      axios.get('/posts/1')
        .then(data =>{
          this.email = data.data.content;
          this.img = "https://cdn.vuetifyjs.com/images/cards/cooking.png";
        }).catch(e => { alert(e)});
    }
  }
</script>
