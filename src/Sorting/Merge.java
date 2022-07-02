package Sorting;

public class Merge {

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi)
    {
        assert isSorted(a, lo, mid); // precondition: a[lo..mid] sorted
        assert isSorted(a, mid+1, hi); // precondition: a[mid+1..hi] sorted
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++)
        {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi); // postcondition: a[lo..hi] sorted
    }

    private static void sort(Comparable[] a , Comparable[] aux, int lo, int hi){
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a)
    {
        Comparable [] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static boolean isSorted(Comparable[] a, int low, int high){
        for (int i = 0; i < a.length - 1; i++){
            if (less(a[i], a[i + 1])) return false;
        }
        return true;
    }

    public static void main(String [] args){
        Comparable[] numbers = new Comparable[]{0, 4, 6, 8, 10, 444, 2, 1, 3, 52, -1, -55, 85};
        sort(numbers);
        for (int i = 0; i < numbers.length; i++)
            System.out.print(numbers[i] + ", ");
    }
}
