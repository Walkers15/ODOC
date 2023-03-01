package Jan;

// https://www.acmicpc.net/problem/1074
import java.util.Scanner;

public class jan3023_DivideConquer {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int r = sc.nextInt();
    int c = sc.nextInt();
    int ans = 0;

    while (N != 0) {
      // 각 사분면 계산
      int divider = (int) (Math.pow(2, N - 1));
      int area = (int) (Math.pow(2, 2 * (N - 1)));

      if (r < divider && c < divider) {
        // 1사분면

      } else if (r < divider && c >= divider) {
        // 2사분면
        ans += area;
        c -= divider;
      } else if (r >= divider && c < divider) {
        // 3사분면
        ans += area * 2;
        r -= divider;
      } else if (r >= divider && c >= divider) {
        // 4사분면
        ans += area * 3;
        r -= divider;
        c -= divider;
      }
      N--;
    }

    System.out.println(ans);
    sc.close();
  }
}
