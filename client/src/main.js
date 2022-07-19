import Vue from "vue";
import Vuelidate from 'vuelidate'
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "@/assets/css/global.css";
import {CART_STORAGE_KEY} from "./store";

Vue.config.productionTip = false;
Vue.use(Vuelidate)

new Vue({
  router,
  store,
  render: function (h) {
    return h(App);
  },
  created() {
    const cartString = localStorage.getItem(CART_STORAGE_KEY);
    if (cartString) {
      const shoppingCart = JSON.parse(cartString);
      this.$store.commit("SET_CART", shoppingCart);
    }
  }
}).$mount("#app");
