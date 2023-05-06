// https://www.acmicpc.net/problem/10871 X보다 작은 수
package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class May0523 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int x = Integer.parseInt(st.nextToken());

    StringBuilder sb = new StringBuilder();
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      int k = Integer.parseInt(st.nextToken());
      if (x > k) {
        sb.append(k + " ");
      }
    }
    System.out.println(sb);
  }
}
