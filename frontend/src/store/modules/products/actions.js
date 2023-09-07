export default {
  async loadProducts(context, payload) {
    if (!context.getters.products || context.getters.products.length === 0) {
      // storeに存在しない場合のみロードする
      // TODO 全データロードするのはデータ量が増えると重いから必要な分だけロードするに変えたい
      const response = await window.axios.get('/api/products');
      const responseData = response.data;
      if (!(response < 300)) {
        // error ...
      }

      context.commit('setProducts', responseData);
    }
  },
};
