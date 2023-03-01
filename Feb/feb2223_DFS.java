package Feb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class feb2223_DFS {
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

    for (int i = 0; i < n; i++) {
      Arrays.fill(visited, false);
      for (int j = 0; j < n; j++) {
        if (graph.get(i).contains(j) && visited[j] == false) {
          dfs(i, j);
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

  public static void dfs(int x, int y) {
    visited[y] = true;
    map[x][y] = true;
    for (int i = 0; i < n; i++) {
      if (graph.get(y).contains(i) && visited[i] == false) {
        dfs(x, i);
      }
    }
  }
}
