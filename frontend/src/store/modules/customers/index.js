import actions from './actions.js';
import getters from './getters.js';
import mutations from './mutations.js';

export default {
  namespaced: true,
  state() {
    return {
      cart: null,
    };
  },
  mutations,
  actions,
  getters,
};

// ex) state.cart
// {
//   cartItems: [
//     {
//       id: 1,
//       product: {
//         productId: 5,
//         description: "product description",
//         imageUrl: "products/~~.jpg",
//         productName: "productName",
//         price: 104300,
//       },
//       quantity: 1
//     },
//     ...
//   ]
// }
