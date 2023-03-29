// https://www.acmicpc.net/problem/16953 A → B
package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class mar2923_BFS {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    long a = Long.parseLong(st.nextToken());
    long b = Long.parseLong(st.nextToken());

    Queue<long[]> queue = new LinkedList<>();

    queue.add(new long[] { a, (long) 1 });

    while (!queue.isEmpty()) {
      long[] current = queue.poll();
      long num = current[0];
      long count = current[1];

      long mul = num * 2;
      long addOne = num * 10 + 1;

      if (mul == b || addOne == b) {
        System.out.println(count + 1);
        return;
      }
      // System.out.println(mul + " " + addOne);
      if (mul < b) {
        queue.add(new long[] { mul, count + 1 });
      }

      if (addOne < b) {
        queue.add(new long[] { addOne, count + 1 });
      }
    }

    System.out.println(-1);
  }
}
