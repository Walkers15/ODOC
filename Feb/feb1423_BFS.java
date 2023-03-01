package Feb;
// https://www.acmicpc.net/problem/7569

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class feb1423_BFS {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Queue<int[]> queue = new LinkedList<>();
    int m = sc.nextInt();
    int n = sc.nextInt();
    int h = sc.nextInt();

    int[][][] box = new int[h][n][m];

    int underCook = 0;

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < n; j++) {
        for (int k = 0; k < m; k++) {
          box[i][j][k] = sc.nextInt();
          if (box[i][j][k] == 1) {
            queue.add(new int[] { i, j, k, 0 });
          } else if (box[i][j][k] == 0) {
            underCook++;
          }
        }
      }
    }

    sc.close();

    if (underCook == 0) {
      System.out.println("0");
      return;
    }

    ArrayList<int[]> diff = new ArrayList<>();
    diff.add(new int[] { 1, 0, 0 });
    diff.add(new int[] { -1, 0, 0 });
    diff.add(new int[] { 0, 1, 0 });
    diff.add(new int[] { 0, -1, 0 });
    diff.add(new int[] { 0, 0, 1 });
    diff.add(new int[] { 0, 0, -1 });

    while (!queue.isEmpty()) {
      int[] currentPosition = queue.poll();
      int z = currentPosition[0];
      int y = currentPosition[1];
      int x = currentPosition[2];
      int days = currentPosition[3];
      // System.out.println(x + " " + y + " " + z + " " + days);
      days++;
      for (int i = 0; i < 6; i++) {
        int[] currentDiff = diff.get(i);
        int newX = x + currentDiff[2];
        int newY = y + currentDiff[1];
        int newZ = z + currentDiff[0];

        if (newX >= 0 && newX < m && newY >= 0 && newY < n && newZ >= 0 && newZ < h) {
          if (box[newZ][newY][newX] == 0) {
            underCook--;
            if (underCook == 0) {
              System.out.println(days);
              return;
            }
            box[newZ][newY][newX] = 1;
            queue.add(new int[] { newZ, newY, newX, days });
          }
        }
      }
    }

    System.out.println(-1);
  }
}
