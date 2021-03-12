package Task1.Procedure.GUI;

import javax.swing.*;
import java.awt.*;

public class ClockView extends JLabel {
    public ClockView(){
        super(String.format("%02d",0) + ":" + String.format("%02d",0));
        setFont(new Font(Font.MONOSPACED, Font.PLAIN, 25));
    }

    public void setValues(int H, int M) {
        setText(String.format("%02d",H) + ":" + String.format("%02d",M));
    }

}
