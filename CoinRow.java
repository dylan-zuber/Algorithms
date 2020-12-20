import java.util.*;

public class CoinRow {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 2, 10, 6, 2};
        System.out.println(coin(arr));
    }

    public static int coin(int[] arr) {
        int[] f = new int[arr.length + 1];
        f[0] = 0;
        f[1] = arr[0];
        for(int i = 2; i <= arr.length; i++) {
            f[i] = Math.max(arr[i-1] + f[i-2], f[i-1]);
        }
        System.out.println(Arrays.toString(f));
        return f[f.length-1];
    }
}