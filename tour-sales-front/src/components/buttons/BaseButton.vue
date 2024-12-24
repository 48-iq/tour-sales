<template>
  <button class="button"><slot></slot></button>
</template>
<style scoped>
.button {
  font-size: 1.3rem;
  background-color: var(--color-dark-green);
  width: 100%;
  border-radius: 5px;
  border: none;
  padding: 5px;
  padding-left: 8px;
  cursor: pointer;
}
</style>
