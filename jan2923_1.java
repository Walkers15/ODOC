// https://www.acmicpc.net/contest/problem/931/1
import java.util.Scanner;

public class jan2923_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int s = sc.nextInt();

        if (s == 1 || t <= 11 || t >= 17) {
            System.out.println(280);
        } else {
            System.out.println(320);
        }
    }
}
