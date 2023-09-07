import { createStore } from 'vuex';
import customerModule from './modules/customers/index.js';
import productModule from './modules/products/index.js';

const store = createStore({
  modules: {
    products: productModule,
    customers: customerModule,
  },
});

export default store;
