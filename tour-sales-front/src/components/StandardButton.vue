
<template>
  <button class="standard-button"><slot/> </button>
</template>

<style scoped>
  .standard-button {
    background-color: var(--color-brown);
    width: 100%;
    height: 30px;
    cursor: pointer;
    padding: 5px;
  }
</style>
