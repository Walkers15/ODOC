package Jan;

// https://www.acmicpc.net/problem/1260
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class jan2723_DFSBFS {
  public static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
  public static boolean[] visited;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int m = sc.nextInt();
    int v = sc.nextInt();
    visited = new boolean[n + 1];
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < m; i++) {
      int v1 = sc.nextInt();
      int v2 = sc.nextInt();

      graph.get(v1 - 1).add(v2);
      graph.get(v2 - 1).add(v1);
    }

    // 그래프 탐색시 정점 번호가 작은 순으로 접근해야 함
    for (int i = 0; i < n; i++) {
      graph.get(i).sort(null);
    }

    dfs(v);
    System.out.println();
    Arrays.fill(visited, false);
    bfs(v);
    sc.close();
  }

  public static void dfs(int v) {
    System.out.print(v + " ");
    visited[v] = true;
    for (int i : graph.get(v - 1)) {
      if (!visited[i]) {
        dfs(i);
      }
    }
  }

  public static void bfs(int v) {
    Queue<Integer> queue = new LinkedList<Integer>();
    queue.add(v);
    visited[v] = true;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      System.out.print(node + " ");
      for (int i : graph.get(node - 1)) {
        if (visited[i] == false) {
          visited[i] = true;
          queue.add(i);
        }
      }
    }

  }
}