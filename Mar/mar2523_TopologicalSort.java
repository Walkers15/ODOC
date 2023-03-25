// https://www.acmicpc.net/problem/1005 ACM Craft

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class mar2523_TopologicalSort {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    int t = Integer.parseInt(br.readLine());
    while (t-- > 0) {
      test();
    }
    System.out.println(sb);
  }

  public static void test() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    int[] cost = new int[n];
    int[] result = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      cost[i] = Integer.parseInt(st.nextToken());
      result[i] = cost[i];
      graph.add(new ArrayList<>());
    }

    int[] dependency = new int[n];
    for (int i = 0; i < k; i++) {
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken()) - 1;
      int to = Integer.parseInt(st.nextToken()) - 1;
      dependency[to]++;
      graph.get(from).add(to);
    }

    int w = Integer.parseInt(br.readLine()) - 1;

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      if (dependency[i] == 0) {
        queue.add(i);
      }
    }

    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (int next : graph.get(node)) {
        result[next] = Math.max(result[next], result[node] + cost[next]);
        dependency[next]--;
        if (dependency[next] == 0) {
          queue.add(next);
        }
      }
    }

    sb.append(result[w] + "\n");
  }
}