// https://www.acmicpc.net/problem/2618

package May;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class May1423_DP_Recursive {
  static int cases[][];
  static int dp[][];
  static int n, w;

  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    w = Integer.parseInt(br.readLine());

    cases = new int[w + 1][2];
    // dp[x][y] = 첫 번째 경찰차가 x 번째 사건 위치, 두 번째 경찰차가 y 번째 사건 위치일때까지 이동하는 거리의 합의 최솟값
    dp = new int[w + 1][w + 1];

    for (int i = 1; i <= w; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      cases[i][0] = Integer.parseInt(st.nextToken()) - 1;
      cases[i][1] = Integer.parseInt(st.nextToken()) - 1;
    }

    int result = allocation(1, 0, 0);
    StringBuilder sb = new StringBuilder();
    sb.append(result + "\n");

    // 경로 역추적
    int firstIndex = 0;
    int secondIndex = 0;

    for (int i = 1; i <= w; i++) {
      int firstMove = calculateDistance(i, firstIndex, true);
      if (dp[firstIndex][secondIndex] == dp[i][secondIndex] + firstMove) {
        // dp[현재][현재] = dp[1번 경찰차가 사건맡음][2번은 가만히] + 1이 이동한 거리이면 1이 이동한거임
        // 아니라면 2가 이동한거
        firstIndex = i;
        sb.append("1\n");
      } else {
        secondIndex = i;
        sb.append("2\n");
      }
    }

    System.out.print(sb);
  }

  public static int allocation(int index, int first, int second) {
    if (index > w) {
      return 0;
    }

    if (dp[first][second] != 0) {
      return dp[first][second];
    }

    int firstMove = allocation(index + 1, index, second) + calculateDistance(index, first, true);
    int secondMove = allocation(index + 1, first, index) + calculateDistance(index, second, false);

    if (firstMove < secondMove) {
      dp[first][second] = firstMove;
    } else {
      dp[first][second] = secondMove;
    }

    return dp[first][second];
  }

  public static int calculateDistance(int caseIndex, int carIndex, boolean isFirstCar) {
    int fromX, fromY, toX, toY;

    if (carIndex == 0) {
      // carIndex가 0인 경우는 초기상태뿐임 (사건 입력을 1부터 w까지 받았으므로)
      // 첫 번째 움직인 경우 1번 경찰차
      if (isFirstCar) {
        fromX = 0;
        fromY = 0;
      } else {
        // 2번 경찰차의 시작 위치는 n,n 임
        fromX = n - 1;
        fromY = n - 1;
      }
    } else {
      fromX = cases[carIndex][0];
      fromY = cases[carIndex][1];
    }

    toX = cases[caseIndex][0];
    toY = cases[caseIndex][1];

    return Math.abs(fromX - toX) + Math.abs(fromY - toY);
  }

}
