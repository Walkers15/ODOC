// https://www.acmicpc.net/problem/9328 열쇠
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 최소 이동 거리 BFS가 아닌 최대 갯수 BFS이므로 Queue 및 Visited 초기화 조건이 좀 다름
 * 만약 거리까지 구하라고 하면 어떻게 풀어야 할까..?
 */
public class Apr1023_BFS {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws NumberFormatException, IOException {
    int tc = Integer.parseInt(br.readLine());
    while (tc-- > 0) {
      test();
    }
    System.out.print(sb);
  }

  public static void test() throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    int h = Integer.parseInt(st.nextToken());
    int w = Integer.parseInt(st.nextToken());

    final int MAX_HEIGHT = h + 2;
    final int MAX_WIDTH = w + 2;

    int[][] map = new int[MAX_HEIGHT][MAX_WIDTH];
    boolean[] keys = new boolean['z' - 'a' + 1];
    Arrays.fill(map[0], 0);
    for (int i = 1; i <= h; i++) {
      map[i][0] = 0;
      String row = br.readLine();
      for (int j = 1; j <= w; j++) {
        char cur = row.charAt(j - 1);
        if (cur == '*') {
          map[i][j] = 1;
        } else if (cur == '.') {
          map[i][j] = 0;
        } else {
          map[i][j] = cur;
        }
      }
      map[i][MAX_WIDTH - 1] = 0;
    }
    Arrays.fill(map[MAX_HEIGHT - 1], 0);

    Queue<int[]> queue = new LinkedList<>();

    String inputKeys = br.readLine();
    char toUpperCase = 'a' - 'A';
    if (inputKeys.charAt(0) != '0') {
      for (int i = 0; i < inputKeys.length(); i++) {
        char key = inputKeys.charAt(i);
        keys[key - 'a'] = true;
        char targetDoor = (char) (key - toUpperCase);
        for (int k = 0; k < MAX_HEIGHT; k++) {
          for (int l = 0; l < MAX_WIDTH; l++) {
            if (map[k][l] == targetDoor) {
              map[k][l] = 0;
            }
          }
        }
      }
    }

    queue.add(new int[] { 0, 0 });

    int result = 0;

    boolean[][] visited = new boolean[MAX_HEIGHT][MAX_WIDTH];
    boolean[][] visitButNoKey = new boolean[MAX_HEIGHT][MAX_WIDTH];
    boolean[][] visitedDocument = new boolean[MAX_HEIGHT][MAX_WIDTH];

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();

      int[] dy = { 1, -1, 0, 0 };
      int[] dx = { 0, 0, 1, -1 };

      for (int i = 0; i < 4; i++) {
        int y = cur[0] + dy[i];
        int x = cur[1] + dx[i];

        boolean notWallAndBound = y >= 0 && y < MAX_HEIGHT && x >= 0 && x < MAX_WIDTH && map[y][x] != 1;
        if (!notWallAndBound) {
          continue;
        }

        boolean notVisited = visited[y][x] == false;
        if (!notVisited) {
          continue;
        }

        boolean isDoor = 'A' <= map[y][x] && map[y][x] <= 'Z';
        boolean isKey = 'a' <= map[y][x] && map[y][x] <= 'z';
        boolean isDocument = map[y][x] == '$';

        // 1. 길인 경우 그냥 이동
        if (!isDocument && !isDoor && !isKey) {
          visited[y][x] = true;
          queue.add(new int[] { y, x });
        }

        // 2. 문서인 경우 이미 획득 표시한 문서인지 확인해보고 증가 후 이동
        if (isDocument) {
          if (!visitedDocument[y][x]) {
            visitedDocument[y][x] = true;
            result++;
          }
          visited[y][x] = true;
          queue.add(new int[] { y, x });
        }

        // 3. 문인 경우 키가 있는지 확인하고 이동, 없으면 추후 도달 가능 표시
        if (isDoor) {
          if (keys[map[y][x] - 'A'] == true) {
            visited[y][x] = true;
            queue.add(new int[] { y, x });
          } else {
            visitButNoKey[y][x] = true;
          }
        }

        // 4. 열쇠인 경우 해당 열쇠를 먹었을 때 새로 갈 수 있는 곳이 있으면 bfs 새로 시작
        if (isKey) {
          visited[y][x] = true;
          queue.add(new int[] { y, x });
          if (keys[map[y][x] - 'a'] == false) {
            // 새로 먹은 열쇠라면
            keys[map[y][x] - 'a'] = true;
            boolean isFlushed = false;
            for (int m = 0; m < MAX_HEIGHT; m++) {
              for (int n = 0; n < MAX_WIDTH; n++) {
                if (visitButNoKey[m][n] && map[m][n] - 'A' == map[y][x] - 'a') {
                  if (!isFlushed) {
                    queue.removeAll(queue);
                    visited = new boolean[MAX_HEIGHT][MAX_WIDTH];
                    isFlushed = true;
                  }
                  map[m][n] = 0;
                  visited[m][n] = true;
                  queue.add(new int[] { m, n });
                }
              }
            }
          }
        }

      }

    }

    sb.append(result + "\n");
  }
}
