import 'dart:developer';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import 'example_candidate_model.dart';

class QuestionCard extends StatelessWidget {
  final QuestionModel question;
  final Function onAnswer;
  final int id;

  const QuestionCard(
      {Key? key,
      required this.question,
      required this.onAnswer,
      required this.id})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10),
        color: CupertinoColors.white,
        boxShadow: [
          BoxShadow(
            color: CupertinoColors.systemGrey.withOpacity(0.2),
            spreadRadius: 3,
            blurRadius: 7,
            offset: const Offset(0, 3),
          )
        ],
      ),
      alignment: Alignment.center,
      child: Column(
        children: [
          Flexible(
            child: Container(
              decoration: const BoxDecoration(
                gradient: LinearGradient(
                  begin: Alignment.topCenter,
                  end: Alignment.bottomCenter,
                  colors: [
                    Color(0xFFFF3868),
                    Color(0xFFFFB49A),
                  ],
                ),
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(10),
                  topRight: Radius.circular(10),
                ),
              ),
            ),
          ),
          Container(
            padding: const EdgeInsets.all(15),
            decoration: const BoxDecoration(
              borderRadius: BorderRadius.only(
                bottomLeft: Radius.circular(10),
                bottomRight: Radius.circular(10),
              ),
            ),
            child: Row(
              children: [
                Column(
                  mainAxisSize: MainAxisSize.min,
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      question.questionText,
                      style: const TextStyle(
                        color: Colors.black,
                        fontWeight: FontWeight.bold,
                        fontSize: 20,
                      ),
                    ),
                    const SizedBox(
                      height: 5,
                    ),
                    _generateBtns(question)
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Column _generateBtns(QuestionModel question) {
    bool answered = question.answerStatus != "N";
    return Column(
      children: [
        Row(
          children: [
            _createBtn(answered, question.answers[0]),
            _createBtn(answered, question.answers[1]),
          ],
        ),
        Row(
          children: [
            _createBtn(answered, question.answers[2]),
            _createBtn(answered, question.answers[3]),
          ],
        )
      ],
    );
  }

  ElevatedButton _createBtn(bool answered, Answer answer) {
    if (answered) {
      return ElevatedButton(
        onPressed: () {},
        style: answer.isRight
            ? ElevatedButton.styleFrom(backgroundColor: Colors.green)
            : ElevatedButton.styleFrom(backgroundColor: Colors.red),
        child: Text(answer.text),
      );
    }

    return ElevatedButton(
        onPressed: () => _onAnswer(answer), child: Text(answer.text));
  }

  void _onAnswer(Answer answer) {
    onAnswer(id, answer.isRight);
  }
}
