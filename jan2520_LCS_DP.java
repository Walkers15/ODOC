
//https://www.acmicpc.net/problem/9251
//LCS, 수업시간에 배운 거 직접 구현해보기
//다음에 9252번 풀어보기
import java.util.Scanner;

public class jan2520_LCS_DP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        int m = a.length();
        int n = b.length();
        int result = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]);
                }
            }
        }
        for (int j = 0; j < n + 1; j++) {
            for (int i = 0; i < m + 1; i++) {
                if (result < dp[j][i])
                    result = dp[j][i];
            }
        }
        System.out.println(result);
        sc.close();
    }
}
