// https://www.acmicpc.net/problem/17144 미세먼지 안녕!
package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class mar2723 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int r = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int t = Integer.parseInt(st.nextToken());

    int[][] map = new int[r][c];

    int[] upperCleaner = new int[] { -2 };
    int[] lowerCleaner = new int[] { -2 };

    for (int i = 0; i < r; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < c; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());

        if (map[i][j] == -1) {
          if (upperCleaner[0] == -2) {
            upperCleaner = new int[] { i, j };
          } else {
            lowerCleaner = new int[] { i, j };
          }
        }
      }
    }

    while (t-- > 0) {
      // 확산
      int[][] spread = new int[r][c];
      spread[upperCleaner[0]][upperCleaner[1]] = -1;
      spread[lowerCleaner[0]][lowerCleaner[1]] = -1;
      int[] dx = new int[] { -1, 1, 0, 0 };
      int[] dy = new int[] { 0, 0, -1, 1 };
      for (int i = 0; i < r; i++) {
        for (int j = 0; j < c; j++) {
          if (map[i][j] > 0) {
            ArrayList<int[]> spreadPositions = new ArrayList<>();
            for (int k = 0; k < 4; k++) {
              // 확산 가능 여부 조사
              int newX = j + dx[k];
              int newY = i + dy[k];
              if (newX >= 0 && newX < c && newY >= 0 && newY < r && map[newY][newX] != -1) {
                spreadPositions.add(new int[] { newY, newX });
              }
            }
            int spreadAmount = (int) Math.floor(map[i][j] * (1 / (double) 5));

            spread[i][j] += (map[i][j] - spreadPositions.size() * spreadAmount);
            spreadPositions.forEach(position -> spread[position[0]][position[1]] += spreadAmount);
          }
        }
      }
      map = spread.clone();

      // 공기청정기 작동
      int[][] clean = new int[r][c];
      for (int i = 0; i < r; i++) {
        clean[i] = map[i].clone();
      }

      // 위쪽 공기청정기
      for (int i = 2; i < c; i++) {
        clean[upperCleaner[0]][i] = map[upperCleaner[0]][i - 1];
      }

      clean[upperCleaner[0]][c - 1] = map[upperCleaner[0]][c - 2];
      for (int i = upperCleaner[0] - 1; i > 0; i--) {
        clean[i][c - 1] = map[i + 1][c - 1];
      }

      clean[0][c - 1] = map[1][c - 1];
      for (int i = c - 2; i > 0; i--) {
        clean[0][i] = map[0][i + 1];
      }

      clean[0][0] = map[0][1];
      for (int i = 1; i <= upperCleaner[0] - 1; i++) {
        clean[i][0] = map[i - 1][0];
      }
      clean[upperCleaner[0]][1] = 0;

      // 아래쪽 공기청정기
      for (int i = 2; i < c; i++) {
        clean[lowerCleaner[0]][i] = map[lowerCleaner[0]][i - 1];
      }

      for (int i = lowerCleaner[0] + 1; i < r; i++) {
        clean[i][c - 1] = map[i - 1][c - 1];
      }

      for (int i = c - 2; i > 0; i--) {
        clean[r - 1][i] = map[r - 1][i + 1];
      }
      clean[r - 1][0] = map[r - 1][1];
      for (int i = r - 2; i > lowerCleaner[0]; i--) {
        clean[i][0] = map[i + 1][0];
      }

      clean[lowerCleaner[0]][1] = 0;

      map = clean.clone();
    }

    int result = 0;
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (map[i][j] > 0) {
          result += map[i][j];
        }
      }
    }
    System.out.println(result);
  }
}
