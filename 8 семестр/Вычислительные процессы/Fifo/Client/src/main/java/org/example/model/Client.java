package org.example.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class Client {
    private final Path fifoServer;

    private final String name;

    private FileOutputStream writer;

    public Client(String name, Path fifoFile) {
        this.fifoServer = fifoFile;
        this.name = name;
    }

    public void connect() throws IOException {
        if (Files.exists(fifoServer)) {
            writer = new FileOutputStream(fifoServer.toString());
            writer.write(("[Connect." + name + "]").getBytes());
            writer.flush();
        } else
            throw new FileNotFoundException("File " + fifoServer + " doesn't exist");
    }

    public Path getFifoServer() {
        return fifoServer;
    }

    public void sendMessage(String message) throws IOException {
        writer.write(("[Message." + name + "]" + message).getBytes());
        writer.flush();
    }

    public void setExclusiveMode(boolean val) throws IOException {
        writer.write(("[Lock." + name + "]" + val).getBytes());
    }

    public void disconnectFromServer() throws IOException {
        setExclusiveMode(false);
        writer.write(("[Disconnect." + name + "]").getBytes());
        writer.flush();
        writer.close();
    }
}
