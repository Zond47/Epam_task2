package com.epam.rd.java.basic.practice2;

/**
 * interface Queue extends Container.
 */

public interface Queue extends Container {

    // Appends the specified element to the end.
    void enqueue(Object element);

    // Removes the head.
    Object dequeue();

    // Returns the head.
    Object top();

}
