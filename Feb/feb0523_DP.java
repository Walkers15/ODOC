package Feb;

// https://www.acmicpc.net/problem/11726
import java.util.Scanner;

public class feb0523_DP {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    int[] dp = new int[n + 1];
    dp[0] = 1;
    dp[1] = 2;
    for (int i = 2; i < n; i++) {
      dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
    }

    System.out.println(dp[n - 1]);
    sc.close();
  }
}
