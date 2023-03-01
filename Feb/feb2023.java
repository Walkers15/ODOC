package Feb;

// https://www.acmicpc.net/problem/11659
import java.util.Scanner;

public class feb2023 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StringBuilder sb = new StringBuilder();
    int n = sc.nextInt();
    int m = sc.nextInt();
    int[] arr = new int[n];
    int[] sum = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = sc.nextInt();
    }
    sum[0] = arr[0];
    for (int i = 1; i < n; i++) {
      sum[i] = sum[i - 1] + arr[i];
    }

    for (int i = 0; i < m; i++) {
      int left = sc.nextInt() - 2;
      int sumLeft = left < 0 ? 0 : sum[left];
      int right = sc.nextInt() - 1;
      sb.append(sum[right] - sumLeft + "\n");
    }

    sc.close();
    System.out.print(sb);
  }
}
