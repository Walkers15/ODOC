// https://www.acmicpc.net/problem/1967

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mar1423_DFS_TreeLength {
  public static ArrayList<ArrayList<node>> tree;
  public static int result = -1;
  public static int endIndex = -1;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    if (n == 1) {
      System.out.println(0);
      return;
    }

    tree = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      tree.add(new ArrayList<>());
    }

    for (int i = 0; i < n - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int parent = Integer.parseInt(st.nextToken()) - 1;
      int child = Integer.parseInt(st.nextToken()) - 1;
      int cost = Integer.parseInt(st.nextToken());

      tree.get(parent).add(new node(child, cost));
      tree.get(child).add(new node(parent, cost));
    }

    // 1. 어느 정점에서 시작해도 cost가 가장 큰 점은 지름의 한 끝점임
    visited = new boolean[n];
    visited[0] = true;
    dfs(0, 0);
    // System.out.println(endIndex);

    // 2. 해당 끝점을 기준으로 다시 탐색하면 지름의 길이가 나옴
    Arrays.fill(visited, false);
    visited[endIndex] = true;
    dfs(endIndex, 0);

    System.out.println(result);
  }

  public static boolean[] visited;

  public static void dfs(int index, int cost) {
    for (node next : tree.get(index)) {
      if (!visited[next.to]) {
        visited[next.to] = true;
        if (next.cost + cost > result) {
          // System.out.println(next.to + " / " + (next.cost + cost));
          result = next.cost + cost;
          endIndex = next.to;
        }
        dfs(next.to, cost + next.cost);
      }
    }
  }

  static class node {
    int to;
    int cost;

    node(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }
  }
}