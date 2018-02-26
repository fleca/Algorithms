import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {

    private double mean;
    private double stddev;
    private int arguments;
    private int[] totalOpenedSites;
    private double totalTrials;

    public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
    {
        if (n < 0 || trials < 0) {
            throw new IllegalArgumentException();
        }
        int row;
        int col;
        arguments = n;
        totalTrials = trials;
        totalOpenedSites = new int[trials];
        for (int i = 0; i < trials; i++){
            int openSite = 0;
            Percolation percolation = new Percolation(n);
       //     while (percolation.percolates() != true){
        //        openSite = StdRandom.uniform(n*n);
         //       row = (openSite-(openSite%n))/n + 1;
          //      col = (openSite%n) + 1;
            //    if(!percolation.isOpen(row,col))
            //        percolation.open(row, col);
           // }
            totalOpenedSites[i] = percolation.numberOfOpenSites();
        }
//        System.out.println("Mean                    = " + mean());
//        System.out.println("stddev                  = " + stddev());
//        System.out.println("95% confidence interval = [" + confidenceLo() + ", " + confidenceHi() + "]");
//        mean();
//        stddev();
//        confidenceLo();
//        confidenceHi();
    }
    public double mean()                          // sample mean of percolation threshold
    {
        mean = StdStats.mean(totalOpenedSites);
        mean = mean/(arguments*arguments);
        return mean;
    }
    public double stddev()                        // sample standard deviation of percolation threshold
    {
        stddev = StdStats.stddev(totalOpenedSites);
        stddev = stddev / (arguments * arguments);
        return stddev;
    }
    public double confidenceLo()                  // low  endpoint of 95% confidence interval
    {
        return mean - (1.96*stddev)/Math.sqrt(totalTrials);
    }
    public double confidenceHi()                  // high endpoint of 95% confidence interval
    {
        return mean + (1.96*stddev)/Math.sqrt(totalTrials);
    }

    public static void main(String[] args)        // test client (described below)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats perco = new PercolationStats(n, trials);
    }
}
