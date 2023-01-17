
//https://www.acmicpc.net/problem/1463
//가장 안좋은 케이스(1뺌)부터 가장 좋은 케이스(3나눔)으로 넘어가면서, 가장 높은 값 > 가장 낮은 값 의 순서로 저장할 수 있도록 반복문 구성
import java.util.Scanner;

public class jan2120_1_DP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) { // i(인덱스) = 1이 되어야 하는 숫자
            dp[i] = dp[i - 1] + 1;
            if (i % 2 == 0)
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            if (i % 3 == 0)
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
        }
        System.out.println(dp[n]);
        sc.close();
    }
}
