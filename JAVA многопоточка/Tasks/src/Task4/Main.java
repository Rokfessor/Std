package Task4;

public class Main {
    public static void main(String[] args) {
        //Capacity - Число стендов | t - Время обслуживания 1 машины | h - Общее время работы | cars - Кол-во машин | w - время ожидания машины
        int a = CarsProvider.provide(3, 20000L, 60000L, 20, 20000);
        System.err.println("=========\nВсего осмотрено " + a + " машин\n==========\n");
    }
}
