<template>
  <div>
    <span>{{ item.title }}</span>
    <input
      type="number"
      :value="item.val"
      @input="setItem(key, $event.target.value)"
      id=""
    />
  </div>
</template>

<script>
export default {
  props: ['item', 'setItem', 'key'],
};
</script>

<style>
</style>