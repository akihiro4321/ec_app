<template>
  <div class="order-item">
    <div class="order-item-summary">
      <img :src="imageUrlPath" alt="Product 1" />
      <div class="order-item-details">
        <p class="order-item-name">{{ productName }}</p>
        <p>Price: ¥{{ price }}</p>
        <p>Quantity: {{ quantity }}</p>
        <p>Subtotal: ¥{{ subTotal }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['productName', 'price', 'subTotal', 'quantity', 'imageUrl'],
  computed: {
    imageUrlPath() {
      return require(`@/assets/images/${this.imageUrl}`);
    },
  },
};
</script>

<style scoped>
.order-item {
  border: 1px solid #ddd;
  padding: 20px;
  margin-bottom: 20px;
}

.order-item img {
  max-width: 100px;
  height: auto;
  margin-right: 20px;
}

.order-item-details {
  flex: 1;
}

.order-item-name {
  font-weight: bold;
}

.order-item-summary {
  display: flex;
  align-items: center;
}
</style>
