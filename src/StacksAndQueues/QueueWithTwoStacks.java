package StacksAndQueues;

public class QueueWithTwoStacks<Item> {

    Stack<Item> inStack = new Stack<Item>();
    Stack<Item> outStack = new Stack<Item>();


    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public void enqueue(Item item) {
        inStack.push(item);
    }

    public Item dequeue() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();
    }

}

