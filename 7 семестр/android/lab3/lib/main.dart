import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:lab3/database.dart';
import 'package:lab3/transaction.dart';
import 'package:syncfusion_flutter_charts/charts.dart';

final dbHelper = DatabaseHelper();

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await dbHelper.init();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Transactions'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  List<Transaction> items = [];
  List<Data> graphData = [];
  int id = 0;
  int totalAmount = 0;
  TextEditingController dateController = TextEditingController();
  TextEditingController amountController = TextEditingController();
  TextEditingController descriptionController = TextEditingController();

  @override
  void initState() {
    dateController.text = "";
    dbHelper.lastRowId().then((value) => id = value + 1);
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    _createList();
    return Scaffold(
      appBar: AppBar(
        title: Text("${widget.title}  ($totalAmount\$)"),
        actions: [Padding(
            padding: const EdgeInsets.only(right: 20.0),
            child: GestureDetector(
              onTap: () {
                showModalBottomSheet(
                    context: context,
                    isScrollControlled: true,
                    builder: (BuildContext context) {
                      return Padding(padding: MediaQuery
                          .of(context)
                          .viewInsets,
                          child: SizedBox(
                            child: SfCartesianChart(
                                primaryXAxis: CategoryAxis(),
                                title: ChartTitle(text: 'Transactions'),
                                series: <LineSeries<Data, String>>[
                                  LineSeries<Data, String>(
                                      dataSource: graphData,
                                      xValueMapper: (Data t, _) => t.date,
                                      yValueMapper: (Data t, _) => t.amount,
                                      // Enable data label
                                      dataLabelSettings: DataLabelSettings(isVisible: true)
                                  )
                                ]
                            ),
                          )
                      );
                    });
              },
              child: const Icon(
                Icons.show_chart,
                size: 26.0,
              ),
            )
        )
        ],
      ),
      body: Center(
        child: ListView(
          children: items,
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _addItem,
        tooltip: 'Add transaction',
        child: const Icon(Icons.add),
      ),
    );
  }

  void _addItem() {
    descriptionController.text = "";
    amountController.text = "";
    dateController.text = DateTime.now().toIso8601String().split("T")[0];
    showModalBottomSheet(
        context: context,
        isScrollControlled: true,
        builder: (BuildContext context) {
          return Padding(
              padding: MediaQuery
                  .of(context)
                  .viewInsets,
              child: SizedBox(
                  height: 250,
                  child: Center(
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        mainAxisSize: MainAxisSize.min,
                        children: <Widget>[
                          TextFormField(
                            controller: descriptionController,
                            decoration: const InputDecoration(
                              border: UnderlineInputBorder(),
                              icon: Icon(Icons.description),
                              labelText: 'Enter description',
                            ),
                          ),
                          TextFormField(
                            controller: amountController,
                            decoration: const InputDecoration(
                              border: UnderlineInputBorder(),
                              icon: Icon(Icons.monetization_on_outlined),
                              labelText: 'Enter amount',
                            ),
                          ),
                          TextField(
                            controller: dateController,
                            decoration: const InputDecoration(
                                icon: Icon(Icons.calendar_today),
                                labelText: "Enter Date"),
                            readOnly: true,
                            onTap: () async {
                              DateTime? pickedDate = await showDatePicker(
                                  context: context,
                                  initialDate: DateTime.now(),
                                  firstDate: DateTime(2000),
                                  lastDate: DateTime.now());
                              if (pickedDate != null) {
                                setState(() {
                                  dateController.text = pickedDate.toIso8601String().split("T")[0];
                                });
                              }
                            },
                          ),
                          ElevatedButton(
                            child: const Text('Add transaction'),
                            onPressed: () {
                              Transaction t = Transaction(
                                  id: id,
                                  description: descriptionController.text,
                                  amount: int.parse(amountController.text),
                                  date: dateController.text,
                                  onDelete: _onDeleteItem);
                              ++id;
                              dbHelper.insert(t.toMap());
                              setState(() {
                                items.add(t);
                              });
                              Navigator.pop(context);
                            },
                          ),
                        ],
                      ))));
        });
  }

  void _createList() {
    List<Transaction> result = [];
    dbHelper.queryAllRows().then((value) {
      value.forEach((element) {
        result.add(Transaction(
            id: element["_id"],
            amount: element["amount"],
            date: element["date"],
            description: element["description"],
            onDelete: _onDeleteItem));
      });

      int total = 0;
      graphData.clear();
      result.forEach((element) {
        total += element.amount;
        bool f = true;
        for (Data d in graphData) {
          if (element.date.compareTo(d.date) == 0) {
            d.amount += element.amount;
            f = false;
            break;
          }
        }

        if (f) {
          graphData.add(Data(date: element.date, amount: element.amount));
        }
      });

      setState(() {
        items = result;
        totalAmount = total;
      });
    });
  }

  void _onDeleteItem(int id) {
    dbHelper.delete(id);
  }
}
