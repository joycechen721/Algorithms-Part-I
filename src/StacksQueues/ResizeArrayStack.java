package StacksQueues;

public class ResizeArrayStack {
    private String [] s;
    private int N = 0;

    public ResizeArrayStack (){
        s = new String[1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public void resize (int capacity){
        String [] copy = new String [capacity];
        for (int i = 0; i < N; i++){
            copy[i] = s[i];
        }
        s = copy;
    }

    public void push (String item){
        if (N == s.length) resize(2 * s.length);
        s[N++] = item;
    }

    public String pop(){
        String item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length/4) resize(s.length/2);
        return item;
    }
}


/*
    Analysis:
    Resizing
        - grow: when array is full, create a new array of twice the size (repeated doubling), then copy items into it.
        - shrink: when array is 1/4 full, create a new array half the size to avoid "thrashing"
    Complexity - N (constant amortized time) and not N^2, since array doubles with each growth
    Memory for N items (stack itself, not counting strings) - ranging 8N when 1/4 full, 32N when full
    Less wasted space compared to linked-list implementation. But linked lists guarantee constant time.
 */