// https://www.acmicpc.net/problem/9375

import java.util.HashMap;
import java.util.Scanner;

public class feb2623 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    for (int k = 0; k < t; k++) {
      test(sc);
    }
    sc.close();
  }

  public static void test(Scanner sc) {
    HashMap<String, Integer> closet = new HashMap<>();
    int n = sc.nextInt();
    for (int i = 0; i < n; i++) {
      sc.next();
      String type = sc.next();
      // System.out.println(type);
      if (closet.get(type) == null) {
        closet.put(type, 2);
      } else {
        int count = closet.get(type);
        closet.put(type, ++count);
      }
    }

    int result = 1;
    for (int count : closet.values()) {
      result *= count;
    }

    System.out.println(result - 1);
  }
}
