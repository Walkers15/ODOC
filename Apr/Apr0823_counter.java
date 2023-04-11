// https://www.acmicpc.net/problem/10807 숫자 세기
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr0823_counter {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());

    }

    int v = Integer.parseInt(br.readLine());

    int result = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] == v) {
        result++;
      }
    }
    System.out.println(result);
  }
}