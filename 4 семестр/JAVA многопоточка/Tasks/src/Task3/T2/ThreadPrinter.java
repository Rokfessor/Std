package Task3.T2;

import java.io.File;
import java.util.ArrayList;

public class ThreadPrinter {
    public static void printByThreads(int threadCount, int messageCount) throws InterruptedException {
        ArrayList<MyThread> threads = new ArrayList<>();
        File file = new File("C:\\Users\\Oladushek\\Desktop\\Std\\JAVA многопоточка\\Tasks\\resources\\result3_2.txt");

        for (int i = 0; i < threadCount; i++) {
            threads.add(new MyThread(file, messageCount, i+"", threadCount));
        }

        for (MyThread thread : threads){
            thread.start();
        }

        for (MyThread thread : threads){
            thread.join();
        }
    }
}
