package org.example.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;

public class FileWatcher extends Thread {
    private final AtomicBoolean watch = new AtomicBoolean(true);
    private final Path path;
    private final Function<String, ?> onMessageReceive;

    public FileWatcher(Path path, Function<String, ?> onMessageReceive) {
        this.path = path;
        this.onMessageReceive = onMessageReceive;
    }

    public void stopWatching() {
        watch.set(false);
    }

    @Override
    public void run() {
        while (watch.get()) {
            try (FileInputStream reader = new FileInputStream(path.toString())) {
                while (reader.available() == 0 && watch.get()) ;
                if (!watch.get())
                    return;
                byte[] tmp = new byte[reader.available()];
                reader.read(tmp);
                onMessageReceive.apply(new String(tmp));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
