<template>
  <li class="book-box">
    <div class="book-image-container">
      <img class="book-image"
        :src="require('@/assets/images/books/' + bookImageFileName(book))"
        :alt="book.title"
      />
      <button v-if="book.isPublic" class="button cta-button read-now-button">
        <svg class="read-now-icon" viewBox="0 0 576 512">
          <path
            d="M 542.22 32.05 c -54.8 3.11 -163.72 14.43 -230.96 55.59 c -4.64 2.84 -7.27 7.89 -7.27 13.17 v 363.87 c 0 11.55 12.63 18.85 23.28 13.49 c 69.18 -34.82 169.23 -44.32 218.7 -46.92 c 16.89 -0.89 30.02 -14.43 30.02 -30.66 V 62.75 c 0.01 -17.71 -15.35 -31.74 -33.77 -30.7 Z M 264.73 87.64 C 197.5 46.48 88.58 35.17 33.78 32.05 C 15.36 31.01 0 45.04 0 62.75 V 400.6 c 0 16.24 13.13 29.78 30.02 30.66 c 49.49 2.6 149.59 12.11 218.77 46.95 c 10.62 5.35 23.21 -1.94 23.21 -13.46 V 100.63 c 0 -5.29 -2.62 -10.14 -7.27 -12.99 Z">
          </path>
        </svg>
      </button>
    </div>
    <div class="container book-info">
      <div class="book-info-text">
        <div class="book-title">{{ book.title }}</div>
        <div class="book-author">{{ book.author }}</div>
        <div class="book-price">${{ (book.price / 100).toFixed(2) }}</div>
      </div>
      <button class="button cta-button add-to-cart-cta-button" @click="addToCart(book)">
        Add to Cart
      </button>
    </div>
  </li>
</template>

<script>
export default {
  name: "categoryBookListItem",
  props: {
    book: {
      type: Object,
      required: true,
    },
  },
  methods: {
    bookImageFileName: function(book) {
      let name = book.title.toLowerCase()
      name = name.replace(/ /g, '_')
      name = name.replace("'","")
      return `${name}.gif`
    },
    addToCart(book) {
      this.$store.dispatch("addToCart", book)
    }
  }
};
</script>
<style scoped>
.book-box {
  display: flex;
  flex-direction: row;
  padding: 1em;
  gap: 0.25em;
  background-color: var(--page-background-color);
  border-radius: 5px;
}

.book-info {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: 0 0 0 1em;
}

.book-info-text {
  width: 9em;
}

.book-title {
  font-weight: bold;
}

.book-author {
  font-style: italic;
  padding: 0.3em 0 0 0;
}

.book-price {
  padding: 0.8em 0 0 0;
}

.add-to-cart-cta-button {
  font-weight: normal;
  width: 9em;
}

.book-image-container {
  position: relative;
}

.read-now-button {
  background-color: var(--highlight-color);
  color: var(--secondary-background-color);
  width: 2.5em;
  height: 2.5em;
  border-radius: 50%;
  position: absolute;
  bottom: 0.3em;
  right: 0.3em;
  border: 0.1em solid black;
}

.read-now-icon {
  fill: white;
  z-index: 0;
  width: 1.5em;
  position: absolute;
  bottom: 0.5em;
  right: 0.5em;
}

</style>
