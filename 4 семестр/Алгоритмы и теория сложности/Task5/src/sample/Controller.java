package sample;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable, CalcListener {
    public TextField inputTF;
    public TextField dicesTF;
    public TextField currentDicesTF;
    public TextArea resultTF;
    public Slider slider;

    public void calculate() {
        try {
            Domino[] dominos = Utils.parse(inputTF.getText());
            StringBuilder sb = new StringBuilder();
            for (Domino d : dominos)
                sb.append(d).append(" ");
            dicesTF.setText(sb.toString());
            currentDicesTF.setText("");
            Thread th = new Thread(() -> Calculator.calculate(dominos));
            th.start();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Calculator.setCalcListener(this);
    }

    @Override
    public void setResult(List<Domino[]> res) {
        StringBuilder sb = new StringBuilder();
        for (Domino[] ds : res) {
            for (Domino d : ds)
                sb.append(d).append(" ");
            sb.append("\n");
        }
        resultTF.setText(sb.toString());
    }

    public void setSpeed() {
        Calculator.sleep = (long) slider.getValue() * 1000;
    }

    @Override
    public void stateChanged(Domino[] dominos) {
        StringBuilder sb = new StringBuilder();
        for (Domino d : dominos)
            sb.append(d).append(" ");
        currentDicesTF.setText(sb.toString());
    }
}
