package unionFind;

public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N){
        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }

    public boolean connected(int p, int q){
        return id[p] == id[q];
    }

    public void union(int p, int q){
        int id1 = id[p];
        int id2 = id[q];
        for (int i = 0; i < id.length; i++){
            if (id[i] == id1)
                id[i] = id2;
        }
    }
}
