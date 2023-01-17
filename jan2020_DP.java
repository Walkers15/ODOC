
//https://www.acmicpc.net/problem/1149
//첫 번째를 무엇으로 칠했는지만 고려하면 됨!
//각각의 dp 배열은 R,G,B 중 하나를 칠하는 경우이므로, 전 단계에서 자신이 아닌 두 색을 칠한 값의 최솟값을 찾아 자기 색을 더하는 비용을 더함
import java.util.Scanner;

public class jan2020_DP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] vileage = new int[n][3];
        int[][] dp = new int[n][3];
        for (int i = 0; i < n; i++) {
            vileage[i][0] = sc.nextInt();
            vileage[i][1] = sc.nextInt();
            vileage[i][2] = sc.nextInt();
        }
        dp[0][0] = vileage[0][0];
        dp[0][1] = vileage[0][1];
        dp[0][2] = vileage[0][2];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + vileage[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + vileage[i][1];
            dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][0]) + vileage[i][2];
        }
        System.out.println(Math.min(Math.min(dp[n - 1][0], dp[n - 1][1]), dp[n - 1][2]));
        sc.close();
    }
}
