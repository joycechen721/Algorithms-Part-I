package hello;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        // use methods readString, isEmpty, println, bernoulli
        String champion = StdIn.readString();
        int count = 1;
        while (!StdIn.isEmpty()){
            String current = StdIn.readString();
            count++;
            double probability = 1.0/count;
            if (StdRandom.bernoulli(probability)){
                champion = current;
            }
        }
        StdOut.println(champion);
    }
}
