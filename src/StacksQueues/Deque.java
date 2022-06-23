package StacksQueues;

import java.util.Iterator;

/*  A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and
    removing items from either the front or the back of the data structure.
    Corner Cases:
    - Throw an IllegalArgumentException if the client calls either addFirst() or addLast() with a null argument.
    - Throw a java.util.NoSuchElementException if the client calls either removeFirst() or removeLast when the deque is empty.
    - Throw a java.util.NoSuchElementException if the client calls the next() method in the iterator when there are no more items to return.
    - Throw an UnsupportedOperationException if the client calls the remove() method in the iterator.
 */
public class Deque<Item> implements Iterable<Item>{

    private Node first, last;
    private int items;

    private class Node{
        Item item;
        Node previous;
        Node next;
    }

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        items = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return items == 0;
    }

    // return the number of items on the deque
    public int size(){
        return items;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException("Cannot add null item to deque.");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        if(isEmpty()) last = first;
        else first.next = oldFirst;
        items++;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException("Cannot add null item to deque.");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        items++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque already empty, cannot remove first.");
        Item item = first.item;
        first = first.next;
        items--;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque already empty, cannot remove last.");
        Item item = last.item;
        last = last.previous;
        last.next = null;
        items--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported in deque iterator.");
        }
        public Item next(){
            if(!hasNext()) throw new java.util.NoSuchElementException("No more items to return.");
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deck = new Deque<Integer>();
        deck.addFirst(15);
        deck.addFirst(16);
        deck.addFirst(17);
        deck.addLast(17);
        deck.addFirst(18);
        deck.addLast(20);
        Iterator<Integer> i = deck.iterator();
        deck.removeLast();
        deck.removeFirst();
        deck.removeLast();
        for (Integer x : deck){
            System.out.println(x);
        }
        System.out.println(i.next());
    }

}
