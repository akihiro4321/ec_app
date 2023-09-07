<template>
  <div class="purchase-history-container">
    <h1 class="purchase-history-header">Purchase History</h1>
    <div class="order-item">
      <div class="order-item-details">
        <div>
          <p class="order-item-name">Product Name 1</p>
          <p>Price: <span class="order-item-price">$49.99</span></p>
          <p>Quantity: 2</p>
          <p>Subtotal: $99.98</p>
        </div>
        <p class="order-item-total">Total: $99.98</p>
      </div>
    </div>
    <div class="order-item">
      <div class="order-item-details">
        <div>
          <p class="order-item-name">Product Name 2</p>
          <p>Price: <span class="order-item-price">$29.99</span></p>
          <p>Quantity: 1</p>
          <p>Subtotal: $29.99</p>
        </div>
        <p class="order-item-total">Total: $29.99</p>
      </div>
    </div>
    <!-- 他の購入履歴アイテムをここに追加 -->
  </div>
</template>

<style scoped>
.purchase-history-container {
  max-width: 800px;
  margin: 50px auto;
  background-color: white;
  padding: 20px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.purchase-history-header {
  font-size: 1.5rem;
  margin-bottom: 20px;
}

.order-item {
  border: 1px solid #ddd;
  padding: 20px;
  margin-bottom: 20px;
}

.order-item-details {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.order-item-name {
  font-weight: bold;
}

.order-item-price {
  color: #e53935;
}

.order-item-total {
  font-weight: bold;
  font-size: 1.2rem;
}
</style>
