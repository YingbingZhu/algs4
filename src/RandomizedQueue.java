import java.util.Iterator;

/**
 * similar to a stack or queue,
 * except that the item removed is chosen uniformly at random among items in the data structure.
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Item[] array;


    // construct an empty randomized queue
    public RandomizedQueue() {

        size = 0;
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

    }

    // remove and return a random item
    public Item dequeue() {
        return Node.item;
    }

    // return a random item (but do not remove it)
    public Item sample() {

    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {


        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Item next() {
            return null;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {

    }

}