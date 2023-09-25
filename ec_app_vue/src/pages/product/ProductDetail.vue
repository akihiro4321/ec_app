<template>
  <div>
    <base-dialog :show="!isAuthenticated" title="Unauthorized" @close="close">
      Please Login
    </base-dialog>
    <div class="product-container">
      <!-- 商品説明欄 -->
      <img class="product-image" :src="imageUrl" alt="Product Image" />
      <div class="product-info">
        <h1>{{ selectedProduct.productName }}</h1>
      </div>
      <div class="price">¥{{ selectedProduct.price }}</div>
      <div class="description">
        <h2>Description</h2>
        <p>{{ selectedProduct.description }}</p>
      </div>
      <base-button type="button" mode="add-to-cart" @click="addToCart"
        >Add to Cart</base-button
      >
    </div>
  </div>
</template>

<script>
export default {
  props: ['id'],
  data() {
    return {
      selectedProduct: {
        productName: '',
        description: '',
        price: 0,
        imageUrl: '',
      },
      imageUrl: '',
      isAuthenticated: true,
    };
  },
  created() {
    this.loadProducts();
  },
  methods: {
    addToCart() {
      this.isAuthenticated = true;
      console.log(this.$store.getters.isAuthenticated);
      if (!this.$store.getters.isAuthenticated) {
        this.isAuthenticated = false;
        return;
      }
      const payload = {
        productId: this.id,
        quantity: 1,
      };
      this.$store.dispatch('customers/addToCart', payload);
    },
    async loadProducts() {
      await this.$store.dispatch('products/loadProducts');
      this.selectedProduct = this.$store.getters['products/products'].find(
        (product) => product.productId === Number(this.id)
      );
      this.imageUrl = require(`@/assets/images/${this.selectedProduct.imageUrl}`);
    },
    close() {
      this.isAuthenticated = true;
    },
  },
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
}
.product-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}
.product-image {
  max-width: 30%;
  margin-bottom: 20px;
}
.product-info {
  text-align: center;
  margin-bottom: 20px;
}
.price {
  font-size: 1.5rem;
  color: #e53935;
  margin-bottom: 10px;
}
.description {
  margin-bottom: 20px;
}
.reviews {
  margin-bottom: 20px;
}

/* 関連商品部分 */
.related-products {
  margin-top: 30px;
}
.product-card {
  display: inline-block;
  width: 200px;
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
  margin-right: 20px;
}
.product-card img {
  max-width: 20%;
  height: auto;
  margin-bottom: 10px;
}
.product-card h3 {
  font-size: 1rem;
  margin-bottom: 5px;
}
.product-card p {
  font-size: 0.9rem;
  color: #e53935;
  margin-bottom: 10px;
}
</style>
