package Task4;

public class Main {
    public static void main(String[] args) {
        Owner owner = new Owner();
        //Capacity - Число стендов | t - Время обслуживания 1 машины | h - Общее время работы |
        //newCar - Время приезда новой машины | w - время ожидания машины | otnW - относительное значение
        owner.start(3, 20, 720, 4,5, 720);
    }
}
