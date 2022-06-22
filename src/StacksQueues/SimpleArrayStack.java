package StacksQueues;

public class SimpleArrayStack {
    private String [] s;
    private int N = 0; //# of objects in stack

    //defect: what if # of objects > stack capacity (array size)?
    public SimpleArrayStack (int capacity){
         s = new String[capacity];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void push (String item){
        s[N++] = item;          //first index into array, then increment N
    }

    public String pop(){
        String item = s[--N];   //first decrement N, then index into array to retrieve String content
        s[N] = null;      //avoids "loitering" by removing String content completely, efficient use of memory
        return item;
    }

    /*
        Analysis:
        Need overflow (resizing array) & underflow (pop from empty stack?) exceptions.
        Can insert null items in this implementation.
        Ideally should avoid loitering, or holding a reference to an object even when it's no longer needed
     */
}
