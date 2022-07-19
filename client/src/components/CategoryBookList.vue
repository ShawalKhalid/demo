<template>
  <section class="category-book-list">
    <ul id="book-boxes">
      <template v-for="book in $store.state.selectedCategoryBooks">
      <category-book-list-item :key="book.bookId"
      :book="book"></category-book-list-item>
      </template>
    </ul>
  </section>
</template>

<script>
import CategoryBookListItem from "./CategoryBookListItem";

export default {
  name: "CategoryBookList",
  components: {
    CategoryBookListItem,
  }
};
</script>

<style scoped>
#book-boxes {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  padding: 1em;
  gap: 1em;
}
</style>
