// https://www.acmicpc.net/problem/2667

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class feb2323_DFS {
  public static boolean[][] map;
  public static ArrayList<Integer> dangee = new ArrayList<>();
  public static boolean[][] visited;
  public static int n;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    sc.nextLine();
    map = new boolean[n][n];
    visited = new boolean[n][n];

    int dangeeNumber = 0;

    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      for (int j = 0; j < n; j++) {
        int info = Character.getNumericValue(line.charAt(j));
        if (info == 1) {
          map[i][j] = true;
        } else {
          map[i][j] = false;
        }
      }
    }

    sc.close();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (!visited[i][j] && map[i][j]) {
          dangee.add(1);
          visited[i][j] = true;
          dfs(i, j, dangeeNumber);
          dangeeNumber++;
        }
      }
    }

    Collections.sort(dangee);

    StringBuilder sb = new StringBuilder();
    sb.append(dangee.size() + "\n");
    for (int i = 0; i < dangee.size(); i++) {
      sb.append(dangee.get(i) + "\n");
    }

    System.out.print(sb);
  }

  public static void dfs(int x, int y, int dangeeNumber) {
    int[] dx = new int[] { 1, -1, 0, 0 };
    int[] dy = new int[] { 0, 0, 1, -1 };

    for (int i = 0; i < 4; i++) {
      int newX = x + dx[i];
      int newY = y + dy[i];

      boolean canMove = newX >= 0 && newX < n && newY >= 0 && newY < n;

      if (canMove && !visited[newX][newY] && map[newX][newY]) {
        int curNum = dangee.get(dangeeNumber);
        dangee.set(dangeeNumber, ++curNum);
        visited[newX][newY] = true;
        dfs(newX, newY, dangeeNumber);
      }
    }
  }
}
