// https://www.acmicpc.net/problem/11382 꼬마 정민

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

public class Apr2323 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    BigDecimal a = new BigDecimal(st.nextToken());
    BigDecimal b = new BigDecimal(st.nextToken());
    BigDecimal c = new BigDecimal(st.nextToken());

    System.out.println((a.add(b)).add(c));

  }
}
