// https://www.acmicpc.net/problem/11444

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mar0423 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long n = Long.parseLong(br.readLine());
    br.close();

    n--;

    long[][] fibo = new long[][] { { 1, 1 }, { 1, 0 } };
    long[][] result = new long[][] { { 1, 0 }, { 0, 1 } };

    while (n > 0) {
      if (n % 2 == 1) {
        result = multiply(result, fibo);
      }

      fibo = multiply(fibo, fibo);

      n /= 2;
    }

    System.out.println(result[0][0]);
  }

  public static long[][] multiply(long[][] mt1, long[][] mt2) {
    long[][] result = new long[2][2];
    int divisor = 1000000007;
    result[0][0] = (mt1[0][0] * mt2[0][0] + mt1[0][1] * mt2[1][0]) % divisor;
    result[0][1] = (mt1[0][0] * mt2[0][1] + mt1[0][1] * mt2[1][1]) % divisor;
    result[1][0] = (mt1[1][0] * mt2[0][0] + mt1[1][1] * mt2[1][0]) % divisor;
    result[1][1] = (mt1[1][0] * mt2[0][1] + mt1[1][1] * mt2[1][1]) % divisor;

    return result;
  }
}
