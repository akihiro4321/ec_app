<template>
  <div class="product-container">
    <!-- 商品説明欄 -->
    <img class="product-image" :src="imageUrl" alt="Product Image" />
    <div class="product-info">
      <h1>{{ productName }}</h1>
    </div>
    <div class="price">¥{{ price }}</div>
    <div class="description">
      <h2>Description</h2>
      <p>{{ description }}</p>
    </div>
    <base-button type="button" mode="add-to-cart" @click="addToCart"
      >Add to Cart</base-button
    >

    <!-- 商品レビュー欄 -->
    <div class="reviews">
      <h2>Customer Reviews</h2>
      <div class="review">
        <div class="rating">★★★★★</div>
        <p>Great product! I love it!</p>
      </div>
      <!-- 他のレビューも続く -->
    </div>
    <base-button type="button" mode="add-to-cart" @click="addToCart"
      >Add to Cart</base-button
    >

    <!-- 関連商品セクション -->
    <div class="related-products">
      <h2>Related Products</h2>
      <div class="product-card">
        <img src="related_product1.jpg" alt="Related Product 1" />
        <h3>Related Product 1</h3>
        <p>$149.99</p>
        <base-button type="button" mode="add-to-cart" @click="addToCart"
          >Add to Cart</base-button
        >
      </div>
      <div class="product-card">
        <img src="related_product2.jpg" alt="Related Product 2" />
        <h3>Related Product 2</h3>
        <p>$129.99</p>
        <base-button type="button" mode="add-to-cart" @click="addToCart"
          >Add to Cart</base-button
        >
      </div>
      <!-- 他の関連商品も同様に続く -->
    </div>
  </div>
</template>

<script>
export default {
  props: ['id'],
  data() {
    return {
      selectedProduct: null,
    };
  },
  computed: {
    productName() {
      return this.selectedProduct.productName;
    },
    description() {
      return this.selectedProduct.description;
    },
    price() {
      return this.selectedProduct.price;
    },
    imageUrl() {
      return require(`@/assets/images/${this.selectedProduct.imageUrl}`);
    },
  },
  created() {
    this.loadProducts();
    this.selectedProduct = this.$store.getters['products/products'].find(
      (product) => product.productId === Number(this.id)
    );
  },
  methods: {
    addToCart() {
      const payload = {
        productId: this.id,
        quantity: 1,
      };
      this.$store.dispatch('customers/addToCart', payload);
    },
    async loadProducts() {
      await this.$store.dispatch('products/loadProducts');
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
