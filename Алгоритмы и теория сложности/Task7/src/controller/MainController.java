package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.Calculator;
import model.Obj;
import model.Utils;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public TableView<Obj> tableSet;
    public TableColumn<Obj, String> setNameCol;
    public TableColumn<Obj, String> setWeightCol;
    public TableColumn<Obj, String> setCostCol;
    public TextField nameTF;
    public TextField weightTF;
    public TextField costTF;
    public TextField maxWeightTF;
    public TableView<Obj> nameTable;
    public TableColumn<Obj, String> nameCol;
    public TableView<int[]> resTable;
    public Label resLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        setWeightCol.setCellValueFactory(new PropertyValueFactory<>("weight"));
        setCostCol.setCellValueFactory(new PropertyValueFactory<>("cost"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableSet.getItems().addAll(new Obj("Ноутбук", 3, 2000),
                new Obj("Пила", 4, 3000),
                new Obj("Гитара", 1, 1500));
    }

    public void digitCheckWei(KeyEvent keyEvent) {
        if (!keyEvent.getCode().isDigitKey())
            weightTF.deletePreviousChar();
    }

    public void digitCheckCost(KeyEvent keyEvent) {
        if (!keyEvent.getCode().isDigitKey())
            costTF.deletePreviousChar();
    }

    public void digitCheckMaxWei(KeyEvent keyEvent) {
        if (!keyEvent.getCode().isDigitKey())
            maxWeightTF.deletePreviousChar();
    }

    public void addRow(ActionEvent actionEvent) {
        String name = nameTF.getText();
        int cost = 0;
        int weight = 0;
        boolean flag = true;
        StringBuilder sb = new StringBuilder("Поле ");

        if (name.isBlank()) {
            flag = false;
            sb.append(" 'Имя' ");
        }

        try {
            cost = Integer.parseInt(costTF.getText());
        } catch (NumberFormatException e) {
            sb.append(" 'Стоимость' ");
        }

        try {
            weight = Integer.parseInt(weightTF.getText());
        } catch (NumberFormatException e) {
            sb.append(" 'Вес' ");
        }

        if (flag) {
            tableSet.getItems().add(new Obj(name, weight, cost));
        } else {
            sb.append(" должно быть заполнено!");
            Utils.showAlert(sb.toString());
        }
    }

    public void removeRow(ActionEvent actionEvent) {
        tableSet.getItems().remove(tableSet.getSelectionModel().getSelectedIndex());
    }

    public void calculate(ActionEvent actionEvent) {
        List<Obj> list = tableSet.getItems();
        int weight = 0;
        boolean flag = true;

        if (list.size() == 0) {
            Utils.showAlert("ЪЕЪ 0 предметов!!");
            flag = false;
        } else {
            try {
                weight = Integer.parseInt(maxWeightTF.getText());
            } catch (NumberFormatException e) {
                Utils.showAlert("ВЕС!! ВВЕДИТЕ ВЕЕЕС!!");
                flag = false;
            }
        }

        if (flag) {
            int[][] res = Calculator.calculate(list, weight);

            resTable.getColumns().remove(0, resTable.getColumns().size());

            for (int i = 0; i < res[0].length; i++) {
                TableColumn<int[], Integer> column = new TableColumn<>(i + "");
                int finalI = i;
                column.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue()[finalI]));

                resTable.getColumns().add(column);
            }

            resLabel.setText("Решение: " + res[res.length - 1][res[0].length - 1]);
            resTable.setItems(FXCollections.observableList(Arrays.asList(res)));

            nameTable.setItems(tableSet.getItems());
        }
    }
}
