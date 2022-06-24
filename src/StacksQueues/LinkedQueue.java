package StacksQueues;

public class LinkedQueue<Item> {
    private Node first, last;

    private class Node{
        Item item;
        Node next;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public void enqueue(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;           //last node doesn't point to anything, since it's the last in queue
        if(isEmpty()) first = last; //accounting for the case of an empty queue
        else oldLast.next = last;   //the previous last node points to current last node
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if(isEmpty()) last = null;
        return item;
    }
}
