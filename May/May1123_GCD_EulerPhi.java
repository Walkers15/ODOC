// https://www.acmicpc.net/problem/11689 GCD(n, k) = 1

package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class May1123_GCD_EulerPhi {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    long n = Long.parseLong(br.readLine());

    long result = n;
    for (long i = 2; i * i <= n; i++) {
      if (n % i == 0) {
        result = result - result / i; // n * (1 - 1/p)
      }
      while (n % i == 0) {
        n = n / i;
      }
    }

    if (n != 1) {
      // i * i 로 조건문을 검사하므로 10같은 수가 들어왔을 때 5를 검사할 수 없음
      result = result - result / n;
    }

    System.out.println(result);
  }
}
