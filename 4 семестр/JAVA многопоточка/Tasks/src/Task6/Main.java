package Task6;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        Timer.startTime = System.currentTimeMillis();
        Library library= new Library();
        library.start();

        while (true) {
            if (Library.isOpen) {
                Reader reader = new Reader("Читатель " + i, library);
                reader.start();
                ++i;
            }
            Thread.sleep(Timer.calcOtnMinutes((Math.random() * 100) + 20));
        }
    }
}
