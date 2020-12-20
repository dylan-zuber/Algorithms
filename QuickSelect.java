import java.util.*;
import java.io.*;

public class QuickSelect {
    public static void main(String[] args) {
        int[] arr = new int[]{34, 11, 43, 36, 33, 5, 41, 1, 12};
        // called for finding the 5th smallest element (index 4 in sorted array)
        System.out.println(quickselect(arr, 0, arr.length - 1, 4));
        // returns 33 at s = 4

    }

    public static int LomutoPartition(int[] list, int l, int r) {
        int p = list[l];
        int s = l;
        for(int i = l + 1; i <= r; i++) {
            if(list[i] < p) {
                s = s + 1;
                int temp = list[i];
                list[i] = list[s];
                list[s] = temp;
            }
       //     System.out.println(Arrays.toString(list));
        }
        int temp = list[l];
        list[l] = list[s];
        list[s] = temp;
       // System.out.println(Arrays.toString(list));
        return s;
    }

    public static int quickselect(int[] list, int l, int r, int k) {
        int s = LomutoPartition(list, l, r);
        System.out.println(Arrays.toString(list));
        System.out.println("s: " + s);
        System.out.println("p: " + list[s]);
        if(s == k) {
            return list[s];
        } else if( s > k ) {
            return quickselect(list, l, s-1, k);
        } else {
            return quickselect(list, s+1, r, k);
        }

    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
    }
}