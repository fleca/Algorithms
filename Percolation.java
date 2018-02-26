import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{

    private int[] sites;
    private int totalSites;
    private int[] percolation;
    private int[] sz;
    private int virtualTopSite;
    private int virtualBottomSite;
    private int n;
    private int totalOpenSities;

    public Percolation(int nGrid)                // create n-by-n grid, with all sites blocked
    {
        if (n <= 0) {
            return;
 //           throw new IllegalArgumentException();
        }

        n = nGrid;
        totalSites = n*n;
        totalOpenSities = 0;
        virtualTopSite = n*n;
        virtualBottomSite = n*n+1;
        sites = new int[totalSites];
        percolation = new int[totalSites+2];
        sz = new int[totalSites+2];
        percolation[virtualTopSite] = virtualTopSite;
        percolation[virtualBottomSite] = virtualBottomSite;
        sz[virtualBottomSite] = virtualBottomSite;
        sz[virtualTopSite] = virtualTopSite;
        for (int i = 0; i < n*n; i++){
            sites[i] = 0;
            percolation[i] = i;
            sz[i] = 1;
        }

    }
    public void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row > n || col > n || row <= 0 || col <= 0) {
      //      throw new IllegalArgumentException();
            return;
        }
        int site = (n*(row-1) + col -1);
        if(sites[site] == 1)
            return;

        sites[site] = 1;
        totalOpenSities++;
        numberOfOpenSites();

        if (site < n){          // checks if the site is in the first row and unites with virtual top site
            union(site,virtualTopSite);
            if (isOpen(row + 1,col) == true && connected(site,site + n) == false){
                union(site,site + n);
            }
        }
        else{
            if (isOpen(row - 1, col) && connected(site, site - n) == false)
                union(site, site - n);
        }

        if (site + n >= totalSites){ // checks if the site is in the last row and unites with virtual bottom site
            union(site,virtualBottomSite);
        }
        else{
            if (isOpen(row + 1, col))
                union(site, site + n);
        }

        if ((site+1)%n != 0){ // not right
            if (isOpen(row, col + 1))
                union(site, site + 1);
        }

        if ((site+n)%n != 0){ //not left
            if (isOpen(row, col - 1))
                union(site, site - 1);
        }
    }
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
        if (row > n || col > n || row <= 0 || col <= 0) {

            throw new IllegalArgumentException();
        }
        int site = (n*(row-1) + col -1);
        if (sites[site] == 1)
            return true;
        else
            return false;
    }
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
        if (row > n || col > n || row <= 0 || col <= 0) {
            throw new IllegalArgumentException();
        }
        int site = (n*(row-1) + col -1);
        if (connected(site,virtualTopSite) == true && isOpen(row,col))
            return true;
        return false;
    }
    public int numberOfOpenSites()       // number of open sites
    {
        return totalOpenSities;
    }
    public boolean percolates()              // does the system percolate?
    {
        if (connected(virtualTopSite,virtualBottomSite)){
            return true;
        }
        else
            return false;
    }

    private int root(int i){
        while (i != percolation[i]) {
            percolation[i] = percolation[percolation[i]]; //path compression
            i = percolation[i];
        }
        return i;
    }

    private boolean connected(int p, int q){
        return root(p) == root (q);
    }

    private void union(int p, int q){
        int i = root(p);
        int j = root(q);
        if (i == j) return;
        if (sz[i] < sz[j]){ //weighted QU
            percolation[i] = j;
            sz[j] += sz[i];
        }
        else{
            percolation[j] = i;
            sz[i] += sz[j];
        }
    }

    public static void main(String[] args) // test client (optional)
    {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats perco = new PercolationStats(n, trials);
    }
}
