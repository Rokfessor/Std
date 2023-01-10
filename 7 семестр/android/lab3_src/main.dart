import 'package:flutter/material.dart';
import 'package:lab3/database.dart';
import 'package:lab3/transaction.dart';

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
      home: const MyHomePage(title: 'Transaction manager'),
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
  List<Widget> items = [];
  int id = 0;
  TextEditingController dateController = TextEditingController();
  TextEditingController amountController = TextEditingController();
  TextEditingController descriptionController = TextEditingController();

  @override
  void initState() {
    dateController.text = "";
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    _createList();
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
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
    showModalBottomSheet(
        context: context,
        isScrollControlled: true,
        builder: (BuildContext context) {
          return Padding(
              padding: MediaQuery.of(context).viewInsets,
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
                              lastDate: DateTime(2101));
                          if (pickedDate != null) {
                            setState(() {
                              dateController.text =
                                  pickedDate.toIso8601String().split("T")[0];
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
    List<Widget> result = [];
    dbHelper.queryAllRows().then((value) {
      value.forEach((element) {
        result.add(Transaction(
            id: element["_id"],
            amount: element["amount"],
            date: element["date"],
            description: element["description"],
            onDelete: _onDeleteItem));
      });

      setState(() {
        items = result;
      });
    });

    dbHelper.queryRowCount().then((value) => id = value + 1);
  }

  void _onDeleteItem(int id) {
    dbHelper.delete(id);
  }
}
