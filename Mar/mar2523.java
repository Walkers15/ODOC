// https://www.acmicpc.net/problem/1005

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class mar2523 {
  public static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      test(br);
    }
    System.out.println(sb);
  }

  static void test(BufferedReader br) throws IOException {
    // System.out.println("new test!");
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[] cost = new int[n];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      cost[i] = Integer.parseInt(st.nextToken());
    }

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    int[] dependency = new int[n];
    while (k-- > 0) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;
      graph.get(from).add(to);
      dependency[to]++;
    }

    Queue<Integer> leaf = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (dependency[i] == 0) {
        leaf.add(i);
      }
    }

    int start = Integer.parseInt(br.readLine()) - 1;
    int result = cost[start];
    boolean[] visited = new boolean[n];
    Queue<int[]> queue = new LinkedList<>();
    visited[start] = true;
    queue.add(new int[] { start, 0 });

    int[] dp = new int[n];
    while (!queue.isEmpty()) {
      int[] current = queue.poll();

      int node = current[0];
      int depth = current[1];
      for (int next : graph.get(node)) {

        if (!visited[next]) {
          visited[next] = true;
          if (dp[depth] < cost[next]) {
            dp[depth] = cost[next];
          }
          if (graph.get(next).size() != 0) {
            queue.add(new int[] { next, depth + 1 });

          }
        }
      }
    }
    for (int i = 0; i < n; i++) {
      result += dp[i];
    }
    // System.out.println("Test Result " + result);
    sb.append(result + "\n");
  }
}
