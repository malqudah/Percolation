/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description:  Using the nxn Percolation system model, performs a series of
 *                trials using the given grid dimensions and calculates the
 *                mean, standard deviation, confidencelow and confidence high
 *
 **************************************************************************** */


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {

    // magic number 1.96 for use in confidence intervals
    private static final double CONFIDENCE_95 = 1.96;

    // instead of using trials + allows it to be used in other methods
    private final int t;

    // array for the fraction of open sites over n squared; used in mean
    // and stddev
    private final double[] threshold;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        t = trials;
        threshold = new double[t];
        for (int i = 0; i < t; i++) {
            Percolation testOne = new Percolation(n);
            while (!testOne.percolates()) {
                testOne.open(StdRandom.uniform(n),
                             StdRandom.uniform(n));
            }
            threshold[i] = (testOne.numberOfOpenSites() / Math.pow(n, 2));
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - ((CONFIDENCE_95 * stddev()) / (Math.sqrt(t)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + ((CONFIDENCE_95 * stddev()) / (Math.sqrt(t)));
    }

    // test client (see below)
    public static void main(String[] args) {
        Stopwatch stopOne = new Stopwatch();
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats testTwo = new PercolationStats(n, trials);
        StdOut.println("mean() = " + testTwo.mean());
        StdOut.println("stddev() = " + testTwo.stddev());
        StdOut.println("confidenceLow() = " + testTwo.confidenceHigh());
        StdOut.println("confidenceHigh() = " + testTwo.confidenceLow());
        StdOut.println("elapsed time = " + stopOne.elapsedTime());
    }
}
