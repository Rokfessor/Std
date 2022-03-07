public class Main {
	public static void Main(String[] args) {
		List<Paint> list = Arrays.asList( //;; Создание списка объектов
			new Paint("Green paint",  "Green",  5000, 12), 
			new Paint("Red paint",    "Red",    5001, 13),
			new Paint("Blue paint",   "Blue",   5002, 18),
			new Paint("Black paint",  "Black",  5003, 22),
			new Paint("White paint",  "White",  5004, 18),
			new Paint("Yellow paint", "Yellow", 5005, 9)
		);
		list.stream().forEach(p -> p.toString()); // Вывод элементов списка
		Paint.otbor(list).forEach(p -> p.toString()); // Вывод отобранных элментов списка
	}
}

public class Paint { // Объяление класса
	private String name; // Поля класса
	private String color; 
	private int cost;
	private int consumption;

	public Paint(String name, String color, int cost, int consumption) { // Конструктор
		this.name = name;
		this.color = color;
		this.cost = cost;
		this.consumption = consumption;
	}

	public static List<Paint> otbor(List<Paint> list, int cons) { //Метод отбора красок по расходу за стекший месяц
		return list.stream().filter(p -> p.consumption > cons).collect(Collectors.toList()); //Потоковая фильтрация исходного списка красок
	}

	//Гетеры
	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public int getCost() {
		return cost;
	}

	public int getConsumption() {
		return consumption;
	}

	@Override
	public String toString() { // Метод вывода списка
		return "name = " + name + ", color=" + color + ", cost=" + cost + " consumption=" + consumption;
	}
}