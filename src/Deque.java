import StacksAndQueues.LinkedQueueOfStrings;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * A double-ended queue or deque
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node {
        Item item;
        Node next;
    }

    // construct an empty deque
    public Deque() {


    }

    // is the deque empty?
    public boolean isEmpty() {

    }

    // return the number of items on the deque
    public int size() {

    }

    // add the item to the front
    public void addFirst(Item item) throws IllegalArgumentException {

    }

    // add the item to the back
    public void addLast(Item item) throws IllegalArgumentException {

    }

    // remove and return the item from the front
    public Item removeFirst() throws NoSuchElementException {

    }

    // remove and return the item from the back
    public Item removeLast() throws NoSuchElementException {

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

}