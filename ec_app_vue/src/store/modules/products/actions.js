export default {
  async loadProducts(context, payload) {
    // TODO 全データロードするのはデータ量が増えると重いから必要な分だけロードするに変えたい
    try {
      const response = await window.axios.get('/api/products');
      const responseData = response.data;
      context.commit('setProducts', responseData);
    } catch (err) {
      // TODO handle error
    }
  },
};
