//https://www.acmicpc.net/problem/10844
//DP 배열을 다차원으로 설계하는 방법도 고민해볼 것 - 우선 점화식 먼저 찾아보고, 1차로 안될 꺼 같으면 2차도 생각해보기
import java.util.Scanner;
public class jan2120_2 {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] dp = new long[101][11];
        for(int i = 1 ; i < 10 ; i++) dp[1][i] = 1;
        for(int i = 2 ; i <= n ; i++){
            dp[i][0] = dp[i-1][1];
            for(int j = 1 ; j <= 9 ; j++) dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
        }
        long result = 0;
        for(int i = 0 ; i <= 9 ; i++) result += dp[n][i];
        System.out.println(result % 1000000000);
    }
}
