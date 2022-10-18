
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private final double[] thresholds;
    private final double CONFIDENCE_95 = 1.96;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("invalid n or trials");
        }

        thresholds = new double[trials];

        for (int i = 0; i < trials; i ++) {
            Percolation percolation = new Percolation(n);
            // Choose a site uniformly at random among all blocked sites.
            while (!percolation.percolates()) {
                int row = StdRandom.uniformInt(1, n+1); // [)
                int col = StdRandom.uniformInt(1, n+1); // [)

                // Open the site.
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                }
            }
            thresholds[i] = (double) percolation.numberOfOpenSites() / n / n;
        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - CONFIDENCE_95 * this.stddev() / (Math.sqrt((double) this.thresholds.length));
    };

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + CONFIDENCE_95 * this.stddev() / (Math.sqrt((double) this.thresholds.length));
    };

    // test client (see below)
    // takes two command-line arguments n and T,
    // performs T independent computational experiments (discussed above) on an n-by-n grid,
    // and prints the sample mean, sample standard deviation, and the 95% confidence interval for the percolation threshold
    public static void main(String[] args){
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, T);
        double x = stats.mean();
        double s = stats.stddev();
        double low = stats.confidenceLo();
        double hi = stats.confidenceHi();
        System.out.printf("mean=%f\n", x);
        System.out.printf("stddev=%f\n", s);
        System.out.printf("%f %f\n", low, hi);
    }

}
