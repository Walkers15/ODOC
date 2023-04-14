// https://www.acmicpc.net/problem/7579 앱

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr1423_DP_Knapsack {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    App[] appList = new App[n];

    st = new StringTokenizer(br.readLine());
    StringTokenizer st2 = new StringTokenizer(br.readLine());

    int maxCost = 0;
    for (int i = 0; i < n; i++) {
      appList[i] = new App(Integer.parseInt(st.nextToken()), Integer.parseInt(st2.nextToken()));
      maxCost += appList[i].exitCost;
    }

    int[] dp = new int[maxCost + 1];

    int result = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      for (int j = maxCost; j - appList[i].exitCost >= 0; j--) {
        int newMemory = appList[i].memory + dp[j - appList[i].exitCost];
        if (dp[j] < newMemory) {
          dp[j] = newMemory;
          if (newMemory >= m) {
            result = Math.min(result, j);
          }
        }
      }
    }

    System.out.println(result);
  }

  static class App {
    int memory;
    int exitCost;

    App(int memory, int exitCost) {
      this.memory = memory;
      this.exitCost = exitCost;
    }
  }
}
