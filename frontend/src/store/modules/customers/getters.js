export default {
  getCart(state) {
    return state.cart;
  },
  cartIsNotEmpty(state, getters) {
    let isNotEmpty = false;
    const cart = getters.getCart;
    if (cart !== null && cart.cartItems.length > 0) {
      isNotEmpty = true;
    }
    return isNotEmpty;
  },
};
