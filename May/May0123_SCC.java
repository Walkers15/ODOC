// https://www.acmicpc.net/problem/2150 Strongly Connected Component
package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class May0123_SCC {
  static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  static ArrayList<ArrayList<Integer>> reverse = new ArrayList<>();
  static boolean[] visited;
  static Stack<Integer> stack = new Stack<>();
  static ArrayList<ArrayList<Integer>> result = new ArrayList<>();
  static int sccIndex = -1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int v = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());

    visited = new boolean[v];

    for (int i = 0; i < v; i++) {
      graph.add(new ArrayList<>());
      reverse.add(new ArrayList<>());
    }

    for (int i = 0; i < e; i++) {
      st = new StringTokenizer(br.readLine());
      int v1 = Integer.parseInt(st.nextToken()) - 1;
      int v2 = Integer.parseInt(st.nextToken()) - 1;

      graph.get(v1).add(v2);
      reverse.get(v2).add(v1);
    }

    for (int i = 0; i < v; i++) {
      if (!visited[i]) {
        visited[i] = true;
        dfs(i);
      }
    }

    // System.out.println(stack);

    visited = new boolean[v];

    while (!stack.isEmpty()) {
      int cur = stack.pop();
      if (!visited[cur]) {
        sccIndex++;
        result.add(new ArrayList<>());
        visited[cur] = true;
        find_scc(cur);
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(result.size() + "\n");

    // System.out.println(result.size());

    result.forEach(arr -> Collections.sort(arr));
    Collections.sort(result, (arr1, arr2) -> {
      return arr1.get(0) - arr2.get(0);
    });

    result.forEach(arr -> arr.add(-1));
    result.forEach(arr -> {
      arr.forEach(n -> sb.append(n + " "));
      sb.append("\n");
    });

    System.out.print(sb);
  }

  static void find_scc(int n) {
    result.get(sccIndex).add(n + 1);
    for (int next : reverse.get(n)) {
      if (!visited[next]) {
        visited[next] = true;
        find_scc(next);
      }
    }
  }

  static void dfs(int n) {
    for (int next : graph.get(n)) {
      if (!visited[next]) {
        visited[next] = true;
        dfs(next);
      }
    }
    stack.add(n);
  }
}
