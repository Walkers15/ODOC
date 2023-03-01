// https://www.acmicpc.net/problem/11725

package Mar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class mar0123_BFS {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < n - 1; i++) {
      int x = sc.nextInt() - 1;
      int y = sc.nextInt() - 1;
      graph.get(x).add(y);
      graph.get(y).add(x);
    }
    sc.close();
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[n];
    int[] parent = new int[n];
    visited[0] = true;
    queue.add(0);

    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (int i : graph.get(node)) {
        if (!visited[i]) {
          visited[i] = true;
          parent[i] = node;
          queue.add(i);
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < n; i++) {
      sb.append((parent[i] + 1) + "\n");
    }

    System.out.print(sb);
  }
}
