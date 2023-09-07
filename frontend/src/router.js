import { createRouter, createWebHistory } from "vue-router";

import NotFound from "./pages/NotFound.vue";
import ContactShop from "./pages/customer/ContactShop.vue";
import CustomerCart from "./pages/customer/CustomerCart.vue";
import CustomerOrder from "./pages/customer/CustomerOrder.vue";
import OrderHistory from "./pages/customer/OrderHistory.vue";
import ProductDetail from "./pages/product/ProductDetail.vue";
import ProductList from "./pages/product/ProductList.vue";

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: "/", redirect: "/products" },
    { path: "/products", component: ProductList },
    { path: "/products/:id", component: ProductDetail, props: true },
    { path: "/orders", component: CustomerOrder },
    { path: "/orders/history", component: OrderHistory },
    { path: "/cart", component: CustomerCart },
    { path: "/contact", component: ContactShop },
    { path: "/:notFound(.*)", component: NotFound },
  ],
});

export default router;
