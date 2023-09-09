export default {
  async loadCart(context, payload) {
    const response = await window.axios.get('/api/cart');
    const responseData = response.data;
    if (!(response < 300)) {
      // error ...
    }
    context.commit('setCart', responseData);
  },
  async addToCart(context, payload) {
    const request = {
      productId: payload.productId,
      quantity: payload.quantity,
    };
    await window.axios.post('/api/cart/addToCart', request);
    // handle response
  },
  async removeFromCart(context, payload) {
    const productId = Number(payload);
    await window.axios.delete('/api/cart/removeFromCart', {
      params: { productId: productId },
    });
    // handle response
    context.commit('removeFromCart', productId);
  },
  async changeQuantity(context, payload) {
    const request = {
      productId: payload.productId,
      quantity: payload.quantity,
    };
    await window.axios.put('/api/cart/changeQuantity', request);
    // handle response
    context.commit('changeQuantity', request);
  },
  async confirmOrder(context, payload) {
    const request = {
      cartItems: payload.orderItems,
      totalCost: payload.totalCost,
    };
    const response = await window.axios.post('/api/order/checkout', request);
    if (!(response < 300)) {
      // error ...
    }
    context.commit('clearCart');
  },
  async loadOrderHistory(context, payload) {
    const response = await window.axios.get('/api/order/history');
    const responseData = response.data;
    if (!(response < 300)) {
      // error ...
    }
    context.commit('setOrderHistory', responseData);
  },
};
