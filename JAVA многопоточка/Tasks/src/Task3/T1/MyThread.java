package Task3.T1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyThread extends Thread {
    private final int messageCount;
    private static int threadCount;
    private final int id;
    private final File file;
    public static int writeThreadId = 0;

    static synchronized void incWrThId() {
        writeThreadId = (writeThreadId + 1) % threadCount;
    }

    public MyThread(File file, int messageCount, String name, int threadCount) {
        super(name);
        MyThread.threadCount = threadCount;
        this.messageCount = messageCount;
        this.file = file;
        id = name.charAt(name.length() - 1) - 48;
    }

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                while (writeThreadId != id) {
                    sleep(1);
                }
                try (FileWriter fw = new FileWriter(file, true)) {
                    fw.write(getName() + "->" + i + "\n");
                    fw.flush();
                    incWrThId();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
