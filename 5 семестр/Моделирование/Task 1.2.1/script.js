let lineChart;
let doughnutChart;
let lineChartSettings = {
  type: 'bar',
  data: {
    labels: ['15/16', '16/17', '17/18', '18/19', '19/20', '20/21'],
    datasets: [{
      data: [12, 19, 3, 5, 2, 3],
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)'
      ],
      borderColor: [
        'rgba(255, 99, 132, 1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)'
      ],
      borderWidth: 1
    }]
  },
  options: {
    responsive: true,
    maintainAspectRatio: false,
    scales: {
      yAxes: [{
        ticks: {
          beginAtZero: true
        }
      }]
    }
  }
};

let doughnutChartSettings = {
  type: 'doughnut',
  data: {
    labels: ['АВб-19-1', 'АВб-19-2', 'ССЗ-19', 'УЛВ-18', 'ВЗУ-20', 'УКР-18'],
    datasets: [{
      data: [300, 50, 100, 122, 167, 90],
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(54, 162, 235)',
        'rgb(255, 206, 86)',
        'rgb(75, 192, 192)',
        'rgb(153, 102, 255)',
        'rgb(255, 159, 64)'
      ],
      hoverOffset: 15
    }]
  }
};

function init() {
  lineChart = new Chart(document.getElementById("lineChart").getContext('2d'), lineChartSettings);
  doughnutChart = new Chart(document.getElementById("doughnutChart").getContext('2d'), doughnutChartSettings);
}

function redrawChart() {
  let data = Array.from(Array(6)).map(() => {
    return Math.round(Math.random() * 50 + 100);
  });

  //console.log(data);
  lineChart.data.datasets[0].data = data;
  doughnutChart.data.datasets[0].data = data;
  lineChart.update();
  doughnutChart.update();
}

init();