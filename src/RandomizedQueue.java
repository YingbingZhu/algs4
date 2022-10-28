import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * similar to a stack or queue,
 * except that the item removed is chosen uniformly at random among items in the data structure.
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] items;


    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
        items = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Can't add empty element to queue");
        }
        if (size == items.length) {
            resizeItems( 2 * size);
        }
        items[size] = item;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty queue");
        }
        int index = StdRandom.uniformInt(size);
        Item item = items[index];
        // replace item with last item
        items[index] = items[size-1];
        items[size-1] = null;
        size --;
        // resize
        if (size > 0 && (size == items.length/4)) {
            resizeItems(items.length/2);
        }

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("empty");
        }
        int index = StdRandom.uniformInt(size);
        return items[index];
    }

    private void resizeItems(int newSize) {
        Item[] copy = (Item[]) new Object[newSize];

        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    /*
     return an independent iterator over items in random order
     Each iterator must return the items in uniformly random order.
     The order of two or more iterators to the same randomized queue must be mutually independent;
     each iterator must maintain its own random order.
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int sizeCopy;
        private Item[] copy;

        private RandomizedQueueIterator() {
            sizeCopy = size;
            copy = (Item[]) new Object[sizeCopy];
            for (int i = 0; i < sizeCopy; i++) {
                copy[i] = items[i];
            }
        }

        @Override
        public boolean hasNext() {
            return sizeCopy > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("empty");
            }
            int index = StdRandom.uniformInt(sizeCopy);
            Item item = copy[index];
            copy[index] = copy[sizeCopy - 1];
            copy[sizeCopy - 1] = null;
            sizeCopy -- ;

            return item;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        String text = "A";
        queue.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");
        System.out.println(queue.size);
        for (String s:queue) {
            System.out.println(s);
        }

        text = "B";
        queue.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "C";
        queue.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "D";
        queue.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        text = "E";
        queue.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");

        StdOut.println("dequeue()" + queue.dequeue());

        StdOut.println("sample()" + queue.sample());

        text = "F";
        queue.enqueue(text);
        StdOut.println("enqueue() with: '" + text + "'");


        StdOut.println("Iterating queue...");
        for (String item: queue) {
            StdOut.println("Iterate element: " + item);
        }
    }

}