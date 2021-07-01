/* *****************************************************************************
 *  Description:  Creates a deque that fits the FIFO and LIFO description at the
 * same time.
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // front of the deque
    private Node first;
    // back of the deque
    private Node last;
    // no. of elements on stack
    private int n;

    private class Node {
        // to create a doubly linked list of items
        private Item item;
        // to make rightward connections
        private Node next;
        // to make leftward connections
        private Node prev;
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("argument is null");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null) {
            oldFirst.prev = first;
        }
        else last = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("argument is null");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        if (oldLast != null) {
            oldLast.next = last;
            last.prev = oldLast;
        }
        else {
            first = last;
        }
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        // condition if n is 0
        if (first == null) {
            throw new NoSuchElementException("deque is empty");
        }
        Item item = first.item;
        // condition if n is 1
        if (first.next == null) {
            first = null;
            last = null;
            n--;
            return item;
        }
        // condition if n is 2
        if (first.next.next == null) {
            first = first.next;
            last = first;
            first.prev = null;
            n--;
            return item;
        }
        Node temp = first.next.next;
        first = first.next;
        temp.prev = first;
        first.prev = null;
        n--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (last == null) throw new NoSuchElementException("deque is empty");
        Item item = last.item;
        // condition if n is 1
        if (last.prev == null) {
            last = null;
            first = null;
            n--;
            return item;
        }
        // condition if n is 2
        if (last.prev.prev == null) {
            last = last.prev;
            last.next = null;
            n--;
            return item;
        }
        Node temp = last.prev.prev;
        last = last.prev;
        temp.next = last;
        last.next = null;
        n--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        // tracks which Node we are returning in the next() method
        private Node current;

        // initializes Iterator instance variables
        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item temp = current.item;
            current = current.next;
            return temp;
        }
    }

    public static void main(String[] args) {
        Deque<String> test = new Deque<String>();
        test.addLast("hi");
        // "hi"
        StdOut.println("item removed first = " + test.removeFirst());
        test.addFirst("my");
        test.addFirst("name");
        test.addFirst("is");
        test.addLast("Ada");
        // "is"
        StdOut.println("item removed first = " + test.removeFirst());
        // "Ada"
        StdOut.println("item removed last = " + test.removeLast());
        // "2"
        StdOut.println("size = " + test.size());
        // "name
        // my"
        for (String str : test) {
            StdOut.println(str);
        }
        // "false"
        StdOut.println("Is it empty = " + test.isEmpty());

    }
}
