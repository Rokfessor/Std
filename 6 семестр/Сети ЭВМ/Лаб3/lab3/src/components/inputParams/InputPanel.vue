<template>
  <input-item
    v-for="(inputItem, i) in items"
    v-bind:item="items[i]"
    :key="i"
    :setItem="setItem"
  />
</template>

<script>
import InputItem from "@/components/inputParams/InputItem.vue";
export default {
  components: {
    InputItem,
  },
  props:
   ['items', 'setItem']
};
</script>

<style>
</style>