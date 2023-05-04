package org.example.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.model.Client;

import java.io.IOException;

public class AppController {

    private Client client;

    @FXML
    public Button sendMessageBtn;

    @FXML
    public Button connectBtn;

    @FXML
    public Button disconnectBtn;

    @FXML
    CheckBox exclusiveMode;

    @FXML
    TextField messageTextField;

    @FXML
    TextArea infoTextArea;

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    public void sendMessage() {
        try {
            client.sendMessage(messageTextField.getText());
            writeInfoMessage("Message successfully sent");
            messageTextField.clear();
            messageChanged();
        } catch (IOException e) {
            writeInfoMessage("Message wasn't sent: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void about() {
        AboutController ac = new AboutController(infoTextArea.getScene().getWindow());
        ac.show();
    }

    @FXML
    public void connect() {
        try {
            writeInfoMessage("Trying to connect to the server: " + client.getFifoServer());
            client.connect();
            writeInfoMessage("Successfully connected");
            sendMessageBtn.setDisable(false);
            disconnectBtn.setDisable(false);
            exclusiveMode.setDisable(false);
            messageTextField.setDisable(false);
            connectBtn.setDisable(true);
            messageChanged();
        } catch (IOException e) {
            writeInfoMessage("Unable to connect to the server: " + e.getMessage());
        }
    }

    @FXML
    public void disconnect() {
        try {
            writeInfoMessage("Trying to disconnect from the server: " + client.getFifoServer());
            client.disconnectFromServer();
        } catch (IOException e) {
            writeInfoMessage(e.getMessage());
        }

        sendMessageBtn.setDisable(true);
        disconnectBtn.setDisable(true);
        exclusiveMode.setDisable(true);
        messageTextField.setDisable(true);
        connectBtn.setDisable(false);
    }

    @FXML
    public void modeChanged() {
        try {
            client.setExclusiveMode(exclusiveMode.isSelected());
            writeInfoMessage("Exclusive mode is " + (exclusiveMode.isSelected() ? "on" : "off"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void messageChanged() {
        sendMessageBtn.setDisable(messageTextField.getText().length() == 0);
    }

    private void writeInfoMessage(String message) {
        infoTextArea.appendText("==\n");
        infoTextArea.appendText(message);
        infoTextArea.appendText("\n==\n\n");
    }

    public void exit() {
        try {
            disconnect();
        } catch (RuntimeException ignored) {
        }
        Platform.exit();
    }
}
