<template>
  <div id="search-form">
    <form>
      <input type="text" class="search-bar" />
      <br />
    </form>
    <router-link to="">
      <svg id="search-icon" viewBox="0 0 512 512">
        <path
          d="M 505 442.7 L 405.3 343 c -4.5 -4.5 -10.6 -7 -17 -7 H 372 c 27.6 -35.3 44 -79.7 44 -128 C 416 93.1 322.9 0 208 0 S 0 93.1 0 208 s 93.1 208 208 208 c 48.3 0 92.7 -16.4 128 -44 v 16.3 c 0 6.4 2.5 12.5 7 17 l 99.7 99.7 c 9.4 9.4 24.6 9.4 33.9 0 l 28.3 -28.3 c 9.4 -9.4 9.4 -24.6 0.1 -34 Z M 208 336 c -70.7 0 -128 -57.2 -128 -128 c 0 -70.7 57.2 -128 128 -128 c 70.7 0 128 57.2 128 128 c 0 70.7 -57.2 128 -128 128 Z"
        ></path>
      </svg>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "HeaderSearchBar",
};
</script>

<style scoped>
#search-form {
  display: inline-flex;
  position: relative;
}

#search-icon {
  position: absolute;
  top: 8px;
  right: 16px;
  z-index: 0;
  fill: var(--highlight-color);
  width: 1.15em;
}

.search-bar {
  padding: 0.5em;
  border: none;
  border-radius: 10px;
  margin-right: 0.5em;
}
</style>
