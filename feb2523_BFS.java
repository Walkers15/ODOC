// https://www.acmicpc.net/problem/16928

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

enum LocationType {
  Load,
  Ladder,
  Snake;
}

public class feb2523_BFS {
  public static void main(String[] args) {
    Location[] map = new Location[101];
    boolean[] visited = new boolean[101];

    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    for (int i = 0; i < 100; i++) {
      map[i] = new Location(LocationType.Load, 0);
    }

    for (int i = 0; i < n; i++) {
      map[sc.nextInt()] = new Location(LocationType.Ladder, sc.nextInt());
    }

    for (int i = 0; i < m; i++) {
      map[sc.nextInt()] = new Location(LocationType.Snake, sc.nextInt());
    }

    sc.close();

    Queue<int[]> queue = new LinkedList<>();
    visited[1] = true;
    queue.add(new int[] { 1, 0 });
    int[] diff = new int[] { 1, 2, 3, 4, 5, 6 };

    while (!queue.isEmpty()) {
      int[] current = queue.poll();
      int position = current[0];
      int moveCount = ++current[1];
      for (int i = 0; i < 6; i++) {
        int newPosition = position + diff[i];
        if (!visited[newPosition]) {
          if (position + diff[i] == 100) {
            System.out.println(moveCount);
            return;
          }

          Location location = map[newPosition];
          switch (location.type) {
            case Load:
              visited[newPosition] = true;
              queue.add(new int[] { newPosition, moveCount });
              break;
            case Ladder:
            case Snake:
              visited[location.target] = true;
              queue.add(new int[] { location.target, moveCount });
              break;
          }
        }

      }
    }
  }

  static class Location {
    public static LocationType Load;
    LocationType type;
    int target;

    Location(LocationType type, int target) {
      this.type = type;
      this.target = target;
    }
  }
}
