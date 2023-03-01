package Feb;

// https://www.acmicpc.net/problem/16236
// BFS가 항상 최단거리가 아니라.. 일단 찾다가 문제의 조건대로 봤을때 더 빠른놈이 있을 수 있음

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class feb1623 {
  public static int[][] map;
  public static Queue<int[]> queue;
  public static int n;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    n = Integer.parseInt(br.readLine());

    map = new int[n][n];
    int[] fish = new int[7];
    queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      StringTokenizer line = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        map[i][j] = Integer.parseInt(line.nextToken());
        if (map[i][j] == 9) {
          queue.add(new int[] { i, j, 0 });
          map[i][j] = 0;
        } else {
          fish[map[i][j]]++;
        }
      }
    }

    if (fish[1] == 0) {
      // 처음부터 먹을수 있는 생선이 한마리도 없으면
      System.out.println(0);
      return;
    }

    int result = 0;
    int eatCount = 0;
    int size = 2;

    while (true) {
      int[] nextFish = bfs(size);
      if (nextFish == null) {
        System.out.println(result);
        return;
      } else {
        int x = nextFish[1];
        int y = nextFish[0];
        int time = nextFish[2];

        map[y][x] = 0;
        eatCount++;
        if (size == eatCount) {
          eatCount = 0;
          size++;
        }
        result = time;

        queue.add(new int[] { y, x, time });
      }
    }
  }

  public static int[] bfs(int size) {
    int[] nextEatFish = null;
    boolean[][] visited = new boolean[n][n];
    int[] first = queue.element();
    visited[first[0]][first[1]] = true;
    while (!queue.isEmpty()) {
      int[] currentInfo = queue.poll();
      int x = currentInfo[1];
      int y = currentInfo[0];
      int time = ++currentInfo[2];
      // 가장 위, 그중에서 왼쪽에 있는 물고기를 먼저 먹어야 함
      int[][] diff = new int[][] { new int[] { -1, 0 }, new int[] { 0, -1 }, new int[] { 0, 1 }, new int[] { 1, 0 } };
      for (int i = 0; i < 4; i++) {
        int newX = x + diff[i][1];
        int newY = y + diff[i][0];
        boolean canMove = newX >= 0 && newX < n && newY >= 0 && newY < n && !visited[newY][newX]
            && map[newY][newX] <= size;

        if (canMove) {
          visited[newY][newX] = true;
          int nextFish = map[newY][newX];
          if (nextFish != 0 && nextFish < size) {
            // 다음 이동할 위치에 나보다 사이즈가 작은 물고기가 있는 경우
            if (nextEatFish == null) {
              nextEatFish = new int[] { newY, newX, time };
            } else {
              if (nextEatFish[2] > time) {
                nextEatFish = new int[] { newY, newX, time };
              } else if (nextEatFish[2] == time) {
                if (nextEatFish[0] > newY) {
                  nextEatFish = new int[] { newY, newX, time };
                } else if (nextEatFish[0] == newY && nextEatFish[1] > newX) {
                  nextEatFish = new int[] { newY, newX, time };
                }
              }
            }
          }
          queue.add(new int[] { newY, newX, time });
        }
      }
    }
    return nextEatFish;
  }
}
