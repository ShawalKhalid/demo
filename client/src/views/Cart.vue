<template>
  <div class="cart-page">
    <section v-if="$store.state.cart.numberOfItems === 0" class="empty-cart-page">
      <p>Your cart is empty.</p>
      <div class="cart-buttons-top">
        <router-link :to="'/category/' + $store.state.selectedCategoryName">
          <button class="button secondary-button">Continue Shopping</button>
        </router-link>
      </div>
    </section>

    <section v-else class="non-empty-cart-page">

      <p>
        {{"Your cart contains " + $store.state.cart.numberOfItems + " items."}}
        <br>
        Subtotal:&nbsp;<Price :amount="$store.state.cart.subtotal"/>
      </p>

      <div class="cart-buttons-top">
        <router-link :to="'/category/' + $store.state.selectedCategoryName">
          <button class="button secondary-button">Continue Shopping</button>
        </router-link>
        <router-link :to="'/checkout'">
          <button class="button cta-button">Proceed to Checkout</button>
        </router-link>
      </div>

      <cart-table></cart-table>

      <div class="cart-buttons-bottom">
        <button @click="clearCart()" class="button tertiary-button">
          Clear Cart
        </button>
      </div>

    </section>
  </div>
</template>
<script>
import CartTable from "@/components/CartTable";
import Price from "@/components/Price";
export default {
  name: "Cart",
  components: {Price, CartTable},
  methods: {
    clearCart() {
      this.$store.dispatch("clearCart");
    }
  }
}
</script>
<style scoped>
.cart-page {
  padding: 1em;
  background-color: var(--page-background-color-light);
}

.non-empty-cart-page,
.empty-cart-page {
  display: flex;
  flex-direction: column;
  text-align: center;
  gap: 1em;
}

.cart-buttons-top {
  display: flex;
  flex-direction: row;
  justify-content: center;
  gap: 2em;
}



</style>