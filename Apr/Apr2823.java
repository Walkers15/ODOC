// https://www.acmicpc.net/problem/10872 팩토리얼
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Apr2823 {
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());

    int result = 1;
    while (n > 0) {
      result *= n;
      n--;
    }

    System.out.println(result);
  }
}
