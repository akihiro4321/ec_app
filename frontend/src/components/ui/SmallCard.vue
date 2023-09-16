<template>
  <div class="small-card">
    <slot></slot>
  </div>
</template>

<style scoped>
.small-card {
  display: flex;
  align-items: center;
  padding: 1rem;
  word-wrap: break-word;
  background-color: #fff;
  background-clip: border-box;
  border: solid 1px #0000006f;
  border-radius: 0.25rem;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.4);
  transition: 0.5s;
  margin: 0.5rem auto;
  max-width: 30rem;
}
</style>
