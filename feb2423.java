// https://www.acmicpc.net/problem/1107

import java.util.Arrays;
import java.util.Scanner;

public class feb2423 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    boolean[] button = new boolean[10];
    Arrays.fill(button, true);
    int m = sc.nextInt();

    for (int i = 0; i < m; i++) {
      button[sc.nextInt()] = false;
    }
    sc.close();

    int result = Math.abs(n - 100);
    for (int i = 0; i <= 999999; i++) {
      String str = Integer.toString(i);

      int len = str.length();

      boolean isBroken = false;
      // str을 만들기 위해 고장난 버튼을 눌러야 하는지 검사
      for (int j = 0; j < len; j++) {
        int num = Character.getNumericValue(str.charAt(j));
        if (!button[num]) {
          isBroken = true;
          break;
        }
      }

      if (!isBroken) {
        result = Math.min(len + Math.abs(n - i), result);
      }
    }

    System.out.println(result);
  }
}