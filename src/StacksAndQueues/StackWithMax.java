package StacksAndQueues;

import java.util.TreeSet;

public class StackWithMax<Item> extends Stack<Item> {

    private TreeSet<Item> treeSet = new TreeSet<Item>();

    public void push(Item item) {
        super.push(item);
        treeSet.add(item);
    }

    public Item pop() {
        return super.pop();
    }

    public Item max() {
        return treeSet.last();
    }

}
