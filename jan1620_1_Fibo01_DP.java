
//https://www.acmicpc.net/problem/1003
import java.util.*;

public class jan1620_1_Fibo01_DP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int loop = sc.nextInt();
        int[][] arr = new int[41][2];
        arr[0][0] = 1;
        arr[0][1] = 0;
        arr[1][0] = 0;
        arr[1][1] = 1;
        for (int i = 0; i < loop; i++) {
            int n = sc.nextInt();
            if (arr[n][0] != 0)
                System.out.println(arr[n][0] + " " + arr[n][1]);
            else {
                for (int j = 2; j <= n; j++) {
                    arr[j][0] = arr[j - 1][0] + arr[j - 2][0];
                    arr[j][1] = arr[j - 1][1] + arr[j - 2][1];
                }
                System.out.println(arr[n][0] + " " + arr[n][1]);
            }
        }
        sc.close();
    }
}
