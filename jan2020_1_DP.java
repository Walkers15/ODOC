//https://www.acmicpc.net/problem/1932
//DP_다차원! 이제는 점화식 세우는 방법을 알 때도 됐다
//최대, 최소 > 저번 세션(n-1)에서의 최대,최소값 + 이번 세션의 비용
import java.util.Scanner;
public class jan2020_1 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n+2][n+2];
        int[][] dp = new int[n+2][n+2];
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j <= i ; j++){
                arr[i][j] = sc.nextInt();
            }
        }
        dp[0][0] = arr[0][0];
        dp[1][0] = arr[0][0] + arr[1][0];
        dp[1][1] = arr[0][0] + arr[1][1];
        for(int i = 2 ; i < n ; i++){
            dp[i][0] = dp[i-1][0] + arr[i][0];
            for(int j = 1 ; j < i ; j++){
                dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1]) + arr[i][j];
            }
            dp[i][i] = dp[i-1][i-1] + arr[i][i];
        }
        int result = 0;
        for(int i = 0 ; i < n ; i++){
            if(dp[n-1][i] > result) result = dp[n-1][i];
        }
        System.out.println(result);
    }
}
