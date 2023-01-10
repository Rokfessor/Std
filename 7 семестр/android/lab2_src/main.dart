import 'dart:developer';
import 'package:flutter/cupertino.dart';
import 'package:appinio_swiper/appinio_swiper.dart';
import 'package:flutter/material.dart';
import 'example_buttons.dart';
import 'example_candidate_model.dart';
import 'example_card.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({
    Key? key,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return const CupertinoApp(
      debugShowCheckedModeBanner: false,
      home: QuizWidget(),
    );
  }
}

class QuizWidget extends StatefulWidget {
  const QuizWidget({
    Key? key,
  }) : super(key: key);

  @override
  State<QuizWidget> createState() => QuizState();
}

class QuizState extends State<QuizWidget> {
  final AppinioSwiperController controller = AppinioSwiperController();

  List<QuestionCard> cards = [];
  List<QuestionCard> cards_for_widget = [];
  int rightAnswers = 0;

  @override
  void initState() {
    cards = _generateQuestionModels();
    cards_for_widget = _generateQuestionModels();
    super.initState();
  }

  List<QuestionCard> _generateQuestionModels() {
    List<QuestionCard> res = [];
    int i = 0;
    for (QuestionModel candidate in questions) {
      res.add(QuestionCard(question: candidate, onAnswer: _onAnswer, id: i));
      ++i;
    }
    return res;
  }

  List<QuestionCard> _reGenerateQuestionModels() {
    List<QuestionCard> res = [];
    int i = 0;
    for (QuestionModel candidate in questions) {
      for (QuestionCard card in cards_for_widget) {
        if (card.question.guid == candidate.guid) {
          res.add(
              QuestionCard(question: candidate, onAnswer: _onAnswer, id: i));
          break;
        }
      }
      ++i;
    }
    return res;
  }

  void _onAnswer(int id, bool isRight) {
    setState(() {
      questions[id].answerStatus = isRight ? "T" : "F";
      if (isRight) {
        rightAnswers++;
      }
      cards = _generateQuestionModels();
      cards_for_widget = _reGenerateQuestionModels();
    });
  }

  @override
  Widget build(BuildContext context) {
    return CupertinoPageScaffold(
      backgroundColor: Colors.white,
      child: Column(
        children: [
          const SizedBox(
            height: 50,
          ),
          CupertinoPageScaffold(
            backgroundColor: Colors.white,
            child: Row(
                children: List<Container>.generate(
                    cards.length,
                    (index) => Container(
                        width: MediaQuery.of(context).size.width *
                                (1 / cards.length) -
                            10,
                        height: 10,
                        margin: const EdgeInsets.only(left: 5, right: 5),
                        color: cards[index].question.answerStatus == "N"
                            ? Colors.black26
                            : cards[index].question.answerStatus == "T"
                                ? Colors.green
                                : Colors.red)).reversed.toList()),
          ),
          SizedBox(
            height: MediaQuery.of(context).size.height * 0.85,
            child: AppinioSwiper(
              unlimitedUnswipe: true,
              controller: controller,
              unswipe: _unswipe,
              cards: cards_for_widget,
              onSwipe: _swipe,
              padding: const EdgeInsets.only(
                left: 10,
                right: 10,
                top: 10,
                bottom: 40,
              ),
            ),
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [unswipeButton(controller)],
          )
        ],
      ),
    );
  }

  void _swipe(int index, AppinioSwiperDirection direction) {
    log("the card was swiped to the: " + direction.name);
  }

  void _unswipe(bool unswiped) {
    if (unswiped) {
      log("SUCCESS: card was unswiped");
    } else {
      log("FAIL: no card left to unswipe");
    }
  }
}
