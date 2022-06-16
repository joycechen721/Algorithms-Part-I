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
        use n*n as the "virtual top root"
        use n*n + 1 as the "virtual bottom root"
     */
    public Percolation(int n) throws IllegalArgumentException{
        if (n <= 0) throw new IllegalArgumentException();
        numOpen = 0;
        grid = new int [n + 1][n + 1];
        size = n + 1;
        for (int i = 0; i <= n; i++){
            for (int j = 0; j <= n; j++){
                grid[i][j] = -1;
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
        use value 0 to signify opening
     */
    public void open(int row, int col) throws IllegalArgumentException{
        if (row > size || row < 0
            || col < 0 || col > size) throw new IllegalArgumentException();
        int currNode = getIndex(row, col);
        grid[row][col] = 0;
        numOpen++;
        if (row == 1) {
            uf.union(currNode, topRoot);
        }
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

    /*
        is the site (row, col) open?
        -1 = "blocked"
     */
    public boolean isOpen(int row, int col){
        return grid[row][col] != -1;
    }

    //is the site (row, col) full?
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
        return uf.find(topRoot) == uf.find(bottomRoot);
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation grid = new Percolation(8);

        grid.open(2, 2);
        grid.open(3,2);
        grid.open(3, 3);
        grid.open(4,3);
        grid.open(5,3);
        grid.open(5,4);
        grid.open(5,5);
        grid.open(7,5);
        grid.open(8,5);
        grid.open(1,1);
        grid.open(1, 2);
        grid.open(6,5);
        System.out.println(grid.percolates());
    }
}

