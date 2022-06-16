package unionFind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int grid [][];
    private int numOpen;
    private WeightedQuickUnionUF uf;
    private int size;
    private int topRoot;
    private int bottomRoot;

    /*
        creates n-by-n grid, with all sites initially blocked
        value of -1 means "blocked"
        use n*n + 1 as the "virtual top root"
        use n*n + 2 as the "virtual bottom root"
     */
    public Percolation(int n){
        numOpen = 0;
        size = n;
        grid = new int [n + 1][n + 1];
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++){
                grid[i][j] = -1;
            }
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        topRoot = n * n + 1;
        bottomRoot = n * n + 2;
    }

    /*
        makes it easier to retrieve index for uf structure.
        basically transforms [i][j] to just 1 index.
     */
    private int getIndex(int row, int col){
        row--;
        col--;
        return row * grid[row].length + col;
    }

    /*
        opens the site (row, col) if it is not open already
     */
    public void open(int row, int col){
        int currNode = getIndex(row, col);
        grid[row][col] = 0; //if there's no opening around
        numOpen++;
        if (uf.find(currNode) == )
        if (row == 1){
            uf.union(currNode, topRoot);
        }
        else if (isOpen(row - 1, col)){
            uf.union(currNode, getIndex(row - 1, col));
        }

        if(isOpen(row + 1, col)){
            uf.union(currNode, getIndex(row + 1, col));
        }
        if (isOpen(row, col - 1)){
            uf.union(currNode, getIndex(row, col - 1));
        }
        if (isOpen(row, col + 1)){
            uf.union(currNode, getIndex(row, col + 1));
        }
    }

    /*
        is the site (row, col) open?
        value -1 as "blocked" indication
     */
    public boolean isOpen(int row, int col){
        return grid[row][col] != -1;
    }

    /*
        is the site (row, col) full?
     */
    public boolean isFull(int row, int col){
        int root = uf.find(getIndex(row, col));
        return root == topRoot;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates(){

    }

    // test client (optional)
    public static void main(String[] args){

    }
}

