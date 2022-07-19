<template>
  <header class="container">
    <section class="bookstore-logo-and-text">
      <router-link to="/">
        <img
          src="../assets/images/site/booknook-logo.png"
          alt="The Book Nook"
          width="100px"
          height="auto"
        />
      </router-link>
      <router-link to="/">
        <h1 class="logo-text">The Book Nook</h1>
      </router-link>
    </section>

    <section class="search-and-category-bar">
      <header-search-bar></header-search-bar>
      <header-dropdown-menu></header-dropdown-menu>
    </section>

    <section class="login-button-and-shopping-cart">
      <button id="login-button" type="submit" class="button header-button">
        <svg id="login-button-icon" viewBox="0 0 448 512">
          <path
            d="M 224 256 c 70.7 0 128 -57.3 128 -128 S 294.7 0 224 0 S 96 57.3 96 128 s 57.3 128 128 128 Z m 89.6 32 h -16.7 c -22.2 10.2 -46.9 16 -72.9 16 s -50.6 -5.8 -72.9 -16 h -16.7 C 60.2 288 0 348.2 0 422.4 V 464 c 0 26.5 21.5 48 48 48 h 352 c 26.5 0 48 -21.5 48 -48 v -41.6 c 0 -74.2 -60.2 -134.4 -134.4 -134.4 Z"
          ></path>
        </svg>
        Login
      </button>

      <div id="shopping-cart">
        <router-link to="/cart">
        <svg id="cart-icon" viewBox="0 0 576 512">
          <path
            d="M 528.12 301.319 l 47.273 -208 C 578.806 78.301 567.391 64 551.99 64 H 159.208 l -9.166 -44.81 C 147.758 8.021 137.93 0 126.529 0 H 24 C 10.745 0 0 10.745 0 24 v 16 c 0 13.255 10.745 24 24 24 h 69.883 l 70.248 343.435 C 147.325 417.1 136 435.222 136 456 c 0 30.928 25.072 56 56 56 s 56 -25.072 56 -56 c 0 -15.674 -6.447 -29.835 -16.824 -40 h 209.647 C 430.447 426.165 424 440.326 424 456 c 0 30.928 25.072 56 56 56 s 56 -25.072 56 -56 c 0 -22.172 -12.888 -41.332 -31.579 -50.405 l 5.517 -24.276 c 3.413 -15.018 -8.002 -29.319 -23.403 -29.319 H 218.117 l -6.545 -32 h 293.145 c 11.206 0 20.92 -7.754 23.403 -18.681 Z"
          ></path>
        </svg>
        <div id="cart-count">{{$store.state.cart.numberOfItems}}</div>
        </router-link>
      </div>
    </section>
  </header>
</template>

<script>
import HeaderDropdownMenu from "@/components/HeaderDropdown";
import HeaderSearchBar from "@/components/HeaderSearchBar";
export default {
  name: "AppHeader",
  components: { HeaderSearchBar, HeaderDropdownMenu },
  component: {
    HeaderDropdownMenu,
  },
};
</script>

<style scoped>
header {
  background: var(--secondary-background-color);
  display: flex;
  padding: 1em;
  gap: 0;
  justify-content: space-between;
  min-width: 600px;
}

.header-button {
  background-color: #e6e6e6;
  border: none;
  border-radius: 10px;
  height: 35px;
  align-self: center;
  color: #364a52;
}

.bookstore-logo-and-text {
  display: inline-flex;
}

.bookstore-logo-and-text a {
  align-self: center;
}

.logo-text {
  font-family: var(--title-font-family);
  font-size: 34px;
  font-weight: bolder;
  color: #e6e6e6;
}

.search-and-category-bar {
  display: inline-flex;
  align-self: center;
}

.login-button-and-shopping-cart {
  display: inline-flex;
  align-self: center;
}

#login-button {
  margin-right: 0.5em;
  align-self: center;
  padding-left: 2.3em;
  position: relative;
}

#login-button-icon {
  position: absolute;
  top: 0.6em;
  left: 1em;
  z-index: 0;
  fill: var(--highlight-color);
  width: 0.85em;
}

#shopping-cart {
  position: relative;
}

#cart-icon {
  fill: var(--highlight-color);
  width: 2.4em;
  margin-top: 0.15em;
}

#cart-count {
  position: absolute;
  top: 0.7em;
  left: 1.65em;
  font-size: 12px;
  font-weight: bold;
  color: var(--secondary-background-color);
  z-index: 0;
}

@media (max-width: 800px) {
  header {
    flex-direction: column;
  }
}
</style>
