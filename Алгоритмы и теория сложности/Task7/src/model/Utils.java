package model;

import javafx.scene.control.Alert;

public class Utils {
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Некорректные данные");
        alert.setContentText(message);
        alert.show();
    }
}
