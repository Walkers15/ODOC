
//https://www.acmicpc.net/problem/11054
//기본적인 LIS는 nlogn 알고리즘을 통해 풀 수 있지만, 이 문제처럼 각각의 항에 해당하는 LIS의 길이를 알아야 하는 문제는
//DP를 이용해서 각 항의 LIS의 길이를 알고 있어야 하기 때문에 n^2의 DP LIS 알고리즘 사용
//양쪽의 길이를 알아야 한다는 IDEA
import java.util.Scanner;

public class jan2420_BitonicArr_DP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            if (dp[i] == 0)
                dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (dp[i] < dp[j] + 1 && arr[i] > arr[j])
                    dp[i] = dp[j] + 1;
            }
        }
        int[] dp_back = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            if (dp_back[i] == 0)
                dp_back[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (dp_back[i] < dp_back[j] + 1 && arr[i] > arr[j])
                    dp_back[i] = dp_back[j] + 1;
            }
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            // System.out.println(dp[i] + " " + dp_back[i]);
            if (dp[i] + dp_back[i] > result)
                result = dp[i] + dp_back[i];
        }
        System.out.println(result - 1);
        sc.close();
    }
}
