// https://www.acmicpc.net/problem/1629
package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar0523 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());

    br.close();

    long result = 1;
    long multiplier = a % c;
    while (b > 0) {
      // System.out.println(result + " " + a + " " + b + " " + c);
      if (b % 2 == 1) {
        result = (result * multiplier) % c;
      }
      multiplier = (multiplier % c * multiplier % c) % c;
      b /= 2;
    }

    System.out.println(result);
  }
}
