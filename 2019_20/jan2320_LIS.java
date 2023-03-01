
//https://www.acmicpc.net/problem/11053
//LIS nlogn 알고리즘
//내일은 DP로 풀어볼 
import java.util.Scanner;

public class jan2320_LIS {
    static int length = 0;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int[] dp = new int[n + 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (dp[idx] < arr[i]) {
                dp[++idx] = arr[i];
                length++;
            } else {
                int k = lowerBound(dp, arr[i]);
                // System.out.println(k);
                dp[k] = arr[i];
            }
            for (int j = 1; j <= idx; j++) {
                // System.out.print(dp[j] + " ");
            }
            // System.out.println();
        }
        // for(int i = 1 ; i <= idx ; i++)
        // System.out.print(dp[i]+" ");
        System.out.println(idx);
        sc.close();
    }

    public static int lowerBound(int[] dp, int key) {
        int high = length;
        int low = 1;
        int mid = low + (high - low) / 2;
        while (low < high) {
            // System.out.println("mid : " + mid);
            mid = low + (high - low) / 2;
            if (dp[mid] >= key) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
