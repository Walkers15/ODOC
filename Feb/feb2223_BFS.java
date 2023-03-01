package Feb;
// https://www.acmicpc.net/problem/11403

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class feb2223_BFS {
  static boolean[][] map;
  static boolean[] visited;
  static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
  static int n;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    map = new boolean[n][n];
    visited = new boolean[n];

    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int connected = sc.nextInt();
        if (connected == 1) {
          graph.get(i).add(j);
        }
      }
    }
    sc.close();

    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < n; i++) {
      Arrays.fill(visited, false);
      for (int j = 0; j < n; j++) {
        if (graph.get(i).contains(j)) {
          queue.add(new int[] { i, j });
        }
      }

      while (!queue.isEmpty()) {
        int[] position = queue.poll();
        int x = position[0];
        int y = position[1];
        map[x][y] = true;
        visited[y] = true;
        for (int k = 0; k < n; k++) {
          if (graph.get(y).contains(k) && visited[k] == false) {
            queue.add(new int[] { x, k });
          }
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j]) {
          sb.append("1 ");
        } else {
          sb.append("0 ");
        }
      }
      sb.append("\n");
    }

    System.out.print(sb);
  }
}
