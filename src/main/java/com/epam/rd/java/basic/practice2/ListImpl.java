package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Storage of the objects inside the container should be implemented by
 * using set of nodes - instances of the Node class.
 */
public class ListImpl implements List {
    private Node head;
    private Node tail;
    private int size;

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node {
        private Object value;
        private Node next;

        public Node(Object value) {
            this.value = value;
        }
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        Node current = head;
        Object value;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                value = current.value;
                current = current.next;
                return value;
            } else {
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            ListImpl.this.remove(value);
        }
    }

    @Override
    public void addFirst(Object element) {
        Node firstNode = new Node(element);
        firstNode.next = head;
        if (size == 0) {
            tail = firstNode;
        }
        head = firstNode;
        size++;
    }

    @Override
    public void addLast(Object element) {
        Node newNode = new Node(element);
        if (size == 0) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void removeFirst() {
        head = head.next;
        size--;
    }

    @Override
    public void removeLast() {
        Node previous = getByIndex(size - 2);
        previous.next = null;
        tail = previous;
        size--;
    }

    @Override
    public Object getFirst() {
        ListImpl.Node firstNode = head;
        if (firstNode == null) {
            return null;
        }
        return firstNode.value;
    }

    @Override
    public Object getLast() {
        ListImpl.Node lastNode = tail;
        if (lastNode == null) {
            return null;
        }
        return lastNode.value;
    }

    private Node getByIndex(int index) {
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    @Override
    public Object search(Object element) {
        Node currentNode = head;
        for (int i = 0; i < size; i++) {
            if (currentNode.value != null && currentNode.value.equals(element)) {
                return element;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (head == null) {
            return false;
        }

        if (head.value.equals(element)) {
            removeFirst();
            return true;
        }

        Node currentNode = head;
        while (currentNode.next != element) {
            if (currentNode.next == null) {
                return false;
            } else if ((element == null && currentNode.next.value == element) || currentNode.next.value == element) {
                currentNode.next = currentNode.next.next;
                if (element == tail.value) {
                    tail = currentNode;
                }
                size--;
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    @Override
    public String toString() {
        Iterator<Object> it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }

        Node currentNode = head;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(currentNode.value);
            if (i != size - 1) {
                sb.append(", ");
            }
            currentNode = currentNode.next;
        }
        return "[" + sb.toString().trim() + ']';
    }

    public static void main(String[] args) {
        ListImpl linkedList = new ListImpl();
        linkedList.addFirst("Q");
        linkedList.addLast("A");
        linkedList.addLast("B");
        linkedList.addLast(null);
        linkedList.remove(null);
        linkedList.clear();
        linkedList.toString();
        linkedList.remove(null);
        for (Object item : linkedList) {
            System.out.print(item);
        }
        linkedList.size();
        linkedList.remove("S");
        linkedList.getByIndex(1);
        linkedList.removeFirst();
        linkedList.removeLast();
        linkedList.clear();
        linkedList.getFirst();
        linkedList.getLast();
    }
}
