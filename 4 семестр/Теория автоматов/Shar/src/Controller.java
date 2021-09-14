import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Button startButton;
    public Button stopButton;
    public Pane drawPane;
    public Button resetButton;
    public Slider timeSlider;
    public Line line1;
    public Line line2;
    public Line line3;
    private Painter painter;

    public void stop(ActionEvent actionEvent) {
        painter.pause();
    }

    public void start(ActionEvent actionEvent) {
        painter.start();
    }

    public void reset(ActionEvent actionEvent) {
        painter.reset();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        painter = new Painter(drawPane, line1, line2, line3);
        painter.setTime((int) timeSlider.getValue());
    }

    public void setTime(MouseEvent dragEvent) {
        painter.setTime((int) timeSlider.getValue());
    }
}
