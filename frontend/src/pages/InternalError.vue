<template>
  <body>
    <div class="error-container">
      <div class="error-code">500</div>
      <div class="error-message">Internal Server Error</div>
      <router-link to="/">Back to Home</router-link>
    </div>
  </body>
</template>

<style scoped>
/* CSS スタイルをここに記述 */

body {
  font-family: Arial, sans-serif;
  background-color: #f9f9f9;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.error-container {
  text-align: center;
}

.error-code {
  font-size: 5rem;
  color: #e53935;
}

.error-message {
  font-size: 1.5rem;
  margin-top: 20px;
}

.back-home-link {
  font-size: 1.2rem;
  margin-top: 20px;
  color: #007bff;
  text-decoration: none;
}

.back-home-link:hover {
  text-decoration: underline;
}
</style>
