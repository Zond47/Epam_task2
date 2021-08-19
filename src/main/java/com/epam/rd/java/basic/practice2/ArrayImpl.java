package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArrayImpl class that implements the Array interface.
 */
public class ArrayImpl implements Array {
    private int size;
    private int capacity;
    private Object[] dataArray;

    public ArrayImpl(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.dataArray = new Object[capacity];
    }

    public ArrayImpl() {
        this(10);
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            dataArray[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int currentElement;
        int lastElement = -1;

        @Override
        public boolean hasNext() {
            return currentElement < size;
        }

        @Override
        public Object next() {
            int i = currentElement;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] tmpData = ArrayImpl.this.dataArray;

            currentElement = i + 1;
            lastElement = i;
            return tmpData[lastElement];
        }

        @Override
        public void remove() {
            ArrayImpl.this.remove(lastElement);
            currentElement = lastElement;
            lastElement = -1;
        }
    }

    @Override
    public void add(Object element) {
        if (size == capacity) {
            capacity = (capacity * 3) / 2 + 1;
            Object[] tmpData = new Object[capacity];
            System.arraycopy(dataArray, 0, tmpData, 0, size);
            dataArray = tmpData;
        }
        dataArray[size++] = element;
    }

    @Override
    public void set(int index, Object element) {
        if (index >= 0 && index < size) {
            dataArray[index] = element;
        }
    }

    @Override
    public Object get(int index) {
        return dataArray[index];
    }

    @Override
    public int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (dataArray[i] != null && dataArray[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(dataArray, index + 1, dataArray, index, numMoved);
        }
        dataArray[--size] = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (int i = 0; i < size; i++) {
            sb.append(dataArray[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.append(']').toString();
    }

    public static void main(String[] args) {
        ArrayImpl arrayList = new ArrayImpl();
        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");
        arrayList.toString();
        arrayList.size();
        arrayList.indexOf("A");
        arrayList.set(2, "D");
        arrayList.get(2);
        arrayList.remove(2);
        arrayList.clear();
    }
}
