// https://www.acmicpc.net/problem/2420 사파리월드

package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Apr2723 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    long n = Long.parseLong(st.nextToken());
    long m = Long.parseLong(st.nextToken());

    System.out.println(Math.abs(n - m));
  }
}
