package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class mar1823_BFS {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] map = new int[n][m];
    boolean[][] visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      String row = br.readLine();
      for (int j = 0; j < m; j++) {
        map[i][j] = Character.getNumericValue(row.charAt(j));
      }
    }

    // 도착지가 출발점인 경우
    if (n == 1 && m == 1) {
      System.out.println(1);
      return;
    }
    int result = Integer.MAX_VALUE;
    Queue<Info> queue = new LinkedList<>();
    queue.add(new Info(0, 0, 1, true));
    visited[0][0] = true;

    int[][] diff = new int[][] { new int[] { 1, 0 }, new int[] { -1, 0 }, new int[] { 0, 1 }, new int[] { 0, -1 } };
    while (!queue.isEmpty()) {
      Info cur = queue.poll();
      int time = ++cur.time;
      for (int i = 0; i < 4; i++) {
        int x = cur.x + diff[i][1];
        int y = cur.y + diff[i][0];
        boolean inRange = x >= 0 && x < m && y >= 0 && y < n;

        if (inRange && !visited[y][x]) {
          // System.out.printf("%d %d %d %b %b\n", x, y, map[y][x], cur.canBreak,
          // inRange);
          // 도착점에 도착했는지 확인
          if (x == m - 1 && y == n - 1) {
            if (result > time) {
              result = time;
            }
            continue;
          }

          // 길이라면 이동
          if (map[y][x] == 0) {
            visited[y][x] = true;
            queue.add(new Info(x, y, time, cur.canBreak));
          }

          // 벽이라면 부술 수 있는지 확인해보고 이동
          if (map[y][x] == 1 && cur.canBreak) {
            // System.out.println("BREAK WALL " + x + " " + y);
            visited[y][x] = true;
            queue.add(new Info(x, y, time, false));
          }
        }
      }
    }

    System.out.println(result == Integer.MAX_VALUE ? -1 : result);
  }

  static class Info {
    int x;
    int y;
    int time;
    boolean canBreak;

    Info(int x, int y, int time, boolean canBreak) {
      this.x = x;
      this.y = y;
      this.time = time;
      this.canBreak = canBreak;
    }
  }
}
