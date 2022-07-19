<template>
  <div class="header-dropdown">
    <button id="category-dropdown" type="submit" class="button header-button">
      Categories
      <svg id="dropdown-arrow" viewBox="0 0 320 512">
        <path
          d="M 31.3 192 h 257.3 c 17.8 0 26.7 21.5 14.1 34.1 L 174.1 354.8 c -7.8 7.8 -20.5 7.8 -28.3 0 L 17.2 226.1 C 4.6 213.5 13.5 192 31.3 192 Z"
        ></path>
      </svg>
    </button>
    <ul>
      <li
          :key="category.categoryId"
          v-for="category in $store.state.categories">
        <router-link :to="'/category/' + category.name">{{
            category.name
          }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HeaderDropdownMenu"
};
</script>

<style scoped>
ol,
ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.header-button {
  background-color: #e6e6e6;
  border: none;
  border-radius: 10px;
  height: 35px;
  align-self: center;
  color: #364a52;
}

#category-dropdown {
  padding-left: 2.3em;
  position: relative;
}

#dropdown-arrow {
  position: absolute;
  top: 0.25em;
  left: 1em;
  z-index: 0;
  fill: var(--highlight-color);
  width: 1em;
}

.header-dropdown {
  position: relative;
  align-self: center;
}

.header-dropdown ul {
  background-color: white;
  display: none;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown a:hover {
  color: white;
}

.header-dropdown li:hover {
  background: var(--primary-color-dark);
}

.header-dropdown li:hover a {
  background: var(--primary-color-dark);
  color: white;
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 8em;
  z-index: 1;
  cursor: pointer;
}
</style>
