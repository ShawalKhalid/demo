<template>
  <nav class="category-nav">
    <ul class="category-buttons">
      <template v-for="category in $store.state.categories">
      <li
          :key="category.categoryId"
          v-if="$store.state.selectedCategoryName === category.name">
        <router-link :to="'/category/' + category.name"
                     class="button selected-category-button">{{
            category.name
          }}</router-link>
      </li>
      <li
          :key="category.categoryId"
          v-else>
        <router-link :to="'/category/' + category.name"
                     class="button unselected-category-button">{{
            category.name
          }}</router-link>
      </li>
      </template>
    </ul>
  </nav>
</template>

<script>
export default {
  name: "CategoryNav"
};
</script>

<style scoped>

.category-buttons {
  display: flex;
  flex-direction: row;
  text-align: center;
  justify-content: center;
  background-color: var(--page-background-color);
  gap: 2em;
  padding: 1em 2em 1em 2em;
}

.button.selected-category-button {
  background-color: var(--selected-color);
  color: var(--secondary-background-color);
  width: 11em;
}

.button.selected-category-button:hover,
.button.selected-category-button:active {
  background-color: var(--selected-color);
  color: var(--secondary-background-color);
  width: 11em;
}

.button.unselected-category-button,
.button.unselected-category-button:visited {
  background-color: var(--highlight-color);
  color: var(--secondary-background-color);
  width: 11em;
}

.button.unselected-category-button:hover,
.button.unselected-category-button:active {
  background-color: var(--primary-color-dark);
  color: var(--primary-background-color);
  width: 11em;
}

</style>
