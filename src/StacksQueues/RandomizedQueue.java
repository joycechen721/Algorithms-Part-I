package StacksQueues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

/*
    A randomized queue is similar to a stack or queue, except that the item removed
    is chosen uniformly at random among items in the data structure.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item [] queue;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue(){
        queue = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    private void resize(int capacity){
        Item [] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < capacity; i++){
            copy[i] = queue[i];
        }
        queue = copy;
    }

    // add the item
    public void enqueue(Item item){
        if (item == null) throw new IllegalArgumentException("Cannot add null item to deque.");
        if (size == queue.length) resize(2 * queue.length);
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque already empty, cannot dequeue.");
        int randomIndex = StdRandom.uniform(size);
        Item random = queue[randomIndex];
        for(int i = randomIndex; i < size - 1; i++){
            queue[i] = queue[i + 1];
        }
        queue[--size] = null;
        return random;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque already empty.");
        return queue[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private final Item [] array = shuffle(queue);
        private int index = 0;

        public Item[] shuffle(Item [] anArray){
            System.arraycopy(anArray, size, array, 0, size - 1);
            StdRandom.shuffle(array);
            return array;
        }
        public boolean hasNext(){
            return index != size - 1;
        }
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported in deque iterator.");
        }
        public Item next(){
            if(!hasNext()) throw new java.util.NoSuchElementException("No more items to return in queue.");
            Item item = array[index];
            index++;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args){

    }

}
