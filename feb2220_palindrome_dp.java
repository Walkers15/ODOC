
//https://www.acmicpc.net/problem/10942
//DP_펠린드롬
//주석 친 곳 유심히 볼 것
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class feb2220_palindrome_dp {
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dp = new int[n][n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        int loop = Integer.parseInt(br.readLine());
        for (int i = 0; i < loop; i++) {
            st = new StringTokenizer(br.readLine());
            if (palin(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1) == 2)
                sb.append("1\n");
            else
                sb.append("0\n");
        }
        System.out.print(sb);
    }

    static int palin(int start, int end) {
        if (dp[start][end] == 0) {
            if (start == end)
                return 2;
            else if (start == end + 1) {
                if (arr[start] == arr[end])
                    return 2;
                else
                    return 1;
            } else {
                if (arr[start] != arr[end])
                    return 1;
                else
                    return (dp[start][end] = palin(start + 1, end - 1));
                // else return palin(start+1,end-1); 왜 이거 쓰면 더 느릴까요?
            }
        } else
            return dp[start][end];
    }
}
