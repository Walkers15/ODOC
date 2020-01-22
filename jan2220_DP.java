//https://www.acmicpc.net/problem/2156
//연속 세개에서 점화식이 왜 저렇게 나오는지 이해할 것!
import java.util.Scanner;
public class jan2220 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+3];
        for(int i = 1 ; i <= n ; i++)
            arr[i] = sc.nextInt();
        int[] dp = new int[n+3];
        dp[1] = arr[1];
        dp[2] = arr[1]+arr[2];
        for(int i = 3 ; i <= n ; i++){
            dp[i] = Math.max(dp[i-2]+arr[i],dp[i-3]+arr[i-1]+arr[i]);
            dp[i] = Math.max(dp[i-1],dp[i]);
        }
        System.out.println(dp[n]);
    }
}
