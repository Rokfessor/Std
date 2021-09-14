package model;

import java.io.IOException;
import java.text.DecimalFormat;

public class Calculator {
    public boolean MRC = false;
    public boolean CE = false;
    public double memory = 0;
    public StringBuilder calculation = new StringBuilder();
    public StringBuilder symbols = new StringBuilder();
    public boolean isOFF = true;
    private final DecimalFormat formatter = new DecimalFormat("########.#######");

    public String calculate() throws IOException {
        calculation.append(symbols);
        if (!calculation.isEmpty()) {
            String[] vals = calculation.toString().split(" ");
            double res = 0;
            for (int i = 0; i < vals.length; i++) {
                String val = vals[i];
                switch (val) {
                    case "+" -> res = res + Double.parseDouble(vals[++i]);
                    case "-" -> res = res - Double.parseDouble(vals[++i]);
                    case "/" -> res = res / Double.parseDouble(vals[++i]);
                    case "*" -> res = res * Double.parseDouble(vals[++i]);
                    case "%" -> res = res % Double.parseDouble(vals[++i]);
                    default -> res += Double.parseDouble(val);
                }
            }

            cleanSymbolsAndCalculation();
            symbols.append(res);
            return formatter.format(res);
        } else return "empty";
    }

    public boolean addSymbol(String symbol) {
        int temp = 0;
        temp += symbols.toString().contains("-") ? 1 : 0;
        temp += symbols.toString().contains(".") ? 1 : 0;
        if (symbols.length() < 8 + temp) {
            symbols.append(symbol);
            return true;
        }
        return false;
    }

    public void addAction(String action) {
        if (calculation.length() != 0 || symbols.length() != 0) {
            if (symbols.length() == 0) {
                int temp = calculation.length();
                calculation.replace(temp - 2, temp - 1, action);
            } else {
                calculation.append(symbols).append(" ")
                        .append(action).append(" ");
                symbols.setLength(0);
            }
        }
    }

    public void invertSign() {
        if (symbols.toString().contains("-")) {
            symbols.delete(0, 1);
        } else {
            symbols.insert(0, '-');
        }
    }

    public void addDot() {
        if (!symbols.toString().contains(".")) {
            symbols.append(".");
        }
    }

    public void cleanSymbols() {
        symbols.setLength(0);
    }

    public void cleanSymbolsAndCalculation() {
        symbols.setLength(0);
        calculation.setLength(0);
    }

    public void sumWithMem() {
        if (symbols.length() != 0)
            memory += Double.parseDouble(symbols.toString());

        System.err.println(memory);
    }

    public void diffWithMem() {
        if (symbols.length() != 0)
            memory -= Double.parseDouble(symbols.toString());

        System.err.println(memory);
    }


    public boolean switchOffState() {
        isOFF = !isOFF;
        MRC = false;
        memory = 0;
        calculation.setLength(0);
        symbols.setLength(0);
        return isOFF;
    }
}
