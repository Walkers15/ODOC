package Mar;

//https://www.acmicpc.net/problem/11053 가장 긴 증가하는 부분 수열
//LIS nlogn 알고리즘
//내일은 DP로 풀어볼 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class mar2223_LIS_LowerBound {
  static int length = 0;

  public static void main(String args[]) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++)
      arr[i] = Integer.parseInt(st.nextToken());

    int[] dp = new int[n + 2];
    int idx = 0;

    for (int i = 0; i < n; i++) {
      if (dp[idx] < arr[i]) {
        dp[++idx] = arr[i];
        length++;
      } else {
        int k = lowerBound(dp, arr[i]);
        dp[k] = arr[i];
      }
    }

    System.out.println(idx);
  }

  public static int lowerBound(int[] dp, int key) {
    int high = length;
    int low = 1;
    int mid = low + (high - low) / 2;
    while (low < high) {
      mid = low + (high - low) / 2;
      if (dp[mid] >= key) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    return low;
  }
}
