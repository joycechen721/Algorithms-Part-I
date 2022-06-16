package unionFind;
import edu.princeton.cs.algs4.*;

public class PercolationStats {
    private int trials;
    private int size;
    private double [] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        this.trials = trials;
        size = n * n;
        fractions = new double[trials];
        int numOpen = 0;
        for (int i = 0; i < trials; i++) {
            Percolation grid = new Percolation(n);
            while (!grid.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int column = StdRandom.uniform(1, n + 1);
                if (!grid.isOpen(row, column)){
                    grid.open(row, column);
                    numOpen++;
                }
            }
            fractions[i] = (double) numOpen / size;
            numOpen = 0;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        double sum = 0.0;
        for (int i = 0; i < trials; i++){
//            System.out.println(fractions[i]);
            sum += fractions[i];
        }
        return sum / trials;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double mean = this.mean();
        double sum = 0.0;
        for (int i = 0; i < trials; i++){
            double oneTrial = fractions[i];
            sum += Math.pow(oneTrial - mean, 2);
        }
        return Math.sqrt(sum / (trials - 1));
    }

//    // low endpoint of 95% confidence interval
//    public double confidenceLo(){
//
//    }
//
//    // high endpoint of 95% confidence interval
//    public double confidenceHi(){
//
//    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats test = new PercolationStats(2, 100000);
        System.out.println(test.mean());
        System.out.println(test.stddev());
    }

}
