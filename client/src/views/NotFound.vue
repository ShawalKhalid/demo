<template>
  <div class="not-found-page">
    <div class="not-found-page-content-container">
      <h2>404 - Page not found.</h2>
    </div>
  </div>
</template>

<script>
</script>

<style scoped>
.not-found-page {
  padding: 1em;
  background-color: var(--page-background-color-light);
}

.not-found-page-content-container {
  display: flex;
  padding: 1em;
  flex-direction: column;
  text-align: center;
  gap: 1em;
}

h2 {
  display: flex;
  flex-direction: column;
  align-self: center;
  background: var(--page-background-color);
  padding: 1em;
  border-radius: 5px;
}

</style>
