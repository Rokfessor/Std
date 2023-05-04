package org.example.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import org.example.model.MemoryBlock;
import org.example.model.SystemProcessesUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AppController {

    //Маппинг свойств из разметки (App.fxml)
    @FXML
    public ChoiceBox<Map.Entry<Integer, String>> choiceBox;

    @FXML
    public TextField minAddress;

    @FXML
    public TextField maxAddress;

    @FXML
    TableView<MemoryBlock> table;

    @FXML
    TableColumn<MemoryBlock, String> addressCol;

    @FXML
    TableColumn<MemoryBlock, String> kbytesCol;

    @FXML
    TableColumn<MemoryBlock, String> modeCol;

    @FXML
    TableColumn<MemoryBlock, String> mappingCol;


    //Инициализация контроллера
    @FXML
    public void initialize() {
        choiceBox //Устанавливаем обработчик выбора процесса из списка
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        updateTable(newValue.getKey());
                        return;
                    }
                    updateTable(choiceBox.getItems().get(0).getKey());
                });
        choiceBox.setConverter(new StringConverter<>() { //Конвертер для отображения элементов в выпадающем списке
            @Override
            public String toString(Map.Entry<Integer, String> object) {
                if (object == null)
                    return "";
                return object.getValue();
            }
            @Override
            public Map.Entry<Integer, String> fromString(String string) {
                return choiceBox.getItems().get(0);
            }
        });

        //Обработчики для генерации текста в клетках таблицы
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        kbytesCol.setCellValueFactory(new PropertyValueFactory<>("kbytes"));
        modeCol.setCellValueFactory(new PropertyValueFactory<>("mode"));
        mappingCol.setCellValueFactory(new PropertyValueFactory<>("mapping"));

        updateProcesses(); //Загружаем таблицу с данными о процессе
    }

    //Маппинг обработчиков из разметки (App.fxml)
    @FXML
    public void minAddressChanged() {
        updateTable(choiceBox.getValue().getKey());
    }

    @FXML
    public void maxAddressChanged() {
        updateTable(choiceBox.getValue().getKey());
    }

    @FXML
    public void updateProcesses() {
        List<Map.Entry<Integer, String>> vals;
        try {
            vals = new ArrayList<>(SystemProcessesUtils.getAvailableProcesses().entrySet()); //Получаем список процессов системы
            choiceBox.setItems(FXCollections.observableArrayList(vals)); //Устанавливаем список в выпадающее меню
            choiceBox.setValue(vals.get(0)); //Устанавливаем начальное значение в списке
        } catch (IOException e) {
            showError(e);
            e.printStackTrace();
        }
    }

    @FXML
    public void aboutProgram() {
        new AboutController(table.getScene().getWindow()).show(); //Отображаем окно с информацией о программе
    }

    @FXML
    public void exit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Do you really want to exit the MemWatcher?");

        Optional<ButtonType> res = alert.showAndWait();

        if (res.isPresent() && res.get() == ButtonType.OK)
            Platform.exit();
    }

    private void updateTable(int pid) {
        try {
            //Получаем данные об используемой процессом памяти
            List<MemoryBlock> data = SystemProcessesUtils.getProcessInfo(pid, minAddress.getText(), maxAddress.getText());
            //устанавливаем полученные данные в таблицу
            table.setItems(FXCollections.observableArrayList(data));
        } catch (IOException e) {
            showError(e);
            e.printStackTrace();
        }
    }

    //Обработка отображния об ошибке
    private void showError(Throwable t) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText(null);
        alert.setContentText(t.getMessage());
        System.err.println("error");
        alert.showAndWait();
    }
}
