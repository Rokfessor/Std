import Exc.WeigherException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Exc.WeigherException.WeigherError.EMPTY_INPUT;
import static Exc.WeigherException.WeigherError.INAPPROPRIATE_SYMBOLS;


public class Main extends Application implements EventHandler<ActionEvent> {

    public static void main(String[] args) {
        Application.launch(args);
    }

    private static Stage stage;
    public static boolean isInstance = false;
    public static boolean antiAliasing = false;
    private static Scene mainScene;
    static GridPane mainPane;
    private static TextField inputArea;
    private static TextArea resultArea;
    private Button solveButton, borderButton, clearButton;
    private Label errorLabel;
    private Pattern dataPattern = Pattern.compile("([1-9][0-9]*[ ]*[к]?[г]?,? ?)+");
    private Pattern weightPattern = Pattern.compile("[1-9][0-9]*");
    private static TextField inputWeight;
    public static int N = 0;
    public static int weightMax = 0;
    public static boolean[][] tab;
    public static int[] vValue;
    public static TableView<Iteration> tableIteration;
    public static TableColumn<Iteration, String> colTry;
    public List<Iteration> iterationList = new ArrayList<>();
    public ObservableList<Iteration> iterationsListObs;

    @Override
    public void start(Stage primaryStage) throws Exception {
        tableIteration = new TableView<>();
        colTry = new TableColumn<>("n\\s");
        colTry.setSortable(false);
        colTry.setEditable(false);
        colTry.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Iteration, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Iteration, String> param) {
                String index = Integer.toString(param.getValue().getIndex());
                return new SimpleStringProperty(index);
            }
        });
        inputArea = new TextField();
        inputArea.setStyle("-fx-font-size:12 ");
        inputWeight = new TextField();
        inputWeight.setStyle("-fx-font-size:12");
        resultArea = new TextArea();
        resultArea.setStyle("-fx-font-size:12");
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        solveButton = new Button("РЕШИТЬ");
        solveButton.setStyle("-fx-font-size:12");
        solveButton.setOnAction(this);
        solveButton.setAlignment(Pos.CENTER);
        clearButton = new Button("ОЧИСТИТЬ");
        clearButton.setStyle("-fx-font-size:12");
        clearButton.setOnAction(this);
        clearButton.setAlignment(Pos.CENTER);
        errorLabel = new Label();
        errorLabel.setStyle("-fx-font-size:16; -fx-text-fill: CC0000;");
        errorLabel.setAlignment(Pos.CENTER);
        resultArea.setText("");
        Label inputLabel = new Label("Данные:");
        Label resultLabel = new Label("Результат:");
        Label inputWeightLabel = new Label("Необходимая сумма");
        inputLabel.setStyle("-fx-font-size:12");
        resultLabel.setStyle("-fx-font-size:12");
        inputWeightLabel.setStyle("-fx-font-size:12");

        HBox solveButtonBox = new HBox(solveButton);
        solveButtonBox.setAlignment(Pos.CENTER);
        HBox clearButtonBox = new HBox(clearButton);
        clearButtonBox.setAlignment(Pos.CENTER);
        HBox errorLabelBox = new HBox(errorLabel);
        errorLabelBox.setAlignment(Pos.CENTER);

        GridPane buttonsPane = new GridPane();
        buttonsPane.setAlignment(Pos.CENTER);
        buttonsPane.setHgap(25);
        buttonsPane.add(solveButtonBox, 0, 0);
        buttonsPane.add(clearButtonBox, 1, 0);

        mainPane = new GridPane();
        mainPane.setAlignment(Pos.CENTER);
        mainPane.add(inputWeightLabel, 0, 0);
        mainPane.add(inputWeight, 0, 1);
        mainPane.add(inputLabel, 0, 2);
        mainPane.add(inputArea, 0, 3);
        mainPane.add(buttonsPane, 0, 4);
        mainPane.add(tableIteration, 0, 5);
        tableIteration.setMinHeight(350);
        mainPane.add(resultLabel, 0, 6);
        mainPane.add(resultArea, 0, 7);
        mainPane.add(errorLabelBox, 0, 8);
        mainPane.setVgap(5);
        mainPane.setPadding(new Insets(8));
        GridPane.setHgrow(inputArea, Priority.ALWAYS);
        GridPane.setHgrow(inputWeight, Priority.ALWAYS);
        GridPane.setHgrow(resultArea, Priority.ALWAYS);
        GridPane.setHgrow(errorLabel, Priority.ALWAYS);
        GridPane.setHgrow(tableIteration, Priority.ALWAYS);
        GridPane.setVgrow(tableIteration, Priority.ALWAYS);

        AnchorPane anchorMain = new AnchorPane();
        AnchorPane.setTopAnchor(mainPane, 0.);
        AnchorPane.setBottomAnchor(mainPane, 0.);
        AnchorPane.setLeftAnchor(mainPane, 0.);
        AnchorPane.setRightAnchor(mainPane, 0.);
        anchorMain.getChildren().add(mainPane);

        mainScene = new Scene(anchorMain, 800, 600, true, SceneAntialiasing.DISABLED);
        primaryStage.setResizable(true);
        primaryStage.setMinHeight(800);
        primaryStage.setMinWidth(600);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Набор суммы");
        primaryStage.show();
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreen(false);

        //Scene scene = new Scene(mainScene, 800, 600, true, SceneAntialiasing.DISABLED);
        primaryStage.setScene(mainScene);

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
        isInstance = true;
        primaryStage.show();
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        errorLabel.setText("");
        inputArea.setText(inputArea.getText().trim());
        inputWeight.setText(inputWeight.getText().trim());
        weightMax = 0;
        if (actionEvent.getSource() == solveButton) {
            resultArea.clear();
            try {
                validateData(inputArea.getText().trim() + ",");
                String temporaryClear = inputWeight.getText();
                validateWeight(temporaryClear);
                weightMax = Integer.parseInt(temporaryClear);
                temporaryClear = inputArea.getText();
                handleData(temporaryClear.trim());
                if (iterationList != null) {
                    iterationList.clear();
                }
                if (iterationsListObs != null) {
                    iterationsListObs.clear();
                }
                tableIteration.refresh();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isSubsetSum()) {
                            System.out.println("Найденно подмножетсво с суммой равной = " + weightMax + ": ");
                            int indI = 0, indY = 0;
                            for (int i = 0; i <= N; i++) {
                                if (tab[i][weightMax]) {
                                    indI = i;
                                    indY = weightMax;
                                    break;
                                }
                            }
                            String tmp = "Предметы со стоимостью:\n" + vValue[indI] + "; ";
                            indY -= vValue[indI];
                            int sum = vValue[indI];
                            indI--;
                            if (sum != weightMax) {
                                while (true) {
                                    int tmpI = indI, tmpY = indY;
                                    if (tmpI - 1 < 0) {
                                        break;
                                    }
                                    if (tab[tmpI - 1][tmpY - vValue[tmpI]]) {
                                        tmp += vValue[tmpI] + "; ";
                                        sum += vValue[tmpI];
                                        indI = tmpI - 1;
                                        indY = tmpY - vValue[tmpI];
                                        if (sum == weightMax) {
                                            break;
                                        }
                                    } else {
                                        indI--;
                                        if (indI < 0) {
                                            break;
                                        }
                                    }
                                }
                            }
                            resultArea.setText("Найдено подмножетсво с суммой равной = " + weightMax + "\n" + tmp);
                        } else {
                            resultArea.setText("Подходящего подмножества с суммой равной = " + weightMax + " нет.");
                            System.out.println("Подходящего подмножества с суммой равной = " + weightMax + " нет.");
                        }
                    }
                }).start();
            } catch (WeigherException wex) {
                errorLabel.setText(wex.getMessage());
            }
        }
        if (actionEvent.getSource() == clearButton) {
            inputArea.clear();
            resultArea.clear();
            inputWeight.clear();
            if (iterationList != null) {
                iterationList.clear();
            }
            if (iterationsListObs != null) {
                iterationsListObs.clear();
            }
            tableIteration.refresh();
        }
    }

    public boolean isSubsetSum() {
        for (int i = 0; i <= N; i++)
            tab[i][0] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= weightMax; j++) {
                tab[i][j] = tab[i - 1][j];
                if (j >= vValue[i]) {
                    tab[i][j] = tab[i][j] || tab[i - 1][j - vValue[i]];
                }
            }
            updateTable(i);
        }

        return tab[N][weightMax];
    }

    public void updateTable(int index) {
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iteration tmp = new Iteration(weightMax + 1);
        for (int i = 0; i <= weightMax; i++) {
            tmp.getCondition()[i] = tab[index][i];
        }
        tmp.setIndex(vValue[index]);
        Platform.runLater(() -> {
            iterationList.add(tmp);
            iterationsListObs = FXCollections.observableList(iterationList);
            tableIteration.setItems(iterationsListObs);
            tableIteration.refresh();
        });
    }

    public void handleData(String inputData) throws WeigherException {
        String newInputData = inputData.replaceAll("кг", " ").replaceAll(",", " ").replaceAll(" +", " ") + " ";
        int previous = 0;
        int index = 0;
        for (String elem : newInputData.split(" ")) {
            if (elem != " " && elem != "") {
                int num = Integer.parseInt(elem);
                index++;
            }
        }
        vValue = new int[index + 1];
        vValue[0] = 0;
        int newIndex = 1;
        for (String elem : newInputData.split(" ")) {
            if (elem != " " && elem != "") {
                vValue[newIndex] = Integer.parseInt(elem);
                if (newIndex == index) {
                    break;
                }
                newIndex++;
            }
        }
        N = index;
        tab = new boolean[N + 1][weightMax + 1];
        TableColumn<Iteration, String>[] colArray = new TableColumn[weightMax + 1];
        if (tableIteration != null && tableIteration.getColumns() != null) {
            tableIteration.getColumns().clear();
        }
        for (int i = 0; i <= weightMax; i++) {
            String name = Integer.toString(i);
            TableColumn<Iteration, String> tmpCol = new TableColumn<>(name);
            tmpCol.setSortable(false);
            tmpCol.setEditable(false);
            tmpCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Iteration, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<Iteration, String> param) {
                    boolean cond = param.getValue().getCondition()[Integer.parseInt(tmpCol.getText())];
                    String res = "";
                    if (cond) {
                        res = "T";
                    } else {
                        res = "F";
                    }
                    return new SimpleStringProperty(res);
                }
            });
            colArray[i] = tmpCol;
        }
        tableIteration.getColumns().add(colTry);
        double sizeCol = 15 / (double) weightMax / (double) 100;
        for (int i = 0; i <= weightMax; i++) {
            tableIteration.getColumns().add(colArray[i]);
            colArray[i].minWidthProperty().bind(tableIteration.widthProperty().multiply(sizeCol));
        }
        tableIteration.refresh();
    }

    public void validateData(String inputData) throws WeigherException {
        if (inputData.trim().equals("")) {
            throw new WeigherException(EMPTY_INPUT);
        }
        Matcher matcher = dataPattern.matcher((inputData));
        while (matcher.find()) {
            System.out.println(inputData.substring(matcher.start(), matcher.end()));
        }
        if (!dataPattern.matcher(inputData).matches()) {
            throw new WeigherException(INAPPROPRIATE_SYMBOLS);
        }
    }

    public void validateWeight(String inputData) throws WeigherException {
        if (inputData.trim().equals("")) {
            throw new WeigherException(EMPTY_INPUT);
        }
        if (!weightPattern.matcher(inputData).matches()) {
            throw new WeigherException(INAPPROPRIATE_SYMBOLS);
        }
    }

    public static void setResult(String message) {
        resultArea.appendText(message);
    }
}
