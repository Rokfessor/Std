package org.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NamePickerController {
    @FXML
    public TextField textField;

    public String getEnteredName() {
        return textField.getText();
    }
}
