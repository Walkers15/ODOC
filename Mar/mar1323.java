package Mar;

// https://www.acmicpc.net/problem/2407

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class mar1323 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    BigInteger dividend = new BigInteger("1");
    BigInteger divisor = new BigInteger("1");
    for (int i = n - m + 1; i <= n; i++) {
      dividend = dividend.multiply(new BigInteger(Integer.toString((i))));

    }

    for (int i = 1; i <= m; i++) {
      divisor = divisor.multiply(new BigInteger(Integer.toString((i))));

    }
    // System.out.println(dividend + " " + divisor);
    System.out.println(dividend.divide(divisor));
  }
}
