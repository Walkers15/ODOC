// https://www.acmicpc.net/problem/1019 책 페이지

package Jun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jun1623_숫자세기 {
  static int[] answer = new int[10];

  public static void main(String[] args) throws NumberFormatException, IOException {
    // 1. 문제 변경하기 (1 ~ N)에서 (A ~ B) 로, 이 때 A는 1의자리 0, B는 1의자리 9
    // 1 - 1. 위 과정에서 x++, y-- 하면서 해당 숫자의 1 ~ 9 등장횟수를 기록해야 함 (나머지, 나누기 활용함수)
    // 2. 이후 일의 자리부터 0 ~ 9가 나오는 횟수를 계산하는데, (20 ~ 59 라면 (5 - 2) + 1 로 구할 수 있음)
    // 2-1. 일의 자리는 그렇고, 십의 자리를 계산하려면 위 처럼 (A - B) + 1 한 후 10을 곱해줘야 함 왜냐면 (10, 11, 12
    // ~ 19로 총 10번이기 때문에)

    // 이렇게 계산하면 O(N)보다 빠르게 풀 수 있음!

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    int start = 1;

    int index = 1;
    while (start <= n) {
      // start의 1의자리를 0으로 만들기
      while (start % 10 != 0 && start <= n) {
        calc(start, index);
        start++;
      }

      if (n < start) {
        break;
      }

      // n의 1의자리를 9로 만들기
      while (n % 10 != 9 && start <= n) {
        calc(n, index);
        n--;
      }

      start /= 10;
      n /= 10;

      for (int i = 0; i < 10; i++) {
        answer[i] += (n - start + 1) * index;
      }

      index *= 10;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      sb.append(answer[i] + " ");
    }

    System.out.println(sb);

  }

  static void calc(int n, int index) {
    while (n > 0) {
      answer[n % 10] += index;
      n /= 10;
    }
  }
}
