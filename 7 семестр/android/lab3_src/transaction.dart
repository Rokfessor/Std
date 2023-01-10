import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class Transaction extends StatelessWidget {
  late int id;
  late String description;
  late String date;
  late int amount;
  late Function onDelete;

  Transaction({super.key,
    required this.id,
    required this.description,
    required this.amount,
    required this.date,
    required this.onDelete
  });

  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 5,
      margin: const EdgeInsets.symmetric(
        vertical: 8,
        horizontal: 5,
      ),
      child: ListTile(
        leading: CircleAvatar(
          radius: 30,
          child: Padding(
            padding: const EdgeInsets.all(6),
            child: FittedBox(
              child: Text('\$$amount'),
            ),
          ),
        ),
        title: Text(
          description,
        ),
        subtitle: Text(
          date,
        ),
        trailing: IconButton(
          icon: const Icon(Icons.delete),
          color: Theme.of(context).errorColor,
          onPressed: () => onDelete(id),
        ),
      ),
    );
  }


  Map<String, dynamic> toMap() {
    return {
      "_id" : id,
      "description": description,
      "amount": amount,
      "date": date
    };
  }
}