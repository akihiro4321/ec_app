<template>
  <div class="cart-container">
    <h1>Your Shopping Cart</h1>
    <div v-for="cartItem in cartItems" :key="cartItem.id">
      <cart-item
        :id="cartItem.id"
        :product-id="cartItem.product.productId"
        :product-name="cartItem.product.productName"
        :total-price="cartItem.totalPrice"
        :quantity="cartItem.quantity"
        @changeQuantity="changeQuantity"
        @removeFromCart="removeFromCart"
      ></cart-item>
    </div>

    <div class="cart-total">
      Total : <span class="cart-item-price">¥{{ totalCost }}</span>
    </div>
    <base-button link to="/orders" class="outline">
      Proceed to Checkout
    </base-button>
  </div>
</template>

<script>
import CartItem from '../../components/customer/CartItem.vue';

export default {
  components: {
    CartItem,
  },
  data() {
    return {
      cartItems: null,
      totalCost: 0,
    };
  },
  async created() {
    await this.loadCart();
    this.getCart();
  },
  methods: {
    async loadCart() {
      await this.$store.dispatch('customers/loadCart');
    },
    getCart() {
      const cart = this.$store.getters['customers/getCart'];
      this.cartItems = cart.cartItems;
      for (let cartItem of this.cartItems) {
        const totalPrice = this.calcPrice(
          cartItem.product.price,
          cartItem.quantity
        );
        cartItem['totalPrice'] = totalPrice;
      }
      this.totalCost = this.calcTotalCost();
    },
    calcPrice(price, quantity) {
      return price * quantity;
    },
    calcTotalCost() {
      const totalCost = this.cartItems.reduce(function (sum, cartItem) {
        return sum + cartItem.totalPrice;
      }, 0);
      return totalCost;
    },
    changeQuantity(productId, quantity) {
      const targetCartItem = this.cartItems.find(
        (cartItem) => cartItem.product.productId === productId
      );
      targetCartItem.quantity = quantity;
      targetCartItem.totalPrice = this.calcPrice(
        targetCartItem.product.price,
        quantity
      );
      this.totalCost = this.calcTotalCost();

      this.$store.dispatch('customers/changeQuantity', {
        productId,
        quantity,
      });
    },
    removeFromCart(cartItemId) {
      this.cartItems = this.cartItems.filter(
        (cartItem) => cartItem.id != cartItemId
      );
      this.totalCost = this.calcTotalCost();
    },
  },
};
</script>

<style scoped>
body {
  font-family: Arial, sans-serif;
}
.cart-container {
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
}
.cart-item {
  border: 1px solid #ddd;
  padding: 10px;
  margin-bottom: 10px;
  display: flex;
  align-items: center;
}
.cart-item img {
  max-width: 100px;
  margin-right: 10px;
}
.cart-item-details {
  flex: 1;
}
.cart-item h3 {
  margin: 0;
}
.cart-item p {
  margin: 0;
}
.cart-item-price {
  font-weight: bold;
  color: #e53935;
}
.cart-actions {
  text-align: right;
}
.cart-total {
  text-align: right;
  margin-top: 20px;
  font-size: 1.2rem;
}
.checkout-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 10px 20px;
  cursor: pointer;
}
.quantity-input {
  width: 40px;
  text-align: center;
}
</style>
