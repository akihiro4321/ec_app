<template>
  <div>
    <section>
      <div class="search-container">
        <input
          class="search-input"
          type="text"
          name="query"
          placeholder="Search..."
          v-model.trim="keyword"
        />
        <button class="search-button" @click="filterByKeyword">Search</button>
      </div>
    </section>
    <section>
      <h2>All Products</h2>
      <product-item
        v-for="product in selectedProducts"
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
  data() {
    return {
      selectedProducts: [],
      keyword: '',
    };
  },
  async created() {
    await this.loadProducts();
    this.selectedProducts = this.$store.getters['products/products'];
  },
  methods: {
    async loadProducts() {
      await this.$store.dispatch('products/loadProducts');
    },
    filterByKeyword() {
      if (this.keyword === '') {
        this.resetFilter();
        return;
      }
      const filteredProducts = this.selectedProducts.filter((product) => {
        return product.productName
          .toLowerCase()
          .includes(this.keyword.toLowerCase());
      });
      this.selectedProducts = filteredProducts;
    },
    resetFilter() {
      this.selectedProducts = this.$store.getters['products/products'];
    },
  },
};
</script>

<style scoped>
.search-container {
  text-align: center;
  margin: 50px auto;
}

.search-input {
  padding: 10px;
  width: 300px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1rem;
}

.search-button {
  padding: 10px 20px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 1rem;
  cursor: pointer;
}

.search-button:hover {
  background-color: #0056b3;
}
</style>
