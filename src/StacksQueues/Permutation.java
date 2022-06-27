package StacksQueues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String [] args){
        RandomizedQueue <String> queue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            queue.enqueue(word);
        }
        for (int i = 0; i < Integer.parseInt(args[0]); i++){
            StdOut.println(queue.sample());
        }
    }
}
