package unionFind;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trials;
    private final double [] fractions;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException("Invalid arguments.");
        this.trials = trials;
        int size = n * n;
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
        return StdStats.mean(fractions);
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(fractions);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(trials);
    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats test = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + test.mean());
        System.out.println("stddev = " + test.stddev());
        System.out.println("95% confidence interval = [" +
                test.confidenceLo() + ", " + test.confidenceHi() + "]");
    }

}
