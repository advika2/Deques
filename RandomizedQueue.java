/* *****************************************************************************
 *  Description:  Creates a randomized queue that dequeues random items in the
 *  queue and iterates randomly through the queue.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a; // array of items
    private int n; // no. of elements in RandomizedQueue


    // construct an empty randomized queue
    public RandomizedQueue() {
        // initial capacity is 2
        a = (Item[]) new Object[2];
        n = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // resize the array holding the elements
    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        // sets the copy to be the new object that holds the elements of the
        // queue
        a = copy;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException("argument is null");
        if (n == a.length) resize(2 * a.length);    // double size if necessary
        a[n++] = item;                            // add item
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) throw new NoSuchElementException("empty queue");
        if (n == a.length / 4) resize(a.length / 2); // half size if necessary
        // chooses the random index whose element to return
        int random = StdRandom.uniform(n);
        Item result = a[random];
        // adds another element to the index whose element is returned
        a[random] = a[n - 1];
        // nulls the n-1 space of the array
        a[n - 1] = null;
        // decrements no. of elements
        n--;
        return result;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) throw new NoSuchElementException("empty queue");
        int random = StdRandom.uniform(n);
        return a[random];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        // makes a copy of the RandomizedQueue
        private final Item[] copy;
        // tracks the index item that has been removed by iterator
        private int track;

        // constructor for Randomized Queue iterator
        public RandomIterator() {
            copy = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                copy[i] = a[i];
            }
            StdRandom.shuffle(copy);
            track = 0;
        }

        public boolean hasNext() {
            return track != n;
        }

        public Item next() {
            if (track == n) throw new NoSuchElementException("empty queue");
            return copy[track++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        test.enqueue("hi");
        // "hi"
        StdOut.println(test.dequeue());
        test.enqueue("my");
        test.enqueue("name");
        test.enqueue("is");
        StdOut.println(test.dequeue());
        StdOut.println(test.sample());
        // "false"
        StdOut.println(test.isEmpty());
        // "2"
        StdOut.println(test.size());
        for (String str : test) {
            StdOut.println(str);
        }
    }
}
