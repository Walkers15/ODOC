
//https://www.acmicpc.net/problem/2748
//90번째까지 피보나치로 가면 수가 엄청 커지니까 long형 사용해야 함!
import java.util.*;

public class jan1620_Fibo_DP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] arr = new long[91];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        System.out.println(arr[n]);
        sc.close();
    }
}
