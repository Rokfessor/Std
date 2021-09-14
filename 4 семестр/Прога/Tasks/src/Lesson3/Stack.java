package Lesson3;

import java.util.EmptyStackException;

public class Stack<E> implements Cloneable {
    private StackElement<E> last = null;
    private int len = 0;

    public E top() {
        if (last == null)
            throw new EmptyStackException();

        return last.data;
    }

    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();

        E data = last.data;
        last = last.previous;
        len--;
        return data;
    }

    public void push(E data) {
        StackElement<E> element = new Lesson3.StackElement<>();
        element.data = data;

        if (last != null) {
            element.previous = last;
        }
        last = element;
        len++;
    }

    public boolean isEmpty() {
        return len <= 0;
    }
}
