// https://www.acmicpc.net/problem/9466 텀 프로젝트

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Apr1223_DFS {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] visited;
  static boolean[] alreadyHaveTeam;
  static int result;

  public static void main(String[] args) throws NumberFormatException, IOException {
    int tc = Integer.parseInt(br.readLine());
    while (tc-- > 0) {
      test();
    }
    System.out.print(sb);
  }

  public static void test() throws NumberFormatException, IOException {
    int n = Integer.parseInt(br.readLine());
    graph = new ArrayList<>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      int select = Integer.parseInt(st.nextToken()) - 1;
      ArrayList<Integer> vertex = new ArrayList<>();
      vertex.add(select);
      graph.add(vertex);
    }

    result = n;
    visited = new boolean[n];
    alreadyHaveTeam = new boolean[n];
    for (int i = 0; i < n; i++) {
      dfs(i);
    }
    sb.append(result + "\n");

  }

  public static void dfs(int now) {
    if (visited[now]) {
      return;
    }

    visited[now] = true;
    int next = graph.get(now).get(0);

    if (!visited[next]) {
      dfs(next);
    } else {
      if (!alreadyHaveTeam[next]) {
        for (int i = next; i != now; i = graph.get(i).get(0)) {
          result--;
        }
        result--;
      }

    }
    alreadyHaveTeam[now] = true;
  }

}
