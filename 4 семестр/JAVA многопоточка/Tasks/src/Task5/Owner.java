package Task5;

public class Owner {
    public CallsProvider callsProvider;
    private final long mills = 60000;

    public void start(int capacity, long t, long h, double newClient, double w, double otnW) {
        callsProvider = new CallsProvider(this);
        new Thread(() -> {
            try {
                Thread.sleep((long) ((h / otnW) * mills));
                CallsProvider.stop = true;
                if (callsProvider.getState() == Thread.State.WAITING)
                    callsProvider.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //Capacity - Число стендов | t - Время обслуживания 1 машины | h - Общее время работы | w - время ожидания машины
        callsProvider.provide(capacity, t, w, newClient, otnW);
    }
}
