
// https://www.acmicpc.net/problem/1697
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class jan3123_BFS {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Queue<int[]> queue = new LinkedList<>();
    int n = sc.nextInt();
    int k = sc.nextInt();
    sc.close();
    boolean[] visited = new boolean[1000001];
    if (n == k) {
      System.out.println(0);
      return;
    }

    visited[0] = true;
    queue.add(new int[] { n, 0 });

    while (!queue.isEmpty()) {
      int[] positionInfo = queue.poll();
      int currentPosition = positionInfo[0];
      int currentTime = positionInfo[1];
      currentTime++;
      if (currentPosition * 2 == k) {
        System.out.println(currentTime);
        break;
      } else if (currentPosition * 2 <= 100000 && !visited[currentPosition * 2]) {
        visited[currentPosition * 2] = true;
        queue.add(new int[] { currentPosition * 2, currentTime });
      }

      if (currentPosition - 1 == k) {
        System.out.println(currentTime);
        break;
      } else if (currentPosition - 1 >= 0 && !visited[currentPosition - 1]) {
        visited[currentPosition - 1] = true;
        queue.add(new int[] { currentPosition - 1, currentTime });
      }

      if (currentPosition + 1 == k) {
        System.out.println(currentTime);
        break;
      } else if (currentPosition + 1 <= 100000 && !visited[currentPosition + 1]) {
        visited[currentPosition + 1] = true;
        queue.add(new int[] { currentPosition + 1, currentTime });
      }
    }
  }

}