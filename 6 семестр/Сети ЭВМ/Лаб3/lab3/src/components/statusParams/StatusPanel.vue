<template>
  <b>status</b>
  <status-item v-for="statusItem in statusItems" :key="statusItem.title" v-bind:item="statusItem"></status-item>
</template>

<script>
import StatusItem from '@/components/statusParams/StatusItem.vue'
export default {
    components: {
      StatusItem
    },
    props: ['statusItems']
}
</script>

<style>

</style>