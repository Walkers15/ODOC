// https://www.acmicpc.net/problem/2533 사회망 서비스(SNS)

package May;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class May0423_DP_DFS {
  static boolean[] visited;
  static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  static int[][] dp;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    visited = new boolean[n];
    dp = new int[n][2];
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < n - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken()) - 1;
      int v2 = Integer.parseInt(st.nextToken()) - 1;
      graph.get(v1).add(v2);
      graph.get(v2).add(v1);
    }

    visited[0] = true;
    dfs(0);

    System.out.println(Math.min(dp[0][0], dp[0][1]));
  }

  public static void dfs(int num) {
    dp[num][0] = 0; // n번 친구가 얼리어답터가 아님
    dp[num][1] = 1; // n번 친구가 얼리어답터임

    for (int next : graph.get(num)) {
      if (!visited[next]) {
        visited[next] = true;
        dfs(next);
        dp[num][0] += dp[next][1];
        dp[num][1] += Math.min(dp[next][0], dp[next][1]);
      }
    }
  }
}
