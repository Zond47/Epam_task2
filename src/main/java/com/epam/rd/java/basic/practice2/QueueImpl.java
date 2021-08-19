package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class QueueImpl implements Queue.
 */

public class QueueImpl implements Queue {
    private Object[] queueItems;
    private int head;
    private int tail;
    private int sizeQueue;
    private int queueCapacity;

    public QueueImpl() {
        this.queueCapacity = 10;
        this.queueItems = new Object[queueCapacity];
        this.head = 0;
        tail = -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < sizeQueue; i++) {
            queueItems[i] = null;
        }
        sizeQueue = 0;
        head = 0;
        tail = 0;
    }

    @Override
    public int size() {
        return sizeQueue;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        int currentItem = head;
        int lastItem = -1;

        @Override
        public boolean hasNext() {
            return currentItem < sizeQueue;
        }

        @Override
        public Object next() {
            int i = currentItem;
            if (i >= sizeQueue) {
                throw new NoSuchElementException();
            }
            Object[] items = QueueImpl.this.queueItems;

            currentItem = i + 1;
            lastItem = i;
            return items[lastItem];
        }

        @Override
        public void remove() {
            QueueImpl.this.dequeue();
        }
    }

    @Override
    public void enqueue(Object element) {
        if (sizeQueue == queueCapacity) {
            queueCapacity = (queueCapacity * 2) + 1;
            Object[] tmpItems = new Object[queueCapacity];
            System.arraycopy(queueItems, 0, tmpItems, 0, sizeQueue);
            queueItems = tmpItems;
        }
        tail = (tail + 1) % queueCapacity;
        queueItems[tail] = element;
        sizeQueue++;
    }

    @Override
    public Object dequeue() {
        Object beheaded = top();
        Object[] tmpData = new Object[queueCapacity];
        System.arraycopy(queueItems, 1, tmpData, 0, sizeQueue);
        queueItems = tmpData;
        tail--;
        sizeQueue--;
        return beheaded;
    }

    @Override
    public Object top() {
        return queueItems[0];
    }

    @Override
    public String toString() {
        Iterator<Object> it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }

        StringBuilder out = new StringBuilder();
        out.append('[');
        for (; ; ) {
            Object e = it.next();
            out.append(e);
            if (!it.hasNext()) {
                return out.append(']').toString();
            }
            out.append(',').append(' ');
        }
    }

    public static void main(String[] args) {
        QueueImpl queue = new QueueImpl();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue(null);
        queue.top();
        queue.toString();
        for (Object item : queue) {
            System.out.print(item);
        }
        queue.dequeue();
        queue.clear();
    }
}

