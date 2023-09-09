<template>
  <div class="order-confirmation-container">
    <h1 class="order-confirmation-header">Order Confirmation</h1>
    <div v-for="cartItem in cartItems" :key="cartItem.id">
      <order-item
        :product-name="cartItem.product.productName"
        :price="cartItem.product.price"
        :sub-total="cartItem.subTotal"
        :quantity="cartItem.quantity"
        :image-url="cartItem.product.imageUrl"
      ></order-item>
    </div>
    <h2 class="order-total">Total: ¥{{ totalCost }}</h2>
    <base-button v-if="cartIsNotEmpty" @click="confirmOrder">
      Confirm Order
    </base-button>
    <base-button link to="/" mode="outline">Back to Home</base-button>
  </div>
</template>

<script>
import OrderItem from '../../components/customer/OrderItem.vue';

export default {
  components: {
    OrderItem,
  },
  data() {
    return {
      cartItems: null,
      totalCost: 0,
    };
  },
  computed: {
    cartIsNotEmpty() {
      return this.$store.getters['customers/cartIsNotEmpty'];
    },
  },
  created() {
    this.loadOrder();
    this.getOrder();
  },
  methods: {
    async loadOrder() {
      await this.$store.dispatch('customers/loadCart');
    },
    getOrder() {
      const cart = this.$store.getters['customers/getCart'];
      this.cartItems = cart.cartItems;
      for (let cartItem of this.cartItems) {
        const subTotal = this.calcPrice(
          cartItem.product.price,
          cartItem.quantity
        );
        cartItem['subTotal'] = subTotal;
      }
      this.totalCost = this.calcTotalCost();
    },
    calcPrice(price, quantity) {
      return price * quantity;
    },
    calcTotalCost() {
      const totalCost = this.cartItems.reduce(function (sum, cartItem) {
        return sum + cartItem.totalPrice;
      }, 0);
      return totalCost;
    },
    confirmOrder() {
      this.$store.dispatch('customers/confirmOrder', {
        cartItems: this.cartItems,
        totalCost: this.totalCost,
      });
    },
  },
};
</script>

<style scoped>
.order-confirmation-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}

.order-confirmation-header {
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.order-total {
  font-size: 1.2rem;
  color: #e53935;
  font-weight: bold;
  margin-top: 20px;
}
</style>
