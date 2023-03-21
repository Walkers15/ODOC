// https://www.acmicpc.net/problem/9935 문자열 폭발
// String Class는 매번 불변하는 문자열을 생성하기 위해 힙 메모리를 사용하므로 연산이 많으면 메모리를 많이 사용함
// 그런 경우에는 StringBuffer(Thread Safe) / StringBuilder(성능 좋음, Unsafe) 사용

package Mar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class mar2123_String {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder str = new StringBuilder(br.readLine());
    String bomb = br.readLine();
    int bombLength = bomb.length();
    StringBuilder sb = new StringBuilder();
    int index = 0;
    while (index < str.length()) {
      sb.append(str.charAt(index));
      if (str.charAt(index) == bomb.charAt(bombLength - 1) && sb.length() >= bombLength) {
        if (sb.substring(sb.length() - bombLength).contains(bomb)) {
          sb.delete(sb.length() - bombLength, sb.length());
        }
      }
      index++;
    }

    System.out.println(sb.length() == 0 ? "FRULA" : sb);
  }
}
