
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        Node prev;
    }

    // construct an empty deque
    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return this.first == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) throws IllegalArgumentException {
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        first = newNode;
        if (isEmpty()) {
            // if empty, last should point to new node
            last = newNode;
        } else {
            first.prev = newNode;
        }
        size ++;
    }

    // add the item to the back
    public void addLast(Item item) throws IllegalArgumentException {
        Node newNode = new Node();
        newNode.item = item;
        last = newNode;
        newNode.prev = last;
        if (isEmpty()) {
            // if empty, first should point to last
            first = newNode;
        } else {
            last.next = newNode;
        }
        size ++;
    }

    // remove and return the item from the front
    public Item removeFirst() throws NoSuchElementException {
        Node node = first;
        // only one node, also set last to null
        if (first.next == null) {
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return node.item;
    }

    // remove and return the item from the back
    public Item removeLast() throws NoSuchElementException {
        Node node = last;
        // only one node, also set first to null
        if (last.prev == null) {
            first = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return node.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() throws NoSuchElementException {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Object> deque = new Deque<>();
        System.out.println(deque.isEmpty());
        System.out.println(deque.size());
        deque.addFirst(2);
        deque.addFirst("S");
        deque.addLast(3);
        System.out.println(deque.size());
        System.out.println(deque.removeFirst());
        System.out.println(deque.size());
        System.out.println(deque.removeLast());
        System.out.println(deque.size());
        deque.addFirst(5);
        deque.addFirst("S");

        // iterator
        for (Object o : deque) {
            System.out.print(o + " ");
        }

    }

}