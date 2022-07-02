package Sorting;

public class Insertion {

    public static void sort(Comparable[] a)
    {
        for (int i = 0; i < a.length; i++){
            for (int j = i; j >= 1; j--){
                if (less(a[j], a[j - 1])) swap(a, j, j - 1); //DONT swap with a[i]!! remember, previous elements would all be sorted already so u just need to compare with the 1 before
                else break;
            }
        }
    }

    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static void swap(Comparable[] a, int x, int y){
        Comparable temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    public static void main(String [] args){
        Comparable[] numbers = new Comparable[]{0, 4, 90, 5, 12, 444, 2, 1, 3, 52, -2, -46, 25};
        sort(numbers);
        for (int i = 0; i < numbers.length; i++)
            System.out.print(numbers[i] + ", ");
    }
}
