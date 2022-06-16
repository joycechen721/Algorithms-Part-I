package unionFind;

public class OptimizedQuickUnionUF {
    private int[] id;

    public OptimizedQuickUnionUF(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    /*
        id[i] points to i's parent.
        root is where i is equal to (==) id[i].
     */
    private int root(int i){
        while (i != id[i]){
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p) == root(q);
    }

    /*
        optimized so that root of smaller tree is linked to
        root of bigger tree, rather than the
     */
    public void union(int p, int q){
        int i = root(p);
        int j = root(q);
        id[i] = j;
    }
}
