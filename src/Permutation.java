import edu.princeton.cs.algs4.StdIn;

/*
that takes an integer k as a command-line argument;
reads a sequence of strings from standard input using StdIn.readString();
and prints exactly k of them, uniformly at random.
Print each item from the sequence at most once.
 */
public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> r = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            r.enqueue(s);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(r.sample());
        }
    }
}
