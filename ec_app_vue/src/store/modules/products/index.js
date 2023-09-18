import actions from './actions.js';
import getters from './getters.js';
import mutations from './mutations.js';

export default {
  namespaced: true,
  state() {
    return {
      products: [],
    };
  },
  mutations,
  actions,
  getters,
};

// ex) state.products
// [
//   {
//     productId: 5,
//     description: "product description",
//     imageUrl: "products/~~.jpg",
//     productName: "productName",
//     price: 104300,
//     status: 0,
//     createdAt: "2023-09-03T23:32:11.983021Z",
//     categoryId: 2
//   },
//   ...
// ]
