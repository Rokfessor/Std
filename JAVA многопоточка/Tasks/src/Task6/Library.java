package Task6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ConcurrentHashMap;

public class Library extends Thread {
    private final long openTime = 8;
    private final long closeTime = 16;

    private long lastClose;
    private long lastOpen;

    public static boolean isOpen = true;
    public ConcurrentHashMap<String, Book> books = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Book> booksList = new ConcurrentHashMap<>();

    public Library() {
        initLib();
    }

    @Override
    public void run() {
        while (true) {
            try {
                isOpen = true;
                lastOpen = System.currentTimeMillis();
                System.err.println(Timer.getCurrentTime() + " ====Библиотека открылась====\n");
                Thread.sleep(Timer.calcOtnHours(openTime));

                isOpen = false;
                lastClose = System.currentTimeMillis();
                System.err.println(Timer.getCurrentTime() + " ====Библиотека закрылась====\n");
                Thread.sleep(Timer.calcOtnHours(closeTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public long timeUntilClose() {
        return Timer.calcOtnHours(openTime) - (System.currentTimeMillis() - lastOpen);
    }

    public long timeUntilOpen() {
        return Timer.calcOtnHours(closeTime) - (System.currentTimeMillis() - lastClose);
    }

    public void initLib() {
        try {
            String[] booksList = Files.readString(new File("C:\\Users\\Oladushek\\Desktop\\Std\\JAVA многопоточка\\Tasks\\resources\\Task6_books.txt").toPath()).split(System.lineSeparator());
            for (String book: booksList) {
                boolean mayTakeHome = Math.random() < 0.5;
                this.booksList.put(book, new Book(book, mayTakeHome));
                books.put(book, new Book(book, mayTakeHome));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
