
//https://www.acmicpc.net/problem/12865
//DP마지막 냅색, 이 냅색에서는 한 물건은 한번만 담을 수 있으므로 식 세울때 주의할 것
import java.util.*;

public class jan2620_DP_KnapSack {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<obj> arr = new ArrayList<>();
        for (int i = 0; i < n; i++)
            arr.add(new obj(sc.nextInt(), sc.nextInt()));
        Collections.sort(arr);
        int[][] dp = new int[k + 1][n + 1];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                if (i >= arr.get(j - 1).w) {
                    dp[i][j] = Math.max(dp[i - arr.get(j - 1).w][j - 1] + arr.get(j - 1).v, dp[i][j - 1]);
                } else
                    dp[i][j] = dp[i][j - 1];
            }
        }
        System.out.println(dp[k][n]);
        sc.close();
    }

    static class obj implements Comparable<obj> {
        int w;
        int v;

        obj(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(obj a) {
            if (this.w > a.w)
                return -1;
            else if (this.w == a.w) {
                if (this.v > a.v)
                    return 1;
                else
                    return 0;
            } else
                return 1;
        }
    }
}
