import java.util.*;

public class Main {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8); // Исходный список
		System.err.println(list); //Вывод исходного списка
		List<Object> res = conv(list); //Вычисления
		System.err.println(res); //Вывод результата
	}

	public static List<Object> conv(List<Integer> list) {
		List<Object> res = new ArrayList<>();
		if (list.size() != 1) { //Пока длина списка не равна 1
			res.add(conv(list.subList(0, list.size() - 1))); //Рекурсивный вызов ф-ции с подсписком 
		}
		res.add(list.get(list.size() - 1)); //Добавляем последний элемент в начало
		return res;
	}
}