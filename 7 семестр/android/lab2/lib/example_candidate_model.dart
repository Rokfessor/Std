import 'package:flutter/cupertino.dart';

class QuestionModel {
  String questionText;
  List<Answer> answers;
  String answerStatus = "N";

  QuestionModel({
    required this.questionText,
    required this.answers
  });
}

class Answer {
  String text;
  bool isRight;

  Answer({
    required this.text,
    required this.isRight
  });
}

List<QuestionModel> questions = [
  QuestionModel(
      questionText: "Question 4",
      answers: [
        Answer(text: "Answer 1", isRight: true),
        Answer(text: "Answer 2", isRight: false),
        Answer(text: "Answer 3", isRight: false),
        Answer(text: "Answer 4", isRight: false)
      ]
  ),
  QuestionModel(
      questionText: "Question 3",
      answers: [
        Answer(text: "Answer 1", isRight: true),
        Answer(text: "Answer 2", isRight: false),
        Answer(text: "Answer 3", isRight: false),
        Answer(text: "Answer 4", isRight: false)
      ]
  ),
  QuestionModel(
      questionText: "Question 2",
      answers: [
        Answer(text: "Answer 1", isRight: true),
        Answer(text: "Answer 2", isRight: false),
        Answer(text: "Answer 3", isRight: false),
        Answer(text: "Answer 4", isRight: false)
      ]
  ),
  QuestionModel(
      questionText: "Question 1",
      answers: [
        Answer(text: "Answer 1", isRight: true),
        Answer(text: "Answer 2", isRight: false),
        Answer(text: "Answer 3", isRight: false),
        Answer(text: "Answer 4", isRight: false)
      ]
  ),
];

const LinearGradient gradientRed = LinearGradient(
  begin: Alignment.topCenter,
  end: Alignment.bottomCenter,
  colors: [
    Color(0xFFFF3868),
    Color(0xFFFFB49A),
  ],
);

const LinearGradient gradientPurple = LinearGradient(
  begin: Alignment.topCenter,
  end: Alignment.bottomCenter,
  colors: [
    Color(0xFF736EFE),
    Color(0xFF62E4EC),
  ],
);

const LinearGradient gradientBlue = LinearGradient(
  begin: Alignment.topCenter,
  end: Alignment.bottomCenter,
  colors: [
    Color(0xFF0BA4E0),
    Color(0xFFA9E4BD),
  ],
);

const LinearGradient gradientPink = LinearGradient(
  begin: Alignment.topCenter,
  end: Alignment.bottomCenter,
  colors: [
    Color(0xFFFF6864),
    Color(0xFFFFB92F),
  ],
);

const LinearGradient kNewFeedCardColorsIdentityGradient = LinearGradient(
  begin: Alignment.topCenter,
  end: Alignment.bottomCenter,
  colors: [
    Color(0xFF7960F1),
    Color(0xFFE1A5C9),
  ],
);