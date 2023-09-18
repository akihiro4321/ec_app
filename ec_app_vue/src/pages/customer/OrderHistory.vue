<template>
  <div class="purchase-history-container">
    <h1 class="purchase-history-header">Purchase History</h1>
    <div v-for="order in orders" :key="order.orderId">
      <div v-for="product in order.products" :key="product.productId">
        <order-item
          :product-name="product.productName"
          :price="product.orderPrice"
          :quantity="product.quantity"
          :image-url="product.imageUrl"
          :order-date="order.orderItem.orderDate"
        ></order-item>
      </div>
    </div>
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
      orders: [],
    };
  },
  created() {
    this.loadOrderHistory();
  },
  methods: {
    async loadOrderHistory() {
      await this.$store.dispatch('customers/loadOrderHistory');
      const history = this.$store.getters['customers/getOrderHistory'];
      this.orders = history.orders;
    },
  },
};
</script>

<style scoped>
.purchase-history-container {
  max-width: 800px;
  margin: 50px auto;
  background-color: white;
  padding: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.4);
}

.purchase-history-header {
  font-size: 1.5rem;
  margin-bottom: 20px;
}
</style>
