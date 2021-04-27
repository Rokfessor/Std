import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class Painter {
    private SequentialTransition transition;
    private Circle circle;
    private Line line1;
    private Line line2;
    private Line line3;
    private final Pane pane;
    private int seconds;
    private Handler handler;
    private boolean flag= false;
    private final int xOffset = 80;
    private final int yOffset = 80;

    public Painter(Pane pane, Line line1, Line line2, Line line3) {
        this.pane = pane;
        handler = new Handler();
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
    }

    public void start() {
        if (transition != null && transition.getStatus() == Animation.Status.RUNNING) {
        } else if (transition != null && transition.getStatus() == Animation.Status.PAUSED) {
            transition.play();
        } else {
            boolean left = flag;
            flag = !flag;
            int x = left ? 110 : 280;
            int y = 30;
            Res res = handler.calcStep(left);

            transition = new SequentialTransition();
            Duration duration = Duration.millis((seconds * 1000f) / 4);
            circle = new Circle();
            circle.setCenterX(x);
            circle.setCenterY(y);
            circle.setRadius(10);
            pane.getChildren().add(circle);

            //Начаьный спуск
            y += 50;
            KeyValue downStart = new KeyValue(circle.centerYProperty(), y);
            Timeline tl1 = new Timeline();
            tl1.getKeyFrames().add(new KeyFrame(duration, downStart));
            transition.getChildren().add(tl1);

            //1 угол
            boolean center;
            x = res.isR1() ? x + xOffset : x - xOffset;
            y += yOffset;
            ParallelTransition p_st1 = new ParallelTransition();
            RotateTransition r_st1;

            r_st1 = left ? new RotateTransition(duration, line1) : new RotateTransition(duration, line3);
            r_st1.setByAngle(res.isR1() ? 90 : -90);
            center = (left == res.isR1());

            KeyValue toX_st1 = new KeyValue(circle.centerXProperty(), x);
            KeyValue toY_st1 = new KeyValue(circle.centerYProperty(), y);
            Timeline t_st1 = new Timeline();
            t_st1.getKeyFrames().add(new KeyFrame(duration, toX_st1, toY_st1));
            p_st1.getChildren().add(t_st1);
            p_st1.getChildren().add(r_st1);
            transition.getChildren().add(p_st1);

            //2 угол
            ParallelTransition p_st2 = new ParallelTransition();
            if (center) {
                RotateTransition r_st2 = new RotateTransition(duration, line2);
                r_st2.setByAngle(res.isR2() ? 90 : -90);
                p_st2.getChildren().add(r_st2);
            }
            x = res.isR2() ? x + xOffset : x - xOffset;
            y += yOffset;

            KeyValue toX_st2 = new KeyValue(circle.centerXProperty(), x);
            KeyValue toY_st2 = new KeyValue(circle.centerYProperty(), y);
            Timeline t_st2 = new Timeline();
            t_st2.getKeyFrames().add(new KeyFrame(duration, toX_st2, toY_st2));
            p_st2.getChildren().add(t_st2);
            transition.getChildren().add(p_st2);

            //Финальный спуск
            y += 40;
            KeyValue downEnd = new KeyValue(circle.centerYProperty(), y);
            Timeline tl2 = new Timeline();
            tl2.getKeyFrames().add(new KeyFrame(duration, downEnd));
            transition.getChildren().add(tl2);

            transition.setOnFinished((finish) -> {
                pane.getChildren().remove(circle);
                start();
            });
            transition.play();
        }
    }

    public void setTime(int val) {
        seconds = val;
    }

    public void pause() {
        if (transition.getStatus() == Animation.Status.RUNNING && transition != null) {
            transition.pause();
        }
    }

    public void reset() {
        if (transition != null) {
            transition.stop();
            resetLines();
            flag = false;
            handler = new Handler();
            pane.getChildren().remove(circle);
        }
    }

    public void resetLines() {
        pane.getChildren().remove(line1);
        pane.getChildren().remove(line2);
        pane.getChildren().remove(line3);
        line1 = new Line(100, 109, 120, 88);
        line1.setStrokeWidth(3);
        line2 = new Line(180, 186,208,156);
        line2.setStrokeWidth(6);
        line3 = new Line(270, 109, 290,88);
        line3.setStrokeWidth(3);
        pane.getChildren().add(line1);
        pane.getChildren().add(line2);
        pane.getChildren().add(line3);
    }
}
