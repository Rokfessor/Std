package org.example.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Function;

public class Server {

    private final Path filePath;
    private final Function<String, ?> onMessageReceive;
    private FileWatcher fileWatcher;

    private String lockUserName;

    public Server(Path filePath, Function<String, ?> onMessageReceive) {
        this.filePath = filePath;
        this.onMessageReceive = onMessageReceive;
    }

    public void startServer() throws IOException {
        createFile(filePath);
        fileWatcher = new FileWatcher(filePath, onMessageReceive);
        fileWatcher.start();
    }

    public void stopServer() throws IOException {
        fileWatcher.stopWatching();
        removeFile(filePath);
    }

    private void createFile(Path path) throws IOException {
        try {
            new ProcessBuilder("mkfifo", path.toString()).start().waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeFile(Path path) throws IOException {
        Files.delete(path);
    }

    public void lock(String userName) {
        lockUserName = userName;
    }

    public void unlock() {
        lockUserName = null;
    }

    public boolean isLocked(String userName) {
        if (lockUserName == null)
            return false;

        return !userName.equals(lockUserName);
    }
}
