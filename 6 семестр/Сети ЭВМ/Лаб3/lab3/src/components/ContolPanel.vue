<template>
  <input-panel :items="inputItems" :setItem="setItem" />
  <status-panel :statusItems="statusItems" />
</template>

<script>
import InputPanel from "@/components/inputParams/InputPanel.vue";
import StatusPanel from "@/components/statusParams/StatusPanel.vue";

export default {
  components: {
    InputPanel,
    StatusPanel,
  },
  props: ["statusItems"],
  data() {
    return {
      inputItems: [
        { title: "Вес аппарата, кг", val: "1500" },
        { title: "Вес топлива, кг", val: "50" },
        { title: "Вес импульса, кг", val: "10" },
        { title: "Скорость истечения, м/c", val: "300" },
        { title: "Max ск-ть касания, м/c", val: "10" },
        { title: "Уск. своб. падения, м/c", val: "1.6" },
        { title: "Лимит времени, с", val: "120" },
        { title: "Нач. расстояние, м", val: "1000" },
      ],
    };
  },
  methods: {
      setItem(key, value) {
          console.log(key);
          console.log(value);
          this.inputItems[0].val = value;
      }
  }
};
</script>

<style>
</style>