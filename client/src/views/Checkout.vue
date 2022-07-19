<template>

  <div class="checkout-page">

    <section v-if="$store.state.cart.numberOfItems === 0" class="empty-checkout-page">
      <p>Your cart is empty. Please add an item to your cart in order to checkout.</p>
      <div class="cart-buttons-top">
        <router-link :to="'/category/' + $store.state.selectedCategoryName">
          <button class="button secondary-button">Continue Shopping</button>
        </router-link>
      </div>
    </section>

    <section v-else class="non-empty-checkout-page">
      <form @submit.prevent="submitOrder">
        <div v-if="!$v.name.$error" class="label-input-valid">
          <label for="name-valid">Name</label>
          <input
              type="text"
              size="23"
              id="name-valid"
              name="name"
              v-model.lazy="$v.name.$model"
          />
        </div>
        <div v-else class="label-input-invalid">
          <label for="name-invalid">Name</label>
          <input
              type="text"
              size="23"
              id="name-invalid"
              name="name"
              v-model.lazy="$v.name.$model"
          />
        </div>
        <template v-if="$v.name.$error">
          <span class="error" v-if="!$v.name.required">Name is required</span>
          <span class="error" v-else-if="!$v.name.minLength">
            Name must have at least
            {{ $v.name.$params.minLength.min }} letters.
          </span>
          <span class="error" v-else-if="!$v.name.maxLength">
            Name can have at most
            {{ $v.name.$params.maxLength.max }} letters.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>

        <div v-if="!$v.address.$error" class="label-input-valid">
          <label for="address-valid">Address</label>
          <input
              type="text"
              size="23"
              id="address-valid"
              name="address"
              v-model.lazy="$v.address.$model"
          />
        </div>
        <div v-else class="label-input-invalid">
          <label for="address-invalid">Address</label>
          <input
              type="text"
              size="23"
              id="address-invalid"
              name="address"
              v-model.lazy="$v.address.$model"
          />
        </div>
        <template v-if="$v.address.$error">
          <span class="error" v-if="!$v.address.required">Address is required</span>
          <span class="error" v-else-if="!$v.address.minLength">
            Address must have at least
            {{ $v.address.$params.minLength.min }} characters.
          </span>
          <span class="error" v-else-if="!$v.address.maxLength">
            Address can have at most
            {{ $v.address.$params.maxLength.max }} characters.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>

        <div v-if="!$v.phone.$error" class="label-input-valid">
          <label for="phone-valid">Phone</label>
          <input
              class="textField"
              type="text"
              size="23"
              id="phone-valid"
              name="phone"
              v-model.lazy="$v.phone.$model"
          />
        </div>
        <div v-else clas="label-input-invalid">
          <label for="phone-invalid">Phone</label>
          <input
              class="textField"
              type="text"
              size="23"
              id="phone-invalid"
              name="phone"
              v-model.lazy="$v.phone.$model"
          />
        </div>
        <template v-if="$v.phone.$error">
          <span class="error" v-if="!$v.phone.required">Phone is required</span>
          <span class="error" v-else-if="!$v.phone.phone">
            Phone number is invalid.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>

        <div v-if="!$v.email.$error" class="label-input-valid">
          <label for="email-valid">Email</label>
          <input type="text"
                 size="23"
                 id="email-valid"
                 name="email"
                 v-model.lazy="$v.email.$model"
          />
        </div>
        <div v-else class="label-input-invalid">
          <label for="email-invalid">Email</label>
          <input type="text"
                 size="23"
                 id="email-invalid"
                 name="email"
                 v-model.lazy="$v.email.$model"
          />
        </div>
        <template v-if="$v.email.$error">
          <span class="error" v-if="!$v.email.required">Email address is required</span>
          <span class="error" v-else-if="!$v.email.email">
            Email address is invalid.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>

        <div v-if="!$v.ccNumber.$error" class="label-input-valid">
          <label for="ccNumber-valid">Credit Card</label>
          <input type="text"
                 size="23"
                 id="ccNumber-valid"
                 name="ccNumber"
                 v-model.lazy="$v.ccNumber.$model"
          />
        </div>
        <div v-else class="label-input-invalid">
          <label for="ccNumber-invalid">Credit Card</label>
          <input type="text"
                 size="23"
                 id="ccNumber-invalid"
                 name="ccNumber"
                 v-model.lazy="$v.ccNumber.$model"
          />
        </div>
        <template v-if="$v.ccNumber.$error">
          <span class="error" v-if="!$v.ccNumber.required">Credit card number is required</span>
          <span class="error" v-else-if="!$v.ccNumber.creditCard">
            Credit card number is invalid.
          </span>
          <span class="error" v-else> An unexpected error occurred. </span>
        </template>

        <div class="label-input-valid">
          <label>Exp Date</label>
          <select v-model="ccExpiryMonth">
            <option
                v-for="(month, index) in months"
                :key="index"
                :value="index + 1"
            >
              {{ month }} ({{ index + 1 }})
            </option>
          </select>

          <select v-model="ccExpiryYear">
            <option v-for="(i, n) in 16" :key="i" :value="n">
              {{yearFrom(n)}}
            </option>
          </select>
        </div>

        <input
            type="submit"
            name="submit"
            class="button cta-button"
            id="complete-purchase-button"
            :disabled="checkoutStatus == 'PENDING'"
            value="Complete Purchase"
        />
      </form>

      <p>
        Your card will be charged <b><Price :amount="$store.state.cart.subtotal + $store.state.cart.surcharge"/></b>
        <br>
        (<b><Price :amount="$store.state.cart.subtotal"/></b> + <b><Price :amount="$store.state.cart.surcharge"/></b> shipping)
      </p>

      <section v-show="checkoutStatus != ''" class="checkoutStatusBox">
        <div v-if="checkoutStatus == 'ERROR'">
          Error: Please fix the problems above and try again.
        </div>

        <div v-else-if="checkoutStatus == 'PENDING'">Processing...</div>

        <div v-else-if="checkoutStatus == 'OK'">Order placed...</div>

        <div v-else>
          <!-- "checkoutStatus == 'SERVER_ERROR'" -->
          An unexpected error occurred, please try again.
        </div>
      </section>
    </section>
  </div>
</template>

<script>
import Price from "@/components/Price";

import {
  required,
  email,
  minLength,
  maxLength,
} from "vuelidate/lib/validators";

import isCreditCard from "validator/lib/isCreditCard";
import isMobilePhone from "validator/lib/isMobilePhone";

const phone = value => isMobilePhone(value, "en-US");
const creditCard = value => isCreditCard(value);

export default {
  components: {Price},
  data() {
    return {
      name: "",
      address: "",
      phone: "",
      email: "",
      ccNumber: "",
      ccExpiryMonth: new Date().getMonth() + 1,
      ccExpiryYear: 0,
      checkoutStatus: "",
    };
  },
  validations: {
    name: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    address: {
      required,
      minLength: minLength(4),
      maxLength: maxLength(45),
    },
    phone: {
      required,
      phone
    },
    email: {
      required,
      email
    },
    ccNumber: {
      required,
      creditCard
    }
  },

  computed: {
    cart() {
      return this.$store.state.cart;
    },
    months() {
      return [
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December",
      ];
    }
  },
  methods: {
    submitOrder() {
      console.log("Submit order");
      this.$v.$touch(); // Ensures validators always run
      if (this.$v.$invalid) {
        this.checkoutStatus = "ERROR";
      } else {
        this.checkoutStatus = "PENDING";
        setTimeout(() => {
          this.$store
              .dispatch('placeOrder', {
                name: this.name,
                address: this.address,
                phone: this.phone,
                email: this.email,
                ccNumber: this.ccNumber,
                ccExpiryMonth: this.ccExpiryMonth,
                ccExpiryYear: this.yearFrom(this.ccExpiryYear)
              })
              .then(() => {
                this.checkoutStatus = 'OK'
                this.$router.push({ name: 'confirmation' })
              })
              .catch(reason => {
                this.checkoutStatus = 'SERVER_ERROR'
                console.log('Error placing order', reason)
              })
        }, 1000);
      }
    },
    /* NOTE: For example yearFrom(0) == 2021 */
    yearFrom(index) {
      return new Date().getFullYear() + index;
    }
  }
};
</script>

<style scoped>
.checkout-page {
  padding: 1em;
  background-color: var(--page-background-color-light);
}

.empty-checkout-page,
.non-empty-checkout-page {
  display: flex;
  padding: 1em;
  flex-direction: column;
  text-align: center;
  gap: 1em;
}

form {
  display: flex;
  flex-direction: column;
  align-self: center;
  background: var(--page-background-color);
  padding: 1em;
  border-radius: 5px;
}

.label-input-valid {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.5em;
}

.label-input-invalid {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 0.2em;
}

form > div > input,
form > div > select {
  margin-left: 0.5em;
  border-radius: 5px;
  border: 1px solid black;
}

.error {
  color: red;
  text-align: right;
  margin-bottom: 0.8em;
}

.checkoutStatusBox {
  padding: 0.5em;
  text-align: center;
}

#complete-purchase-button {
  width: 12em;
  text-align: center;
  justify-content: center;
  align-content: center;
  align-self: center;
  margin-top: 0.5em;
}

</style>
