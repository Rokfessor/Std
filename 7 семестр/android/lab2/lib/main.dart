import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:lab2/quiz_page.dart';
import 'package:pie_chart/pie_chart.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return CupertinoApp(home: HomeScreen());
  }
}

class HomeScreen extends StatelessWidget {
  bool ended = false;
  int right = 0;
  int total = 0;

  HomeScreen({super.key, this.ended = false, this.right = 0, this.total = 0});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Image.network(
              "https://bcoensingen.ch/wp-content/uploads/2020/03/quiz-3.png"),
          ElevatedButton(
              onPressed: () {
                Navigator.of(context).push(
                    MaterialPageRoute(builder: (context) => new QuizWidget()));
              },
              child: const Text("Начать квиз!", style: TextStyle(
                fontSize: 20
              ))),
          SizedBox(height: 20),
          _chart()
        ],
      ),
    );
  }

  TextEditingController textController = TextEditingController();

  Widget _chart() {
    if (!ended) {
      return Container(
        child: TextField(
          text
        ),
      );
    }

    final dataMap = <String, double>{
      "Right answers": right.toDouble(),
    };

    return Column(
      children: [
        PieChart(
          dataMap: dataMap,
          chartType: ChartType.disc,
          chartRadius: 200,
          legendOptions: const LegendOptions(showLegends: false),
          baseChartColor: Colors.red,
          colorList: const <Color>[
            Colors.green,
          ],
          chartValuesOptions: const ChartValuesOptions(
            showChartValuesInPercentage: true,
          ),
          totalValue: total.toDouble(),
        ),
        const Text("Результаты квиза", style: TextStyle(
            fontSize: 20,
            decorationStyle: TextDecorationStyle.double
        )),
      ],
    );
  }
}
