import java.util.Arrays;
import java.util.Scanner;

public class feb1823 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int x = sc.nextInt();
    int y = sc.nextInt();
    int z = sc.nextInt();

    int[] arr = new int[] { x, y, z };
    boolean xy = x == y;
    boolean yz = y == z;
    boolean xz = x == z;

    if (xy && yz) {
      System.out.println(10000 + (x * 1000));
    } else if (xy || yz || xz) {
      int same = 0;
      if (xy || xz) {
        same = x;
      } else if (yz) {
        same = y;
      }
      System.out.println(1000 + (same * 100));
    } else {
      Arrays.sort(arr);
      System.out.println(arr[2] * 100);
    }
    sc.close();
  }
}
