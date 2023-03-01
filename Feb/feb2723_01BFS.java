package Feb;

// https://www.acmicpc.net/problem/13549

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class feb2723_01BFS {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int max = 200100;
    int[] cost = new int[max + 1];
    int n = sc.nextInt();
    int k = sc.nextInt();
    sc.close();
    Arrays.fill(cost, Integer.MAX_VALUE);
    LinkedList<Integer> deque = new LinkedList<>();

    cost[n] = 0;
    deque.add(n);

    while (!deque.isEmpty()) {

      int position = deque.poll();

      if (position == k) {
        System.out.println(cost[position]);
        return;
      }

      int[] newPositions = new int[] { position * 2, position + 1, position - 1 };
      for (int i = 0; i < 3; i++) {
        int newPosition = newPositions[i];
        int newCost = i == 0 ? cost[position] : cost[position] + 1;
        if (newPosition <= max && newPosition >= 0 && newCost < cost[newPosition]) {
          cost[newPosition] = newCost;
          if (i == 0) {
            deque.addFirst(newPosition);
          } else {
            deque.add(newPosition);
          }
        }
      }
    }
  }
}
