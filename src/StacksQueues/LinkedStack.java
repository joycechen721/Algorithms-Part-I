package StacksQueues;

//simple generic stack implementation using linked list
public class LinkedStack<Item> {
    private Node first = null;

    //private inner class (access modifiers don't matter)
    private class Node{
        Item item;
        Node next;
    }

    //stack is empty if the first dummy node is pointing to null
    public boolean isEmpty(){
        return first == null;
    }

    public void push (Item item){
        Node oldFirst = first;
        first = new Node(); //point first to a new node object
        first.item = item; //set instance var
        first.next = oldFirst;
    }

    //return item of popped node
    public Item pop(){
        Item item = first.item;
        first = first.next;
        return item;
    }
}

/*
    Analysis:
    Every operation takes constant time in worst case.
    40 bytes per stack node. stack of size N = 40N bytes.
    Faster implementations -- using the array!
 */
