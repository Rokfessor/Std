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
          SizedBox(
            height: 10,
          ),
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
                child: Image.network(question.imgUrl)),
          ),
          Container(
            padding: const EdgeInsets.all(15),
            decoration: const BoxDecoration(
              borderRadius: BorderRadius.only(
                bottomLeft: Radius.circular(10),
                bottomRight: Radius.circular(10),
              ),
            ),
            child: Center(
              child:
                Column(
                  mainAxisSize: MainAxisSize.min,
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Flexible(
                      fit: FlexFit.loose,
                      child: Text(
                        overflow: TextOverflow.ellipsis,
                        maxLines: 5,
                        question.questionText,
                        style: const TextStyle(
                          fontWeight: FontWeight.bold,
                          fontSize: 14,
                        ),
                      ),
                    ),
                    Center(
                      child: _generateBtns(question),
                    )
                  ],
                ),
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
        _createBtn(answered, question.answers[0]),
        _createBtn(answered, question.answers[1]),
        _createBtn(answered, question.answers[2]),
        _createBtn(answered, question.answers[3]),
      ],
    );
  }

  ElevatedButton _createBtn(bool answered, Answer answer) {
    if (answered) {
      return ElevatedButton(
          onPressed: () {},
          style: answer.isRight && answer.isSelected
              ? ElevatedButton.styleFrom(backgroundColor: Colors.green)
              : !answer.isRight && answer.isSelected
                  ? ElevatedButton.styleFrom(backgroundColor: Colors.red)
                  : ElevatedButton.styleFrom(backgroundColor: Colors.black26),
          child: Flexible(
            child: Text(answer.text,
                softWrap: true, overflow: TextOverflow.ellipsis),
          ));
    }

    return ElevatedButton(
        onPressed: () => _onAnswer(answer), child: Text(answer.text));
  }

  void _onAnswer(Answer answer) {
    answer.isSelected = true;
    onAnswer(id, answer.isRight);
  }
}
