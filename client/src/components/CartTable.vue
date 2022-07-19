<template>
  <div class="cart-table">
    <ul>
      <li v-for="item in $store.state.cart.items" :key="item.book.bookId">
        <div class="cart-book-image">
          <img :src="require('@/assets/images/books/' + bookImageFileName(item.book))" />
        </div>
        <div class="cart-book-title">
          {{item.book.title}}
        </div>
        <div class="cart-book-price">
          <Price :amount="item.price"/>
        </div>
        <div class="cart-book-quantity">
          {{item.quantity}}
          <button @click="updateCart({book: item.book, quantity: item.quantity + 1})" class="icon-button increase-button">
            <i class="fas fa-plus-circle"/>
          </button>
          <button @click="updateCart({book: item.book, quantity: item.quantity - 1})" class="icon-button decrease-button">
            <i class="fas fa-minus-circle" />
          </button>
        </div>
        <div class="cart-book-subtotal">
          <Price :amount="item.quantity * item.book.price"/>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import Price from '@/components/Price';

export default {
  name: "cartTable",
  components: {Price},
  methods: {
    bookImageFileName: function (book) {
      let name = book.title.toLowerCase()
      name = name.replace(/ /g, '_')
      name = name.replace("'", "")
      return `${name}.gif`
    },
    updateCart(book, quantity) {
      this.$store.dispatch("updateCart", book, quantity)
    }
  }
};
</script>

<style scoped>

.cart-table {
  display: grid;
  align-self: center;
  justify-self: center;
  grid-template-columns: repeat(5, auto);
  grid-column-gap: 4em;
  grid-row-gap: 1em;
  background-color: var(--page-background-color);
  padding: 1em;
  align-items: center;
  border-radius: 5px;
}

ul, li {
  display: contents;
}

.cart-book-image img {
  width: 55px;
  height: auto;
}

.cart-book-title {
  text-align: left;
}

.cart-book-price {
  text-align: right;
}

.cart-book-subtotal {
  text-align: right;
}

.increase-button {
  padding: 0;
  margin-left: 0.8em;
}

.decrease-button {
  padding: 0;
  margin-left: 0.4em;
}

</style>