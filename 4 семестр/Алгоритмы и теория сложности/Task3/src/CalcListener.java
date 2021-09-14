public interface CalcListener {
    void stateChangedExact(double[] mass1, int[] mass2, int[] mass3);
    void setResultExact(String res);
    void stateChangedApprox(double[] mass1, double[] mass2, int i, int j);
    void setResultApprox(String res);
}
