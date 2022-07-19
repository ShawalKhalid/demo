<template>
  <div class="category-page">
    <category-nav></category-nav>
    <category-book-list :books="$store.state.selectedCategoryBooks"> </category-book-list>
  </div>
</template>

<script>
import CategoryNav from "@/components/CategoryNav";
import CategoryBookList from "@/components/CategoryBookList";
import router from "@/router.js";

export default {
  name: "category",
  components: {
    CategoryNav,
    CategoryBookList,
  },
  created: function() {
    this.$store.dispatch("selectCategory", this.$route.params.name)
    this.$store.dispatch("fetchSelectedCategoryBooks")
        .catch(function() {
          router.push('/404') // '/404' triggers NotFound
    });
  }
};
</script>

<style scoped>
.category-page {
  background-color: var(--page-background-color-light);
}
</style>
