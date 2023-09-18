<template>
  <small-card>
    <img class="card-image" :src="imageUrlPath" />
    <div class="card-box">
      <h2 class="card-title">{{ productName }}</h2>
      <p class="card-description">{{ description }}</p>
      <p>¥{{ price }}</p>
      <base-button link :to="productDetailsLink">Details</base-button>
    </div>
  </small-card>
</template>

<script>
export default {
  props: ['id', 'productName', 'description', 'price', 'imageUrl'],
  computed: {
    productDetailsLink() {
      return this.$route.path + '/' + this.id;
    },
    imageUrlPath() {
      return require(`@/assets/images/${this.imageUrl}`);
    },
  },
};
</script>

<style scoped>
.card-image {
  width: 40%;
  height: 200px;
  object-fit: contain;
}
.card-box {
  padding-left: 2rem;
}
.card-title {
  margin-bottom: 0.5rem;
  font-size: 1.2rem;
  font-weight: bold;
}
.card-description {
  margin-bottom: 0.5rem;
  line-height: 1.5;
}
</style>
