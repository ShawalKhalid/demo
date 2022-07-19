var featuredCategory = "Best Sellers"

<template>
  <div class="home-page">
    <section class="flow-content container">
      <h1 id="main-welcome-message">
        {{'Welcome to the Book Nook! Check out some of our featured ' + $store.state.featuredCategoryName + '!'}}
      </h1>
      <div class="featured-book-image-items">
        <template v-for="book in $store.state.featuredCategoryBooks">
          <a :key="book.bookId"
             v-if="book.isFeatured">
            <img
              :src="require('@/assets/images/books/' + bookImageFileName(book))"
              :alt="book.title"
            />
          </a>
        </template>
      </div>

      <div class="shop-now-cta-button">
        <router-link :to="'/category/' + $store.state.featuredCategoryName">
          <button id="shop-now-button" type="submit" class="button cta-button">
            SHOP NOW!
          </button>
        </router-link>
      </div>

    </section>
  </div>
</template>

<script>

export default {
  name: "home",
  methods: {
    bookImageFileName: function(book) {
      let name = book.title.toLowerCase()
      name = name.replace(/ /g, '_')
      name = name.replace("'","")
      return `${name}.gif`
    }
  }
};
</script>

<style scoped>
.home-page {
  display: flex;
  background-color: var(--page-background-color);
  justify-content: center;
}

#main-welcome-message {
  font-size: 36px;
  color: var(--secondary-background-color);
  text-align: center;
  margin-left: 10%;
  margin-right: 10%;
}

.category-image-items a {
  text-align: center;
  cursor: pointer;
}

.category-image-items div {
  padding: 0.5em 0;
  background: rgba(0, 0, 0, 0.5); /* last # is percent opacity */
  color: white;
  transform: translateY(-2.25em);
  margin-bottom: -2em;
}

.featured-book-image-items {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  gap: 2em;
  margin-left: 25%;
  margin-right: 25%;
  justify-content: center;
  max-width: 550px;
}

.shop-now-cta-button {
  display: flex;
  justify-content: center;
  font-weight: bold;
  padding: 0.5em 0 0.5em 0;
}

router-link {
  color: black;
  font-weight: bolder;
}

</style>
