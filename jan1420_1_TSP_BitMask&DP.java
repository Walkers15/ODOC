//https://www.acmicpc.net/problem/2098
//DP 배열에서 방문한 마을 표시를 위해 비트마스크 사용(visited)
//DP 식 세우고 식 연산하는 법 계속 생각해 볼 것
import java.util.Scanner;

public class jan1420_1 {
    static int[][] map = new int[16][16];
    static int[][] dp = new int[16][1 << 16];
    static int n, visited;
    static int INF = Integer.MAX_VALUE - 1000000;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(tsp(0, 1));
    }

    static int tsp(int current, int visited) {
        if ((visited == (1 << n) - 1)) { //모든 마을을 탐색한 경우
            if (map[current][0] == 0) return INF;
            return map[current][0];
        }
        if (dp[current][visited] != 0)//이미 방문한 적이 있는 경우
            return dp[current][visited];//그떄 만든 값 리턴
        dp[current][visited] = INF; //INF로 초기화
        for (int k = 0; k < n; k++) {
            int next = 1 << k;//k번째 마을, 비트연산(visited 에서 비트 빼 오는 데 사용)
            if (map[current][k] == 0 || (visited & next) > 0) continue;
            dp[current][visited] = Math.min(dp[current][visited], tsp(k, visited | next) + map[current][k]);
        }
        return dp[current][visited];
    }
}
