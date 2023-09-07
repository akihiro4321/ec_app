<template>
  <div>
    <section>
      <h2>Filter</h2>
    </section>
    <section>
      <h2>All Products</h2>
      <product-item
        v-for="product in getProducts"
        :key="product.productId"
        :id="product.productId"
        :product-name="product.productName"
        :description="product.description"
        :price="product.price"
        :image-url="product.imageUrl"
      ></product-item>
    </section>
  </div>
</template>

<script>
import ProductItem from '../../components/product/ProductItem.vue';
export default {
  components: {
    ProductItem,
  },
  computed: {
    getProducts() {
      return this.$store.getters['products/products'];
    },
  },
  created() {
    this.loadProducts();
  },
  methods: {
    async loadProducts() {
      await this.$store.dispatch('products/loadProducts');
    },
  },
};
</script>

<style scoped></style>
