<template>
  <div class="cart-item">
    <!-- <img src="product1.jpg" alt="Product Pic" /> -->
    <div class="cart-item-details">
      <h3>{{ productName }}</h3>
      <p>
        TotalPrice : <span class="cart-item-price">¥{{ totalPrice }}</span>
      </p>
      <label for="quantity-product1">Quantity : </label>
      <input
        class="quantity-input"
        type="number"
        v-bind:value="quantity"
        v-on:input="changeQuantity"
      />
    </div>
    <div class="cart-actions">
      <base-button type="button" mode="remove" @click="removeFromCart"
        >Remove</base-button
      >
    </div>
  </div>
</template>

<script>
export default {
  props: ['id', 'productId', 'productName', 'totalPrice', 'quantity'],
  methods: {
    changeQuantity(event) {
      if (event.target.value < 0) {
        event.target.value = 0;
        return;
      }
      this.$emit('changeQuantity', this.productId, Number(event.target.value));
    },
    async removeFromCart() {
      this.$store.dispatch('customers/removeFromCart', this.id);
      this.$emit('removeFromCart', this.id);
    },
  },
};
</script>

<style scoped>
.cart-item {
  border: 1px solid #ddd;
  padding: 10px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}
.cart-item img {
  max-width: 100px;
  margin-right: 10px;
}
.cart-item-details {
  flex: 1;
}
.cart-item h3 {
  margin: 0;
}
.cart-item p {
  margin: 0;
}
.cart-item-price {
  font-weight: bold;
  color: #e53935;
}
.quantity-input {
  width: 40px;
  text-align: center;
}
/* 数値の入力欄にスピナーを常時表示する */
input[type='number']::-webkit-inner-spin-button,
input[type='number']::-webkit-outer-spin-button {
  opacity: 1;
}
</style>
