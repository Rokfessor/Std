package Lesson4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.lang.StringBuilder;

class Main {
    public static void main(String[] args) {
        String infix = "1 * ( 12.2 + 23 ) - ( 4 / 5 )";
        String postfix = convert(infix);
        System.err.println("Инфиксная: " + infix);
        System.err.println("Постфиксная: " + postfix);
        System.err.println("Результат: " + calculate(postfix));
    }

    public static double calculate(String ex) {
        ArrayList<String> chars = new ArrayList<>(Arrays.asList(ex.split(" ")));
        int i = 0;
        while (i < chars.size()) {
            if (chars.get(i).equals(""))
                continue;
            try {
                Double.parseDouble(chars.get(i));
                i++;
            } catch (NumberFormatException e) {
                chars.set(i - 2, String.valueOf(getRes(Double.parseDouble(chars.get(i - 2)), Double.parseDouble(chars.get(i - 1)), chars.get(i).charAt(0))));
                chars.remove(i);
                chars.remove(i - 1);
                i = 0;
            }
        }
        return Double.parseDouble(chars.get(0));
    }

    public static double getRes(double a, double b, char sign) {
        return switch (sign) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '/' -> a / b;
            case '*' -> a * b;
            default -> 0;
        };
    }

    public static String convert(String ex) {
        StringBuilder sb = new StringBuilder();

        Stack<Character> op = new Stack<>();
        char[] chars = ex.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (Character.isDigit(ch)) {
                while (Character.isDigit(chars[i]) || chars[i] == '.') {
                    sb.append(chars[i++]);
                }
                sb.append(' ');
            } else if (ch == '(') {
                op.push(ch);
            } else if (ch == ')') {
                while (op.peek() != '(') {
                    sb.append(op.pop()).append(' ');
                }
                op.pop();
            } else if (isOperator(ch)) {
                while (!op.isEmpty() && priority(op.peek()) >= priority(ch)) {
                    sb.append(op.pop()).append(' ');
                }
                op.push(ch);
            }
        }
        while (!op.isEmpty()) {
            sb.append(op.pop()).append(' ');
        }

        return sb.toString();
    }

    private static boolean isOperator(char ch) {
        return ch == '^' || ch == '*' || ch == '/' || ch == '+' || ch == '-';
    }

    private static int priority(char operator) {
        return switch (operator) {
            case '^' -> 3;
            case '*', '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }
}
