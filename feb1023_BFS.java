// https://www.acmicpc.net/problem/2178

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class feb1023_BFS {
  static int n, m;
  static boolean[][] maze;
  static boolean[][] visited;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    n = sc.nextInt();
    m = sc.nextInt();
    sc.nextLine();

    maze = new boolean[n][m];
    visited = new boolean[n][m];

    for (int i = 0; i < n; i++) {
      String roadStr = sc.nextLine();
      for (int j = 0; j < m; j++) {
        int road = Character.getNumericValue(roadStr.charAt(j));
        if (road == 1) {
          maze[i][j] = true;
        } else {
          maze[i][j] = false;
        }
      }
    }

    int result = Integer.MAX_VALUE;

    Queue<int[]> queue = new LinkedList<>();
    visited[0][0] = true;
    queue.add(new int[] { 0, 0, 0 });
    while (!queue.isEmpty()) {
      int[] currentRoad = queue.poll();
      int x = currentRoad[0];
      int y = currentRoad[1];
      int moveCount = currentRoad[2];

      int leftX = x - 1;
      int rightX = x + 1;
      int downY = y - 1;
      int upY = y + 1;

      // 상하좌우 인접칸 이동 가능한지 확인하고 이동
      int nextMoveCount = ++moveCount;
      if (leftX >= 0 && maze[y][leftX] && !visited[y][leftX]) {
        if (checkEnd(leftX, y)) {
          if (nextMoveCount < result) {
            result = ++nextMoveCount;
          }
        }
        visited[y][leftX] = true;
        queue.add(new int[] { leftX, y, nextMoveCount });
      }

      if (rightX < m && maze[y][rightX] && !visited[y][rightX]) {
        if (checkEnd(rightX, y)) {
          if (nextMoveCount < result) {
            result = ++nextMoveCount;
          }
        }
        visited[y][rightX] = true;
        queue.add(new int[] { rightX, y, nextMoveCount });
      }

      if (downY >= 0 && maze[downY][x] && !visited[downY][x]) {
        if (checkEnd(x, downY)) {
          if (nextMoveCount < result) {
            result = ++nextMoveCount;
          }
        }
        visited[downY][x] = true;
        queue.add(new int[] { x, downY, nextMoveCount });
      }

      if (upY < n && maze[upY][x] && !visited[upY][x]) {
        if (checkEnd(x, upY)) {
          if (nextMoveCount < result) {
            result = ++nextMoveCount;
          }
        }
        visited[upY][x] = true;
        queue.add(new int[] { x, upY, nextMoveCount });
      }
    }

    System.out.println(result);
    sc.close();
  }

  public static boolean checkEnd(int x, int y) {
    if (x + 1 == m && y + 1 == n) {
      return true;
    } else {
      return false;
    }
  }
}
