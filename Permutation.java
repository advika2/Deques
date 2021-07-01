/* *****************************************************************************
 *  Name:    Advika Srivastava
 *  NetID:   advikas
 *  Precept: P10
 *
 *  Description: client for Randomized Queue.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        // creates RandomizedQueue to store the elements
        RandomizedQueue<String> permutation = new RandomizedQueue<String>();
        // enqueues elements into the Randomized Queue
        while (!StdIn.isEmpty()) {
            permutation.enqueue(StdIn.readString());
        }
        // dequeues and print k elements
        for (int i = 0; i < k; i++) {
            String toPrint = permutation.dequeue();
            StdOut.println(toPrint);
        }
    }
}
