package Feb;
// https://www.acmicpc.net/problem/9095

import java.util.Scanner;

public class feb0223_DP {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int i = 0; i < t; i++) {
      System.out.println(test(sc));
    }
    sc.close();
  }

  public static int test(Scanner sc) {
    int n = sc.nextInt();
    switch (n) {
      case 1:
        return 1;
      case 2:
        return 2;
      case 3:
        return 4;
    }

    int[] dp = new int[n];
    dp[0] = 1;
    dp[1] = 2;
    dp[2] = 4;

    for (int i = 3; i < n; i++) {
      dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
    }
    return dp[n - 1];
  }
}
