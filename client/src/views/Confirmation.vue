<template>
  <div class="confirmation-page">
    <p>Thank you for your order!</p>
    <div class="confirmation-view">
      <div class="confirmation-info">
        <ul>
          <li>{{"Confirmation #: " + $store.state.orderDetails.order.confirmationNumber}}</li>
          <li>{{"Time: " + orderDateConverter($store.state.orderDetails.order.dateCreated)}}</li>
        </ul>
        <confirmation-table> </confirmation-table>
        <ul>
          <li>{{ $store.state.orderDetails.customer.customerName }}</li>
          <li>{{ $store.state.orderDetails.customer.address }}</li>
          <li>{{ $store.state.orderDetails.customer.email }}</li>
          <li>{{ $store.state.orderDetails.customer.phone }}</li>
          <br>
          <li>{{ ccNumberFormatter($store.state.orderDetails.customer.ccNumber)
                  + " " + ccExpDateFormatter($store.state.orderDetails.customer.ccExpDate) }}</li>
        </ul>
        <div id="customerInfo"></div>
      </div>
    </div>
    <div id="continue-shopping">
      <router-link :to="'/category/' + $store.state.selectedCategoryName">
        <button class="button secondary-button">Continue Shopping</button>
      </router-link>
    </div>
  </div>
</template>

<script>
import ConfirmationTable from "@/components/ConfirmationTable";
export default {
  name: "Confirmation",
  components: { ConfirmationTable },
  methods: {
    orderDateConverter(orderTimeEpoch){
      var d = new Date(orderTimeEpoch);
      return d.toLocaleDateString() + " " + d.toLocaleTimeString();
    },
    ccNumberFormatter(ccNumber) {
      return "************" + ccNumber.substring(ccNumber.length - 4);
    },
    ccExpDateFormatter(ccExpDateEpoch) {
      var d = new Date(ccExpDateEpoch);
      return d.getMonth() + 1 + "/" + d.getFullYear();
    }
  }
};
</script>

<style scoped>
.confirmation-page {
  padding: 1em;
  background-color: var(--page-background-color-light);
}

.confirmation-view {
  display: flex;
  justify-content: center;
  margin-top: 1em;
  margin-bottom: 1em;
}

.confirmation-info {
  display: flex;
  padding: 1em;
  flex-direction: column;
  background: var(--page-background-color);
  border-radius: 5px;
}

p {
  text-align: center;
}

#continue-shopping {
  text-align: center;
}
</style>
