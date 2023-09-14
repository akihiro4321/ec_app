export default {
  async loadProducts(context, payload) {
    // TODO 全データロードするのはデータ量が増えると重いから必要な分だけロードするに変えたい
    const response = await window.axios.get('/api/products');
    const responseData = response.data;
    if (!(response < 300)) {
      // error ...
    }

    context.commit('setProducts', responseData);
  },
};
