package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * StackImpl class that implements the Stack interface.
 */

public class StackImpl implements Stack {
    private Object[] stackItems;
    private int top;
    private int capacityStack;

    public StackImpl(int size) {
        this.stackItems = new Object[size];
        this.capacityStack = size;
        this.top = -1;
    }

    public StackImpl() {
        this(0);
    }

    @Override
    public void clear() {
        for (int i = 0; i <= top; i++) {
            stackItems[i] = null;
        }
        capacityStack = 0;
        top = 0;

    }

    @Override
    public int size() {
        return capacityStack;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int cursor = top;
        int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor >= 0;
        }

        @Override
        public Object next() {
            int i = cursor;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            Object[] items = StackImpl.this.stackItems;

            cursor = i - 1;
            lastRet = i;
            return items[lastRet];
        }

        @Override
        public void remove() {
            StackImpl.this.pop();
        }
    }

    @Override
    public void push(Object element) {
        if (top == capacityStack - 1) {
            capacityStack++;
            Object[] tmpItems = new Object[capacityStack];
            System.arraycopy(stackItems, 0, tmpItems, 0, stackItems.length);
            stackItems = tmpItems;
        }
        stackItems[++top] = element;
    }

    @Override
    public Object pop() {
        if (top == 0 || top == -1) {
            return null;
        }
        Object returned = stackItems[top];
        stackItems[top] = null;
        Object[] tmpItems = new Object[top];
        System.arraycopy(stackItems, 0, tmpItems, 0, top);
        top--;
        capacityStack--;
        stackItems = tmpItems;
        return returned;
    }

    @Override
    public Object top() {
        if (top == 0 || top == -1) {
            return null;
        }
        return stackItems[top];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (capacityStack == 0) {
            return "[]";
        }
        sb.append('[');

        for (int i = 0; i <= top; i++) {
            sb.append(stackItems[i]);
            if (i != top) {
                sb.append(", ");
            }
        }
        return sb.append(']').toString();
    }


    public static void main(String[] args) {
        StackImpl stack = new StackImpl();
        stack.push("A");
        stack.push("B");
        stack.push("C");
        stack.push("D");
        stack.pop();
        stack.size();
        stack.clear();

    }
}
