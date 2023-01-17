//https://www.acmicpc.net/problem/15651
//System.out.print는 상대적으로 시간이 오래 걸리는 메소드이므로, 출력이 많은 예제에서 시간 초과가 나는 경우에는 StringBuilder를 사용해볼 것

import java.util.Scanner;

public class feb0620_BT {
    static int n, m;
    static int[] arr = new int[9];
    static StringBuilder sb = new StringBuilder();

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        dfs(0);
        System.out.println(sb);
        sc.close();
    }

    public static void dfs(int num) {

        if (num == m) {
            for (int i = 0; i < m; i++)
                sb.append(arr[i] + " ");
            sb.append("\n");
        } else {
            for (int i = 1; i <= n; i++) {
                arr[num] = i;
                dfs(num + 1);
            }
        }
    }
}
