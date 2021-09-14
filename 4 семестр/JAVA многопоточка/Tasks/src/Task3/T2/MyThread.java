package Task3.T2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MyThread extends Thread {
    private final int messageCount;
    private int mc = 0;
    private static int threadCount;
    private final int id;
    private final File file;
    public static int writeThreadId = 0;

    static synchronized void incWrThId() {
        writeThreadId = (writeThreadId + 1) % threadCount;
    }

    static synchronized void resetWrThId() {
        writeThreadId = 0;
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
        for (int i = 0; i < messageCount * (threadCount - id); i++) {
            System.err.println(id + " " + i + " " + messageCount * (threadCount - id));
            while (writeThreadId != id) {
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            StringBuilder sb;
            if (id == 0 && mc != 0)
                sb = new StringBuilder("\n");
            else
                sb = new StringBuilder();

            if (mc < messageCount) {
                ++mc;
                for (int j = 0; j < mc; j++) {
                    sb.append(getName());
                }
                try(FileWriter fw = new FileWriter(file, true)){
                    fw.write(sb.toString());
                    fw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resetWrThId();
            } else {
                for (int j = 0; j < mc; j++) {
                    sb.append(getName());
                }
                try(FileWriter fw = new FileWriter(file, true)){
                    fw.write(sb.toString());
                    fw.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                incWrThId();
            }
        }
    }
}

