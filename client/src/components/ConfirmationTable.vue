<template>
  <table>
    <tr
      v-for="(item, index) in this.$store.state.orderDetails.lineItems"
      :key="item.productId"
    >
      <td>
        {{ $store.state.orderDetails.books[index].title }}
      </td>
      <td>{{ item.quantity }}</td>
      <td><Price :amount="item.quantity * $store.state.orderDetails.books[index].price"/></td>
    </tr>
  </table>
</template>

<script>
import Price from '@/components/Price';

export default {
  name: "ConfirmationTable",
  components: {Price},
};
</script>

<style scoped>
table {
  border: 1px black solid;
  width: fit-content;
  margin-top: 1em;
  margin-bottom: 1em;
  align-self: center;
  background: var(--page-background-color);
}

td {
  display: table-cell;
  padding: 0.5em 0.5em;
  border: 1px black solid;
  background: var(--page-background-color);
  vertical-align: middle;
}

td:nth-child(1) {
  text-align: left;
}

td:nth-child(2) {
  text-align: center;
}

td:nth-child(3) {
  text-align: right;
}
</style>
