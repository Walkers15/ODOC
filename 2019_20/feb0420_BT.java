
//https://www.acmicpc.net/problem/15650
//백트래킹은 왜 이렇게 되는 걸까요?
import java.util.Scanner;

public class feb0420_BT {
  static int n, m;
  static boolean[] visit = new boolean[9];
  static boolean fill = false;
  static int[] arr = new int[9];

  public static void main(String args[]) {
    Scanner sc = new Scanner(System.in);
    n = sc.nextInt();
    m = sc.nextInt();
    dfs(0);
    sc.close();
  }

  public static void dfs(int num) {
    if (num == m && fill == true) {
      for (int i = 0; i < m; i++)
        System.out.print(arr[i] + " ");
      System.out.println();
      fill = false;
    } else {
      for (int i = 1; i <= n; i++) {
        if (fill == true)
          fill = false;
        if (visit[i] == false) {
          if (num == 0) {
            if (m == 1)
              fill = true;
            arr[num] = i;
          } else if (arr[num - 1] < i) {
            if (num == m - 1)
              fill = true;
            arr[num] = i;
          }
          visit[i] = true;
          dfs(num + 1);
          visit[i] = false;
        }
      }
    }
  }
}
