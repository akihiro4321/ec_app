import { createStore } from 'vuex';
import authModule from './modules/auth/index.js';
import customerModule from './modules/customers/index.js';
import productModule from './modules/products/index.js';

const store = createStore({
  modules: {
    products: productModule,
    customers: customerModule,
    auth: authModule,
  },
});

export default store;
