import actions from './actions.js';
import getters from './getters.js';
import mutations from './mutations.js';

export default {
  namespaced: true,
  state() {
    return {
      cart: null,
      orderHistory: null,
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

// ex) state.orderHistory
// {
//   orders: [
//     {
//       orderItem: {
//         orderId: 1,
//         orderDate: '2023-09-09T02:33:27.774681Z',
//         totalCost: 51300,
//       },
//       products: [
//         {
//           productId: 1,
//           productName: 'Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops',
//           imageUrl: null,
//           orderPrice: 1000,
//           quantity: 3,
//         },
//         {
//           productId: 2,
//           productName: 'Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops',
//           imageUrl: null,
//           orderPrice: 2000,
//           quantity: 2,
//         },
//       ],
//     },
//     another order ...
//   ];
// }
