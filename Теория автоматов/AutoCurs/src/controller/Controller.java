package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Calculator;

import java.io.IOException;

public class Controller {
    public TextField inputField;
    private final Calculator calculator = new Calculator();
    @FXML
    private Painter painterController; //Если что, то при создании(которого нет) - происходит магия!!

    public void buttonClicked(ActionEvent actionEvent) {
        String buttonName = ((Button) actionEvent.getSource()).getText();
        if (!calculator.isOFF) {

            if (!buttonName.equals("MRC") && calculator.MRC)
                calculator.MRC = false;

            switch (buttonName) {
                case "OFF/ON" -> {
                    painterController.drawOnOFF(calculator.switchOffState());
                    inputField.setText(calculator.symbols.toString());
                }
                case "." -> {
                    calculator.addDot();
                    inputField.setText(calculator.symbols.toString());
                }
                case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                    painterController.drawAddSymbol(calculator.addSymbol(buttonName));
                    inputField.setText(calculator.symbols.toString());
                }
                case "С/CE" -> {
                    if (!calculator.CE) {
                        calculator.cleanSymbols();
                        painterController.drawClean(false);
                        calculator.CE = true;
                    } else {
                        calculator.cleanSymbolsAndCalculation();
                        painterController.drawClean(true);
                        calculator.CE = false;
                    }
                    inputField.setText(calculator.symbols.toString());
                }
                case "%", "/", "*", "-", "+" -> {
                    calculator.addAction(buttonName);
                    inputField.clear();
                    painterController.drawAction();
                }
                case "=" -> {
                    try {
                        calculator.calculate();
                        inputField.setText(calculator.symbols.toString());
                        painterController.drawCalculate();
                    } catch (IOException e) {
                        inputField.setText(e.getMessage());
                    }
                    painterController.drawCalculate();
                }
                case "+-" -> {
                    calculator.invertSign();
                    inputField.setText(calculator.symbols.toString());
                    painterController.drawInvert();
                }
                case "M+" -> {
                    calculator.sumWithMem();
                    painterController.drawMem();
                }
                case "M-" -> {
                    calculator.diffWithMem();
                    painterController.drawMem();
                }
                case "MRC" -> {
                    painterController.drawMRC(calculator.MRC);
                    calculator.symbols.setLength(0);
                    if (!calculator.MRC) {
                        calculator.symbols.append(calculator.memory);
                        inputField.setText(calculator.symbols.toString());
                        calculator.MRC = true;
                    } else {
                        inputField.setText("");
                        calculator.MRC = false;
                        calculator.memory = 0;
                    }
                }
            }
        } else if (buttonName.equals("OFF/ON")) {
            painterController.drawOnOFF(calculator.switchOffState());
        }
    }
}
