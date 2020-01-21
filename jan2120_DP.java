//https://www.acmicpc.net/problem/2579
//점화식의 경우의 수
import java.util.Scanner;
public class jan2120{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n+2];
        for(int i = 0 ; i < n ; i++){
            arr[i] = sc.nextInt();
        }
        int[] dp = new int[n+2];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0],arr[0]+arr[1]);
        dp[2] = Math.max(arr[0]+arr[2],arr[1]+arr[2]);
        for(int i = 3 ; i < n ; i++){
            dp[i] = Math.max(dp[i-2]+arr[i],dp[i-3]+arr[i-1]+arr[i]);
        }
        System.out.println(dp[n-1]);
    }
}
