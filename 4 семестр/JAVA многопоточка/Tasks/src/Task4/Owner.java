package Task4;

public class Owner {
    public CarsProvider carsProvider;
    private final long mills = 60000;

    public void start(int capacity, long t, long h, long newCar, long w, double otnW) {
        carsProvider = new CarsProvider(this);
        new Thread(() -> {
            try {
                Thread.sleep((long) ((h / otnW) * mills));
                CarsProvider.stop = true;
                if (carsProvider.getState() == Thread.State.WAITING)
                    carsProvider.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //Capacity - Число стендов | t - Время обслуживания 1 машины | h - Общее время работы | w - время ожидания машины
        carsProvider.provide(capacity, t, w, newCar, otnW);
    }
}
