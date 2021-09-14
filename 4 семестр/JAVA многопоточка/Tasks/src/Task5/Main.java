package Task5;

public class Main {
    public static void main(String[] args) {
        Owner owner = new Owner();
        //Capacity - Число клиентов обслуживаемых одновременно| t - Время обслуживания 1 клиента | h - Общее время работы |
        //newCar - Время звонка нового клиента | w - время ожидания клиента | otnW - относительное значение времени
        owner.start(3, 3, 1440, 0.8,0.5, 720);
    }
}
