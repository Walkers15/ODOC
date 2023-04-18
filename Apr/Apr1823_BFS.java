// https://www.acmicpc.net/problem/12852 1로 만들기 2
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Apr1823_BFS {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    if (n == 1) {
      System.out.println("0\n1");
      return;
    }

    Queue<Node> queue = new LinkedList<>();
    ArrayList<Integer> start = new ArrayList<>();
    queue.add(new Node(start, n));

    boolean[] visit = new boolean[n + 1];
    visit[n] = true;

    ArrayList<Integer> result = null;

    while (!queue.isEmpty()) {
      Node node = queue.poll();
      int num = node.current;

      int[] nextArr = new int[] { num / 3, num / 2, num - 1 };
      boolean[] canGo = new boolean[] { num % 3 == 0, num % 2 == 0, true };

      for (int i = 0; i < 3; i++) {
        if (!canGo[i]) {
          continue;
        }

        int next = nextArr[i];
        if (next == 1) {
          node.trace.add(1);
          result = node.trace;
          queue.clear();
          break;
        } else if (!visit[next]) {

          visit[next] = true;
          queue.add(new Node(node.trace, next));
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(result.size() - 1 + "\n");
    result.stream().forEach(num -> sb.append(num + " "));
    System.out.println(sb);
  }

  static class Node {
    ArrayList<Integer> trace;
    int current;

    Node(ArrayList<Integer> trace, int current) {
      this.trace = new ArrayList<>(trace);
      this.trace.add(current);
      this.current = current;
    }
  }
}
