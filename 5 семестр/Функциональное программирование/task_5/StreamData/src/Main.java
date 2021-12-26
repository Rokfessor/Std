import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> data = Loader.loadData("resources/data.csv");
        System.err.println("===Исходный список===");
        data.forEach(System.err::println);

        //======
        System.err.println();
        final String filterName = "Антон";
        List<Person> res1 = data.stream() //
                .filter(person ->
                        person.name.toLowerCase(Locale.ROOT).equals(filterName.toLowerCase(Locale.ROOT))) ////Метод фильтрации пр имени
                .peek(person -> person.age += Math.random() * 11) //Метод применения функции к каждому элементу потока
                .sorted(Comparator.comparingInt(value -> value.age)) //Метод сортировки по возрасту
                .collect(Collectors.toList());
        System.err.println("===Отсортированный по возрасту и отфильтрованный по имени \"" + filterName + "\" список===");
        res1.forEach(System.err::println);

        //======
        System.err.println();
        final int skipElems = 3;
        final int limitElems = 5;
        final String emailDomen = "yandex.ru";
        List<String> res2 = data.stream()
                .filter(person -> person.email.contains(emailDomen))
                .skip(skipElems)
                .limit(limitElems)
                .map(person -> person.email)
                .collect(Collectors.toList());
        System.err.println("===Список первых " + limitElems + " \"" + emailDomen + "\" почт (не включая первые " + skipElems + " элементов)===");
        res2.forEach(System.err::println);

        //======
        System.err.println();
        System.err.println("===Возрастные показатели===");
        System.err.println("Общий возраст пользователей: " + data.stream().mapToInt(value -> value.age).sum());
        System.err.println("Средний возраст пользователей: " + data.stream().mapToInt(value -> value.age).average().getAsDouble());
        System.err.println("Возраст старейшего пользователя: " + data.stream().mapToInt(value -> value.age).max().getAsInt());

        List<Person> repeatItems = new ArrayList<>();
        data.forEach(person -> {
            if (Math.random() < 0.1) {
                repeatItems.add(person);
                while (Math.random() * 11 < 1)
                    repeatItems.add(person);
            }
        });
        System.err.println("===Список с повторениями и без===");
        repeatItems.forEach(System.err::println);
        System.err.println();
        List<Person> noRepeatItems = repeatItems.stream().distinct().toList();
        noRepeatItems.forEach(System.err::println);
    }
}
