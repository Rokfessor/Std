❗❗Внимание❗❗  Вижу ты зашел поживиться бесплатными работами по проге. Прочитай это, только полностью!! А взамен ты получишь супер полезную инфу и неиного смешнявочек.  
  
И так, т.к. я ненавижу Swing, то отныне все графические проги будут писаться на JavaFX. Поэтому если ты сильный и смелый, то вот быстрый гайд по установке
1) Идем [cюда](https://gluonhq.com/products/javafx/) и качаем архив JavaFX Windows x64 SDK из раздела LatestRelease! После, выковыриваем его в какую-нибудь папку.
2) Значит потом идем в File->New project и выбираем вот это
![Опа, походу картинка не загрузилась. Удачи бро!](https://sun9-66.userapi.com/impg/6bEz1OHn2TL7w5E-RkJD_fIv83T-B2RjIoKMGg/JIXuRIdNG5g.jpg?size=708x525&quality=96&sign=592d1f2eed5e0e2eb5f5ae30334995d4&type=album)  
Если такой вкладочки нет, то заходим в File->Settings->Plugins и устанавливаем плагин JavaFX (или, что скорее всего, он уже установлен, и, всё там же, только в разделе Installed - нужно нажать галочку активации). ![Опа, походу картинка не загрузилась. Удачи бро!](https://sun9-8.userapi.com/impg/0J9nrPYCqunl4geJXjiFIaKqYx9Y2mOjL8xFSg/lWo2wSh3vuI.jpg?size=1281x703&quality=96&sign=1756823aec92466dfa110cebb7aaaf20&type=album) Продолжаем создавать проект и все в таком духе (кстати будет лучше, если проект будет собран на более-менее свежей версии SDK Jav'ы. Чтобы узнать как это делается см. 8 пункт этого гайда. У меня, напрмер, стоит 15 версия и мне крутямбова😎).  
3) Затем нам нужно подрубить fx к проекту. Делается это следующим способом:  
 3.1 Идем в File->Project Structure->Libraries  
 3.2 Нажимаем плюс, который слева и выбираем пунктик Java ![Опа, походу картинка не загрузилась. Удачи бро](https://sun9-53.userapi.com/impg/NtBM0f_YLhOBDxmb2SZxf6sIy6tqxO4vY0Vqng/4cEYkPnhV7I.jpg?size=958x608&quality=96&sign=a6c8532840b96a8e9741dd4dfb9e560a&type=album)
 3.3 И выбираем папочку lib в каталоге fx'a ![Опа, походу картинка не загрузилась. Удачи бро](https://sun9-3.userapi.com/impg/5vmrN64xd4f0edtfSWMdzwv30JcqLf485rEP-A/Sbg516zdENU.jpg?size=1123x876&quality=96&sign=249a83b7ea22f7c6648f8eb2aa5c7b07&type=album)
 3.4 Тыкаем ок ок ок
4) Шок контент, уже 4 пункт. Осталось немножко!  
  Идем в Run->Edit Configurations, в Modify options тыкаем Add VM options ![Опа, походу картинка не загрузилась. Удачи бро](https://sun9-38.userapi.com/impg/GOapljRU5Ti-o68fPi3hfYmXwJQbeb2Oac_aOA/xWRru3A8DW0.jpg?size=1395x848&quality=96&sign=5b33895468006c68c94f1e6292525809&type=album)
  После чего в появившейся строчке вставляем вот это --module-path абсолютный/путь/до/javafx-sdk-16/lib --add-modules=javafx.controls,javafx.fxml Применяем настроечки.
5) Иии вау, все работает. Запускаем наш тестовый код, который сгенерировала идея и радуемся пустому окошку. А если не работает, тооо... ¯\\_(ツ)_/¯ Посмотри [здесь](https://www.jetbrains.com/help/idea/javafx.html), [здесь](https://openjfx.io/openjfx-docs/#install-javafx) или [здесь](https://metanit.com/java/javafx/1.8.php), но сильно понятнее не станет  
  
⚠⚠Пункты для самых крутых ребяток⚠⚠  
  
6) Утановка scene builder'a. Идем [сюда](https://gluonhq.com/products/scene-builder/) и качаем его. Далее устанавливаем и заходим в идею File->Settngs Languages & Frameworks->JavaFx и в текстовое поле суем путь до exe'шника scene buildr'a ![Опа, походу картинка не загрузилась. Удачи бро](https://sun9-37.userapi.com/impg/MMjV1hzWSSfFh_eJgAYH-pRYQiOwhJdFE0-cog/NLvc0CAzpbw.jpg?size=1129x881&quality=96&sign=e8a6714c11883b8a0d8a923f1e6121de&type=album)
  После этого можно будет нажать на fxml файлик пкм'ом, выбрать Open In Scenebuilder и редактировать гуишку в редакторе гуишек ![Тут был прикольчик, но он не загрузился((](https://i0.wp.com/lacriaturacreativa.com/wp-content/uploads/2017/10/cursos-disenos-grafico.gif)
7) Можно оформить переменную, которая будет отвечать за путь до либы. ![https://metanit.com/java/javafx/1.8.php в самом конце](https://sun9-28.userapi.com/impg/swM494Ssv8aBcfpN2jpRl_2qwBiN7X-SrF68Nw/8AwVyTRmgdw.jpg?size=1123x870&quality=96&sign=134319df6be5a1d4fca61f841f101003&type=album) тогда в VM options мы сможем делать так   
--module-path=${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml , а не так  
--module-path абсолютный/путь/до/javafx-sdk-16/lib --add-modules=javafx.controls,javafx.fxml  (что может быть удобным, если ты будешь брать мои работы, т.к. я использую именно этот путь, иначе придется всегда указывать свой, а это гемор). 
8) Никак. 

❔❔Немного ответов на вопросы❔❔  
-Чёта все равно не работает!  
-Посмотри как поставить [JAVA_HOME](https://java-course.ru/begin/install-jdk/), мб поможет  
  
-Всё, теперь я могу навсегда забыть про установку и создавать свои прекрасные приложения?  
-Нет, каждый раз, когда ты создаешь новый проект, тебе нужно будет всегда выполнять 3 и 4 пункт при создании нового проекта!  
  
-Ну хоть твои работы то я смогу без проблем скачать и запустить. Они же настроены!?  
-Всё еще нет, нужно так же выполнять пункт 3 и 4. Однако 4 можно и пропустить, если ты сделаешь 7 (но осторожно, он для крутых ребяток)  

-А чо на паре прога не заработала?  
-А потому что когда ты откроешь работу на вузовском пк, то настройки у тебя будут старые и все еще придется выполнить 3 и 4 пункт. Так что не забудь кинуть на флешечку либу!  
  
-Ну зачем, зачееем ты начал писать на JavaFX?? Я не знаю ее, я бля со swing'ом то еле разобрался, а тут fxml файлы какие-то, чета устанавливать нужно и вообще гемор какой-то!  
-Друг мой, JavaFX - это венец GUI строения в Java. Как только ты вкусишь прелести местного подхода к проектированию - Swing тебе покажется ничтожной помойкой, на которой адекватно кодит только Владос и JetBrains, а остальные ее юзают - потому что нет альтернатив (есть! просто Swing идет из коробки и его не нужно лишний раз ставить). Установить ее придется только 1 раз, дальше сделать 2(1) лишних движения и все будет работать. С FXML практически не придется работать, если поставишь SceneBuiler. А куча плюх, которые дает fx невероятно упрощают разработку (Я не получил ни копейки за то, что только что облизал fx). Так что вот [ссылка](https://metanit.com/java/javafx/1.1.php) и не ной!

❗❗Какие работы будут написаны на FX'e❗❗
- Алгоритмы начиная с доминошек  
- Автоматы начиная с шариков 
- ОО ПО начиная с гладиаторов (Ну, собственно, ими и заканчивая)

