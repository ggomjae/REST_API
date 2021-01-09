<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary lighten-1">
                <v-toolbar-title justify-center>SignUp</v-toolbar-title>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field
                    label="Username"
                    prepend-icon="mdi-account-circle"
                  />
                  <v-text-field
                    label="Email"
                    name="email"
                    prepend-icon="mdi-account"
                    type="text"
                    v-model="email"
                  ></v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    prepend-icon="mdi-lock"
                    type="password"
                    v-model="password"
                  ></v-text-field>

                  <v-text-field
                    id="confirm"
                    label="Confirm Password"
                    name="confirm"
                    prepend-icon="mdi-alert-circle-check"
                    type="password"
                    v-model="confirm"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-divider></v-divider>
              <v-card-actions>
                <v-btn color="success">SIGNUP</v-btn>
                <v-btn color="info">Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
    <v-footer class="pa-3 white--text" color="primary lighten-2" absolute>
      <div>
        Developed with 🐻️  by {{GGOMJAE}} &copy; {{ new Date().getFullYear() }}
      </div>
    </v-footer>
  </v-app>
</template>

<script>
  export default {
    name: "SignUp"
  }
</script>

<style scoped>
</style>
