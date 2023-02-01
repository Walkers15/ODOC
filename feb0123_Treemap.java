
// https://www.acmicpc.net/problem/7662
// 정답코드: Java의 Treemap은 Red-Black Tree (B-Tree)를 구현하였으므로 worst case에 대해서도 nlogn 이 보장됨
// 다만 TreeMap의 경우 중복 key값을 가질 경우 Override되므로, value를 해당 key를 가진 원소의 갯수로 처리

import java.util.Scanner;
import java.util.TreeMap;

public class feb0123_Treemap {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();
    for (int i = 0; i < t; i++) {
      test(sc);
    }
    sc.close();
  }

  public static void test(Scanner sc) {
    int k = sc.nextInt();
    sc.nextLine();
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < k; i++) {
      String operation = sc.nextLine();
      char operator = operation.split(" ")[0].charAt(0);
      int n = Integer.parseInt(operation.split(" ")[1]);
      switch (operator) {
        case 'I':
          Integer count = map.get(n);
          if (count == null) {
            count = 1;
            map.put(n, count);
          } else {
            count++;
            map.put(n, count);
          }
          break;
        case 'D':
          if (map.size() == 0) {
            continue;
          }
          if (n == 1) {
            int targetCount = map.get(map.lastKey());
            targetCount--;
            if (targetCount == 0) {
              map.remove(map.lastKey());
            } else {
              map.put(map.lastKey(), targetCount);
            }
          } else {
            int targetCount = map.get(map.firstKey());
            targetCount--;
            if (targetCount == 0) {
              map.remove(map.firstKey());
            } else {
              map.put(map.firstKey(), targetCount);
            }
          }
          break;
      }
    }
    int size = map.size();
    if (size == 0) {
      System.out.println("EMPTY");
    } else {
      int max = map.lastKey();
      int min = map.firstKey();
      System.out.println(max + " " + min);
    }
  }
}
