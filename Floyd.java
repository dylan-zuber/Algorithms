import java.util.*;

public class Floyd {
    public static void main(String[] args) {
        int max = 10000;
        int[][] arr = {{0, max, 3, max}, 
                        {2, 0, max, max}, 
                        {max, 7, 0, 1}, 
                        {6, max, max, 0}};
        System.out.println("Initial:");
        printArray(arr);
        floydsAlgorithm(arr, arr.length);
        System.out.println("Final:");
        printArray(arr);
    }

    public static void floydsAlgorithm(int[][] arr, int n) {
        for(int k = 0; k < n; k++) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
            System.out.println("k: " + k);
            printArray(arr);
        }
    }

    public static void printArray(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}