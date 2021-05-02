import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.IndexRange;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.StyleClassedTextArea;

public class Main extends Application {

    /*public static void main(String[] args) {
        launch(args);
    }

    private final StyleClassedTextArea area = new StyleClassedTextArea();

    @Override
    public void start(Stage primaryStage) {
        Button red = createColorButton(Color.RED, "red");
        Button green = createColorButton(Color.GREEN, "green");
        Button blue = createColorButton(Color.BLUE, "blue");
        Button bold = createBoldButton("bold");
        HBox panel = new HBox(red, green, blue, bold);

        VirtualizedScrollPane<StyleClassedTextArea> vsPane = new VirtualizedScrollPane<>(area);
        VBox.setVgrow(vsPane, Priority.ALWAYS);
        area.setWrapText(true);
        VBox vbox = new VBox(panel, vsPane);

        Scene scene = new Scene(vbox, 600, 400);
        scene.getStylesheets().add(Main.class.getResource("css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Manual Highlighting Demo");
        primaryStage.show();

        area.requestFocus();
    }

    private Button createBoldButton(String styleClass) {
        Button button = new Button("B");
        button.styleProperty().set("-fx-font-weight: bold;");
        setPushHandler(button, styleClass);
        return button;
    }

    private Button createColorButton(Color color, String styleClass) {
        Button button = new Button("Dsad");
        setPushHandler(button, styleClass);
        return button;
    }

    private void setPushHandler(Button button, String styleClass) {
        button.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                IndexRange range = area.getSelection();
                area.setStyleClass(range.getStart(), range.getEnd(), styleClass);
                area.requestFocus();
            }
        });
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.setTitle("Regex");
        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add(getClass().getResource("css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
