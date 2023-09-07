export default {
  setCart(state, payload) {
    state.cart = payload;
  },
  removeFromCart(state, payload) {
    const productId = payload.id;
    state.cart.cartItems = state.cart.cartItems.filter(
      (cartItem) => cartItem.product.id !== productId
    );
  },
  changeQuantity(state, payload) {
    const productId = payload.productId;
    const quantity = payload.quantity;
    state.cart.cartItems = state.cart.cartItems.map((cartItem) => {
      if (cartItem.product.id === productId) {
        const newCartItem = cartItem;
        newCartItem.quantity = quantity;
        return newCartItem;
      }
      return cartItem;
    });
  },
  clearCart(state) {
    state.cart = null;
  },
};
