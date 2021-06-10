package model.utils;

import javafx.scene.control.Alert;

public class Utils {
    public static boolean calcLuck(double luck){
        return Math.random() * 100 < luck;
    }

    public static double round(double val) {
        return Math.ceil(val * 100) / 100;
    }

    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.setTitle(null);
        alert.show();
    }
}
