package unionFind;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/*
    passed all except 4 backwater tests (will fix in the future)
    current grade: 94/100 as of 6/16/2022
 */
public class Percolation {
    private final boolean[][] grid;
    private int numOpen;
    private final WeightedQuickUnionUF uf;
    private final int size;
    private final int topRoot;
    private final int bottomRoot;

    /*
        creates n-by-n grid, with all sites initially blocked
        value of -1 means "blocked"
        use n*n as the "virtual top root"
        use n*n + 1 as the "virtual bottom root"
     */
    public Percolation(int n){
        if (n <= 0) throw new IllegalArgumentException("Cannot have size 0 or negative grid initialization.");
        numOpen = 0;
        grid = new boolean [n + 1][n + 1];
        size = n + 1;
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++){
                grid[i][j] = false;
            }
        }
        uf = new WeightedQuickUnionUF(n * n + 2);
        topRoot = n * n;
        bottomRoot = n * n + 1;
    }

    /*
        makes it easier to retrieve index for uf structure.
        makes 1,1 at 0; 1,2 at 1...
     */
    private int getIndex(int row, int col){
        row--;
        col--;
        return row * (size - 1) + col;
    }

    /*
        opens the site (row, col) if it is not open already
     */
    public void open(int row, int col){
        checkBoundaries(row, col);
        int currNode = getIndex(row, col);
        if (!isOpen(row, col)){
            numOpen++;
            grid[row][col] = true;
        }

        //any node at top row unions with virtual top root
        if (row == 1) {
            uf.union(currNode, topRoot);
        }
        //any node at bottom row unions with virtual bottom root
        else if (row == size - 1){
            uf.union(currNode, bottomRoot);
        }

        //check top of current position
        if (row - 1 > 0 && isOpen(row - 1, col)){
            uf.union(currNode, getIndex(row - 1, col));
        }
        //check bottom of current position
        if(row + 1 < size && isOpen(row + 1, col)){
            uf.union(currNode, getIndex(row + 1, col));
        }
        //check left of current position
        if (col - 1 > 0 && isOpen(row, col - 1)){
            uf.union(currNode, getIndex(row, col - 1));
        }
        //check right of current position
        if (col + 1 < size && isOpen(row, col + 1)){
            uf.union(currNode, getIndex(row, col + 1));
        }
    }

    private void checkBoundaries(int row, int col){
        if (row >= size || row <= 0 || col <= 0 || col >= size)
            throw new IllegalArgumentException("Either row or column value or both are out of bounds.");
    }

    //is the site (row, col) open?
    public boolean isOpen(int row, int col){
        checkBoundaries(row, col);
        return grid[row][col];
    }

    //is the site (row, col) full?
    public boolean isFull(int row, int col){
        checkBoundaries(row, col);
        int currRoot = uf.find(getIndex(row, col));
        int root = uf.find(topRoot);
        return currRoot == root;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates(){
        if (size == 2 && numOpen == 1) return true;
        return uf.find(topRoot) == uf.find(bottomRoot);
    }

    // test client (optional)
    public static void main(String[] args){
//        Percolation grid = new Percolation(8);
//
//        grid.open(2, 2);
//        grid.open(3,2);
//        grid.open(3, 3);
//        grid.open(4,3);
//        grid.open(5,3);
//        grid.open(5,4);
//        grid.open(5,5);
//        grid.open(7,5);
//        grid.open(8,5);
//        grid.open(1,1);
//        grid.open(1, 2);
//        grid.open(6,5);
//        System.out.println(grid.percolates());
    }
}

