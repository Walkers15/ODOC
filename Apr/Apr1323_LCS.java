// https://www.acmicpc.net/problem/9252 LCS2

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Apr1323_LCS {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String a = br.readLine();
    String b = br.readLine();

    int n = a.length() >= b.length() ? a.length() : b.length();

    int[][] dp = new int[n + 1][n + 1];

    Arrays.fill(dp[0], 0);
    for (int i = 1; i <= a.length(); i++) {
      dp[i][0] = 0;
      for (int j = 1; j <= b.length(); j++) {
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }

    StringBuilder sb = new StringBuilder();

    int lcsLength = dp[a.length()][b.length()];
    sb.append(lcsLength + "\n");

    int aIndex = a.length();
    int bIndex = b.length();

    char[] lcs = new char[lcsLength];
    while (dp[aIndex][bIndex] != 0) {
      if (dp[aIndex][bIndex] == dp[aIndex - 1][bIndex]) {
        aIndex--;
        continue;
      }
      if (dp[aIndex][bIndex] == dp[aIndex][bIndex - 1]) {
        bIndex--;
        continue;
      }
      lcs[--lcsLength] = a.charAt(aIndex - 1);
      aIndex--;
      bIndex--;
    }

    for (int i = 0; i < lcs.length; i++) {
      sb.append(lcs[i]);
    }

    System.out.println(sb);
  }
}
