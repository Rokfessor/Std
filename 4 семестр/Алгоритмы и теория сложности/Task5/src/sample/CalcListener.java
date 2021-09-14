package sample;

import java.util.List;

public interface CalcListener {
    void setResult(List<Domino[]> res);
    void stateChanged(Domino[] m);
}
