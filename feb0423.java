// https://www.acmicpc.net/problem/11279

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class feb0423 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());

    TreeMap<Integer, Integer> maxHeap = new TreeMap<>();

    for (int i = 0; i < n; i++) {
      int x = Integer.parseInt(br.readLine());
      if (x == 0) {
        if (maxHeap.size() == 0) {
          sb.append("0\n");
        } else {
          int max = maxHeap.lastKey();
          int count = maxHeap.get(max);
          sb.append(max).append("\n");
          count--;
          if (count == 0) {
            maxHeap.remove(max);
          } else {
            maxHeap.put(max, count);
          }
        }
      } else {
        Integer targetCount = maxHeap.get(x);
        if (targetCount == null) {
          maxHeap.put(x, 1);
        } else {
          targetCount++;
          maxHeap.put(x, targetCount);
        }
      }
    }
    System.out.print(sb);
    br.close();
  }
}