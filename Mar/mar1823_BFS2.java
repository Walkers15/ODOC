package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class mar1823_BFS2 {
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
    Queue<AfterBreakInfo> afterBreakQueue = new LinkedList<>();
    queue.add(new Info(0, 0, 1));
    visited[0][0] = true;

    boolean[][] wallBroke = new boolean[n][m];
    // 벽을 부술 수 있는 모든 가능성 집어넣기
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
            // System.out.printf("도착!! %d %d %d\n", time, cur.x, cur.y);
            if (result > time) {
              result = time;
            }
            continue;
          }

          // 길이라면 이동
          if (map[y][x] == 0) {
            visited[y][x] = true;

            queue.add(new Info(x, y, time));
          }

          // 벽이라면 부술 수 있는지 확인해보고 부순 뒤 세계선에 집어넣음
          if (map[y][x] == 1) {
            if (!wallBroke[y][x]) {
              // System.out.printf("Break Wall %d %d in time %d\n", x, y, time);
              wallBroke[y][x] = true;
              // showVisited(visited);
              afterBreakQueue.add(new AfterBreakInfo(x, y, time, visited));
            }
          }
        }
      }
    }

    // System.out.println(afterBreakQueue.size());
    while (!afterBreakQueue.isEmpty()) {
      AfterBreakInfo info = afterBreakQueue.poll();
      boolean[][] afterVisited = info.visited;
      // visited = info.visited;
      afterVisited[info.y][info.x] = true;
      // System.out.printf("현재 세계선 %d %d %d\n", info.x, info.y, queue.size());
      // showVisited(info.visited);
      queue.add(info);
      while (!queue.isEmpty()) {
        Info cur = queue.poll();
        int time = ++cur.time;
        for (int i = 0; i < 4; i++) {
          int x = cur.x + diff[i][1];
          int y = cur.y + diff[i][0];
          boolean inRange = x >= 0 && x < m && y >= 0 && y < n;

          if (inRange && !afterVisited[y][x]) {
            // System.out.printf("%d %d %d %b %b\n", x, y, map[y][x], cur.canBreak,
            // inRange);
            // 도착점에 도착했는지 확인
            if (x == m - 1 && y == n - 1) {
              // System.out.printf("도착! %d %d %d\n", time, info.x, info.y);
              if (result > time) {

                result = time;
              }
              queue.clear();
              continue;
            }

            // 길이라면 이동
            if (map[y][x] == 0) {
              afterVisited[y][x] = true;

              queue.add(new Info(x, y, time));
            }
          }
        }
      }
    }

    System.out.println(result == Integer.MAX_VALUE ? -1 : result);
  }

  static void showVisited(boolean[][] visited) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 4; j++) {
        sb.append(visited[i][j] + " ");
      }
      sb.append("\n");
    }
    System.out.println(sb);
  }

  static class Info {
    int x;
    int y;
    int time;

    Info(int x, int y, int time) {
      this.x = x;
      this.y = y;
      this.time = time;
    }
  }

  static class AfterBreakInfo extends Info {
    boolean[][] visited;

    AfterBreakInfo(int x, int y, int time, boolean[][] visited) {
      super(x, y, time);
      this.visited = new boolean[visited.length][visited[0].length];
      for (int i = 0; i < visited.length; i++) {
        this.visited[i] = visited[i].clone();
      }
    }
  }
}
