package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Calc;
import model.Calculator;

import java.util.Arrays;

public class MainController {

    public TextField maxWeightTF;
    public TextField weightTF;
    public TableView<boolean[]> resTable;
    public Label resLabel;

    public void digitCheckWei(KeyEvent keyEvent) {
        if (!(keyEvent.getCode().isDigitKey() || keyEvent.getCode().equals(KeyCode.SPACE)))
            weightTF.deletePreviousChar();
    }

    public void digitCheckMaxWei(KeyEvent keyEvent) {
        if (!keyEvent.getCode().isDigitKey())
            maxWeightTF.deletePreviousChar();
    }

    public void calculate(ActionEvent actionEvent) {
        int[] mass = new int[0];
        int it = 0;
        for (String digit : weightTF.getText().split(" ")) {
            mass = Arrays.copyOf(mass, mass.length + 1);
            mass[it] = Integer.parseInt(digit);
            ++it;
        }

        boolean[][] res = Calculator.calculate(mass, Integer.parseInt(maxWeightTF.getText()));
        System.err.println(Arrays.deepToString(res));
        for (int i = 0; i < res[0].length - 1; i++) {
            TableColumn<boolean[], Boolean> column = new TableColumn<>(mass[i] + "");

            int finalI = i;
            column.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue()[finalI + 1]));

            column.setCellFactory(new Callback<>() {
                @Override
                public TableCell<boolean[], Boolean> call(TableColumn<boolean[], Boolean> booleanTableColumn) {
                    return new TableCell<>() {
                        @Override
                        public void updateItem(Boolean item, boolean empty) {
                            super.updateItem(item, empty);
                            if (!isEmpty()) {
                                if (item)
                                    this.setTextFill(Color.GREEN);
                                else
                                    this.setTextFill(Color.RED);
                                setText(item.toString());
                            }
                        }
                    };
                }
            });

            resTable.getColumns().add(column);
        }

        resTable.setItems(FXCollections.observableList(Arrays.asList(res)));
        try {
            resLabel.setText(Calc.calculate(Integer.parseInt(maxWeightTF.getText()), mass));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}