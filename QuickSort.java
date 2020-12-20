import java.util.*;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{35, 41, 25, 39, 22, 37, 40, 12};
        quicksort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public static int HoarePartition(int[] arr, int l, int r) {
        int p = arr[l];
        int i = l - 1;
        int j = r + 1;
        
        while(true) {
            do {
                i++;
            } while(arr[i] < p);
    
            do {
                j--;
            } while( arr[j] > p);

            if(i >= j){
                return j;
            }
            
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void quicksort(int[] arr, int l, int r) {
        if( l < r ) {
            int s = HoarePartition(arr, l, r);
            System.out.println("l: " + l + " r: " + r + " s: " + s + " " + Arrays.toString(arr));
            quicksort(arr, l, s);
            quicksort(arr, s+1, r);
        }
    }
}