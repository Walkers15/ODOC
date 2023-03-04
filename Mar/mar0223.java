// https://www.acmicpc.net/problem/2438

package Mar;

import java.util.Scanner;

public class mar0223 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int n = sc.nextInt();
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= i; j++) {
        sb.append("*");
      }
      sb.append("\n");
    }
    System.out.println(sb);
    sc.close();
  }
}
