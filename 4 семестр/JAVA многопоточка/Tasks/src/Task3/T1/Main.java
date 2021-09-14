package Task3.T1;

public class Main {
    public static void main(String[] args) {
        try {
            ThreadPrinter.printByThreads(5, 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
