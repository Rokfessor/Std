package org.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import org.example.model.Message;
import org.example.model.MessageParser;
import org.example.model.MessageType;
import org.example.model.Server;

import java.io.IOException;
import java.nio.file.Path;

public class AppController {

    private Server server;

    private final Path FIFO_FILE = Path.of("/home/maxim/Desktop/fifoServer");

    @FXML
    public Button runServerBtn;
    @FXML
    public Button stopServerBtn;
    @FXML
    public TextArea messagesTextArea;
    @FXML
    public TextArea infoTextArea;

    @FXML
    public void initialize() {
        server = new Server(FIFO_FILE, (m) -> {
            try {
                Message message = MessageParser.parse(m);
                if (!server.isLocked(message.clientName)) {
                    if (message.type == MessageType.Lock) {
                        if ("true".equals(message.text)) {
                            server.lock(message.clientName);
                            writeInfoMessage("Server locked by " + message.clientName);
                        } else {
                            server.unlock();
                            writeInfoMessage("Server unlocked");
                        }
                        return null;
                    }

                    if (message.type != MessageType.Message) {
                        writeInfoMessage(message.type.toString() + " " + message.clientName);
                    } else {
                        writeClientMessage("[" + message.clientName + "] " + message.text);
                    }
                }
            } catch (IllegalArgumentException e) {
                writeInfoMessage("Got wrong message: " + m + "\n" + e.getMessage());
            }
            return null;
        });
    }

    @FXML
    public void runServer() {
        try {
            server.startServer();
            writeInfoMessage("Server started: " + FIFO_FILE);
            stopServerBtn.setDisable(false);
            runServerBtn.setDisable(true);
        } catch (IOException e) {
            writeInfoMessage("Server start failed: " + FIFO_FILE + "\n " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void stopServer() {
        try {
            if (server != null)
                server.stopServer();

            writeInfoMessage("Server stopped: " + FIFO_FILE);
        } catch (IOException e) {
            writeInfoMessage("Server stop failed: " + FIFO_FILE);
            e.printStackTrace();
        }
        stopServerBtn.setDisable(true);
        runServerBtn.setDisable(false);

    }

    @FXML
    public void clearInfo() {
        infoTextArea.clear();
    }

    @FXML
    public void clearMessages() {
        messagesTextArea.clear();
    }

    @FXML
    public void about() {
        AboutController ac = new AboutController(infoTextArea.getScene().getWindow());
        ac.show();
    }

    @FXML
    public void exit() {
        try {
            if (server != null)
                server.stopServer();

            Platform.exit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeInfoMessage(String message) {
        Platform.runLater(() -> {
            infoTextArea.appendText("==\n");
            infoTextArea.appendText(message);
            infoTextArea.appendText("\n==\n\n");
        });
    }

    private void writeClientMessage(String message) {
        Platform.runLater(() -> {
            messagesTextArea.appendText("==\n");
            messagesTextArea.appendText(message);
            messagesTextArea.appendText("\n==\n\n");
        });
    }
}
