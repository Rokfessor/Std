package Task3.T2;

public class Main {
    public static void main(String[] args) {
        try {
            ThreadPrinter.printByThreads(5, 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
