

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private final int n;
    private WeightedQuickUnionUF grid;
    private WeightedQuickUnionUF full;
    private boolean[] status;
    private int top;
    private int bottom;
    private int openSites;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("invalid n");
        }
        this.n = n;
        // two node for virtual top and bottom
        this.grid = new WeightedQuickUnionUF(n * n + 2);
        // one node for virtual top
        this.full = new WeightedQuickUnionUF(n * n + 1);

        // virtual top and bottom index
        this.top = n * n;
        this.bottom = n * n + 1;
        this.openSites = 0;

        this.status = new boolean[n * n];
        for (int i = 0; i < n * n; i ++) {
            status[i] = false;
        }

    }

    private void boundCheck(int row, int col) {
        if (row <= 0 || col <= 0 || row > this.n || col > this.n) {
            throw new IllegalArgumentException("invalid index");
        }
    }

    /**
     * get index in the status array
     * @param row
     * @param col
     * @return
     */
    private int getIndex(int row, int col) {
        boundCheck(row, col);
        return this.n * (row - 1) + col - 1;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        boundCheck(row, col);
        if (isOpen(row, col)) {
            return;
        }

        // open
        int idx = getIndex(row, col);
        status[idx] = true;
        openSites ++;
        // corner case
        if (row == 1) {
            this.grid.union(idx, this.top);
            this.full.union(idx, this.top);
        }
        if (row == this.n) {
            this.grid.union(idx, this.bottom);
        }

        // union with up
        int up = idx - this.n;
        if ((row - 1) > 0 && this.status[up]) {
            this.grid.union(idx, up);
            this.full.union(idx, up);
        }
        // union with left
        int left = idx - 1;
        if ((col - 1) > 0 && this.status[left]) {
            this.grid.union(idx, left);
            this.full.union(idx, left);
        }
        // union with right
        int right = idx + 1;
        if ((col + 1) <= this.n && this.status[right]) {
            this.grid.union(idx, right);
            this.full.union(idx, right);
        }
        // union with down
        int down = idx + this.n;
        if ((row + 1) <= this.n && this.status[down]) {
            this.grid.union(idx, down);
            this.full.union(idx, down);
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        boundCheck(row, col);
        int idx = getIndex(row, col);
        return this.status[idx];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        boundCheck(row, col);
        int idx = getIndex(row, col);
        return this.full.find(idx) == this.full.find(this.top);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.grid.find(this.top) == this.grid.find(this.bottom);
    }

    // test client (optional)
    public static void main(String[] args) {
        try {
            Percolation system = new Percolation(4);
            System.out.println(system.full.count());
            System.out.println(system.grid.count());
            System.out.println("open site Row 2 Col 2");
            system.open(2, 2);
            System.out.println("Opened Sites: " + system.numberOfOpenSites());

            if (system.isFull(2, 2)) {
                System.out.println("(error) Row 2 Col 2 is full site");
            } else {
                System.out.println("(expected) Row 2 Col 2 is not full site");
            }

            System.out.println("open site Row 1 Col 2");
            system.open(1, 2);
            System.out.println("Opened Sites: " + system.numberOfOpenSites());

            if (system.isFull(2, 2)) {
                System.out.println("(expected) Row 2 Col 2 is full site");
            } else {
                System.out.println("(error) Row 2 Col 2 is not full site");
            }

            System.out.println("open site Row 4 Col 3");
            system.open(4, 3);
            System.out.println("Opened Sites: " + system.numberOfOpenSites());

            if (system.isFull(4, 3)) {
                System.out.println("(error) Row 4 Col 3 is full site");
            } else {
                System.out.println("(expected) Row 4 Col 3 is not full site");
            }

            System.out.println("open site Row 3 Col 2");
            system.open(3, 2);
            System.out.println("Opened Sites: " + system.numberOfOpenSites());

            if (system.isFull(3, 2)) {
                System.out.println("(expected) Row 3 Col 2 is full site");
            } else {
                System.out.println("(error) Row 3 Col 2 is not full site");
            }

            System.out.println("open site Row 4 Col 2");
            system.open(4, 2);
            System.out.println("Opened Sites: " + system.numberOfOpenSites());

            if (system.isFull(4, 2)) {
                System.out.println("(expected) Row 4 Col 2 is full site");
            } else {
                System.out.println("(error) Row 4 Col 2 is not full site");
            }

            System.out.println("does system percolate?");
            if (system.percolates()) {
                System.out.println("(expected) Yes");
            } else {
                System.out.println("(error) No");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
