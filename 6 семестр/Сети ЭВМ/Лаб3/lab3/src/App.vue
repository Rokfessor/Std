<template>
  <div>
    <control-panel 
      :statusItems="statusItems" 
       
    />
  </div>
</template>

<script>
import ControlPanel from "@/components/ContolPanel.vue";
export default {
  components: {
    ControlPanel,
  },

  data() {
    return {
      currentSpeed: 1,
      timeSpent: 1,
      distance: 1,
      fuelLeft: 1,
      statusItems: [
        { title: "Текущая скорость корабля, м/с", val: this.currentSpeed },
        { title: "Прошло времени, c", val: this.currentSpeed },
        { title: "Расстояние, м", val: this.distance },
        { title: "Осталось топлива, кг", val: this.fuelLeft },
      ],
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
};
</script>

<style>
</style>