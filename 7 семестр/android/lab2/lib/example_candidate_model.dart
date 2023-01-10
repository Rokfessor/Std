import 'package:flutter/cupertino.dart';

class QuestionModel {
  String questionText;
  List<Answer> answers;
  String guid = UniqueKey().toString();
  String answerStatus = "N";
  String imgUrl;

  QuestionModel({
    required this.questionText,
    required this.answers,
    required this.imgUrl,
  });
}

class Answer {
  String text;
  bool isRight;
  bool isSelected = false;

  Answer({required this.text, this.isRight = false});
}

List<QuestionModel> questions = [
  QuestionModel(
      questionText:
          "Ну, и последний вопрос. Это рисунок режиссера \nк одному из лучших фильмов 2019 года. \nЧто за фильм?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/1653755/2a0000016fe7ef4e992b1398cebc4a6d9e21/500x280",
      answers: [
        Answer(text: "Ирландец"),
        Answer(text: "Джокер"),
        Answer(text: "Однажды в… Голливуде"),
        Answer(text: "Достать ножи")
      ]),
  QuestionModel(
      questionText:
          "Угадаете, какой режиссер сделал этот рисунок и к какому фильму?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/1523358/2a0000016fe7d84a6815e0b52bfb623bc983/500x280",
      answers: [
        Answer(text: "Акира Куросава для «Ран»", isRight: true),
        Answer(text: "Джан Имоу для «Дома летающих кинжалов»"),
        Answer(text: "Энг Ли для «Крадущегося тигра, затаившегося дракона»"),
        Answer(text: "Хаяо Миядзаки для «Ходячего замка» ")
      ]),
  QuestionModel(
      questionText: "Что-то знакомое… Или нет?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/371804/2a0000016fe7dd7b863bb893b1d9559d17c1/500x280",
      answers: [
        Answer(text: "Сияние"),
        Answer(text: "Бегущий по лезвию"),
        Answer(text: "Начало", isRight: true),
        Answer(text: "Твин Пикс")
      ]),
  QuestionModel(
      questionText:
          "На раскадровке мы видим главного героя фильма — головореза с дурацкой прической. Откуда он?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/371804/2a0000016fe83e9d34b05731b4800e3c26db/500x280",
      answers: [
        Answer(text: "Старикам тут не место", isRight: true),
        Answer(text: "Семь психопатов"),
        Answer(text: "Бешеные псы"),
        Answer(text: "Большой куш")
      ]),
  QuestionModel(
      questionText: "Из какого фильма этот глаз?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/1540808/2a0000016fe7e4bd8a0ba3b7d5e8e2f47dec/500x280",
      answers: [
        Answer(text: "Маяк"),
        Answer(text: "Оно 2"),
        Answer(text: "Мертвые не умирают"),
        Answer(text: "Мы")
      ]),
  QuestionModel(
      questionText:
          "Эту раскадровку рисовал не режиссер фильма, но она очень близка к получившейся сцене. Это...",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/403930/2a0000016fe7e66c2fbebdd5fd6c1cbe210d/500x280",
      answers: [
        Answer(text: "Запах напалма по утрам", isRight: true),
        Answer(text: "Born to Kill"),
        Answer(text: "Спасение одного там рядового"),
        Answer(text: "Бонд, Джеймс Бонд")
      ]),
  QuestionModel(
      questionText:
          "Даем подсказку: это раскадровка для фильма, который всегда находится в пятерке лидеров в топ-250 КиноПоиска.",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/1534095/2a0000016fe7eab178f0854ad7242f99540a/500x280",
      answers: [
        Answer(text: "Побег из Шоушенка"),
        Answer(text: "Зеленая миля"),
        Answer(text: "Форрест Гамп", isRight: true),
        Answer(text: "1+1")
      ]),
  QuestionModel(
      questionText:
          "Не можем не спросить про сериалы. Героиню, правда, узнать трудновато, но мы уверены, вы справитесь.",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/1523358/2a0000016fe7ed96f305abb216332034bd04/500x280",
      answers: [
        Answer(text: "Мир Дикого Запада"),
        Answer(text: "Игра престолов", isRight: true),
        Answer(text: "Ведьмак"),
        Answer(text: "Ходячие мертвецы")
      ]),
  QuestionModel(
      questionText: "Вернемся к вопросам полегче. Это из какого «Терминатора»?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/1653755/2a0000016fe7d6c89af35b041766fa27ce65/500x280",
      answers: [
        Answer(text: "Терминатор", isRight: true),
        Answer(text: "Терминатор 2: Судный день"),
        Answer(text: "Терминатор 3: Восстание машин"),
        Answer(text: "Терминатор: Темные судьбы")
      ]),
  QuestionModel(
      questionText:
          "Теперь вопрос для очень внимательных зрителей. Где есть похожий кадр?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/403930/2a0000016fe7d42b9441d8a7522c0db9f89c/500x280",
      answers: [
        Answer(text: "Рок-волна"),
        Answer(text: "Брат"),
        Answer(text: "Одержимость"),
        Answer(text: "Стражи галактики", isRight: true)
      ]),
  QuestionModel(
      questionText:
          "А эту грустную девушку в цветах нарисовали для какого фильма?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/1676369/2a0000016fe7d23d88f906908ce17b71c5f3/500x280",
      answers: [
        Answer(text: "Плетеный человек"),
        Answer(text: "Солнцестояние", isRight: true),
        Answer(text: "Маргаритки"),
        Answer(text: "Таинственный лес")
      ]),
  QuestionModel(
      questionText:
          "Вы можете удивиться, но это раскадровки, сделанные одним из главных режиссеров современности. Узнаете фильм?",
      imgUrl:
          "https://avatars.mds.yandex.net/get-quiz/371804/2a0000016fe7ca76b741021214806dcf27f5/500x280",
      answers: [
        Answer(text: "Криминальное чтиво"),
        Answer(text: "Крестный отец 2"),
        Answer(text: "Таксист", isRight: true),
        Answer(text: "Бойцовский клуб")
      ]),
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
