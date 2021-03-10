package Lesson3;

import java.util.EmptyStackException;

public class Main {
    public static void main(String[] args) {
        String rightString = "f";
        boolean tmp = true;
        Stack<Character> stack = new Lesson3.Stack<>();

        for (int i = 0; i < rightString.length(); i++) {
            char c = rightString.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                try {
                    if ((stack.top() == '{' && c == '}') ||
                            (stack.top() == '[' && c == ']') ||
                            (stack.top() == '(' && c == ')')) {
                        stack.pop();
                    } else {
                        tmp = false;
                    }
                }catch (EmptyStackException e){
                    tmp = false;
                }
            }
        }

        if (tmp && stack.isEmpty()) {
            System.err.println("Расстановка скобок правильная");
        } else {
            System.err.println("Расстановка скобок неправильная");
        }
    }
}
