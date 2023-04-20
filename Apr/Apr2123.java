// https://www.acmicpc.net/problem/2166 다각형의 면적
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr2123 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    long[][] point = new long[n][2];

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      point[i][0] = Long.parseLong(st.nextToken());
      point[i][1] = Long.parseLong(st.nextToken());
    }

    double crossProduct = 0;

    // 신발끈
    // 오른쪽
    for (int i = 0; i < n; i++) {
      crossProduct += point[i][0] * point[(i + 1) % n][1];
    }

    // 왼쪽
    for (int i = 0; i < n; i++) {
      crossProduct -= point[i][0] * point[(i - 1 + n) % n][1];
    }

    System.out.printf("%.1f\n", Math.abs(crossProduct / 2));
  }
}
