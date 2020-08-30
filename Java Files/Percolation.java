/* *****************************************************************************
 *  Name:    Mohammad Alqudah
 *  NetID:   malqudah
 *  Precept: P05
 *
 *  Description:  Model an n-by-n percolation system using the union-find
 *                data structure.
 *
 *
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.StdOut;

public class Percolation {

    // substitute variable for n (dimension of the grid for ease of reference)
    private final int size;

    // hold the new 1D array after we convert the 2D one;
    // ie to help with UnionFind
    private boolean[] gridTwo;

    // assign array index to true if open
    private final boolean opened;

    // keep track of open sites
    private int openCount;

    // WeightedQuickUnionUF object
    private final QuickFindUF q1;

    // virtual top
    private final int top;

    // virtual bottom
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        // corner case, grid size cant be negative or 0
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        // initializes some of the instance variables
        size = n;
        gridTwo = new boolean[(int) Math.pow(size, 2)];
        opened = true;
        openCount = 0;

        // creates a weighted quick union object of length n squared plus 2
        // sets the top and bottom to be n squared and n squared plus 1
        // respectively
        q1 = new QuickFindUF((int) Math.pow(n, 2) + 2);
        top = (int) Math.pow(n, 2);
        bottom = (int) (Math.pow(n, 2) + 1);
    }


    // checks if a given row or col is negative or greater than what is allowed
    private void indexCheck(int row, int col) {
        if (row < 0 || row > (size - 1)) {
            throw new IllegalArgumentException();
        }
        if (col < 0 || col > (size - 1)) {
            throw new IllegalArgumentException();
        }
    }

    // private method to convert the 2D index into 1D index for easier access
    private int gridConversion(int row, int col) {
        int index = row * size + col;
        return index;
    }

    // opens the site (row, col) if it is not open already, and increments
    // sites that have been opened; also calls union for all the sites in
    // "the first and last rows" linking them to the virtual top
    // and bottom; then given the qualifications for
    // calling union on a site and its cardinal surrounding sites
    // ie updownleftright
    public void open(int row, int col) {
        indexCheck(row, col);
        if (gridTwo[gridConversion(row, col)] != opened) {
            gridTwo[gridConversion(row, col)] = opened;
            openCount++;
        }
        if (row == 0) {
            q1.union(top, gridConversion(row, col));
        }
        if (row == size - 1) {
            q1.union(bottom, gridConversion(row, col));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
            q1.union(gridConversion(row, col), gridConversion(row - 1, col));
        }
        if (row + 1 <= size - 1 && isOpen(row + 1, col)) {
            q1.union(gridConversion(row, col), gridConversion(row + 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            q1.union(gridConversion(row, col), gridConversion(row, col - 1));
        }
        if (col + 1 <= size - 1 && isOpen(row, col + 1)) {
            q1.union(gridConversion(row, col), gridConversion(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        indexCheck(row, col);
        return gridTwo[gridConversion(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        indexCheck(row, col);
        if (q1.find(gridConversion(row, col)) == q1.find(top)) {
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openCount;
    }

    // does the system percolate? if the root of the virtual bottom equals
    // the root of the top, it does
    public boolean percolates() {
        if (q1.find(bottom) == q1.find(top)) {
            return true;
        }
        return false;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Percolation test = new Percolation(3);
        test.open(0, 2);
        test.open(1, 2);
        test.open(2, 2);
        StdOut.println(test.isOpen(0, 2));
        StdOut.println(test.numberOfOpenSites());
        StdOut.println(test.isFull(1, 2));
        StdOut.println(test.percolates());
    }

}

