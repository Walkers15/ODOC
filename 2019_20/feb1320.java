
//https://www.acmicpc.net/problem/14888
//백트래킹 DFS
//왜 if문 안에서 연산자의 갯수를 빼지 않고 함수 호출할 때 연산자의 갯수를 빼야 하는가?
//그래야 이후에 호출되는 함수들이 앞의 함수에서 연산자를 사용한 여부와 관계없이 자신의 연산자만 뺄 수 있기 때문
/*
백트래킹 : 하나를 검사하고 조건을 만족하지 않는 경우 이후를 검사하지 않고 다음 검사 조건으로 넘어가는 일종의 문제 풀이 방식
DFS : 그래프에서 적용할 수 있는 탐색 방식(정형화된 알고리즘), 이 때 한 조건을 검사하고 일치하지 않는 경우 다음으로 넘어가는 방식을 백트래킹이라고 생각할 수 있음
*/
import java.util.Scanner;

public class feb1320 {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int[] op = new int[] { sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt() };
        count = op[0] + op[1] + op[2] + op[3];
        dfs(arr, op[0], op[1], op[2], op[3], 0, arr[0]);// n : 지금까지 사용한 연산자의 갯수 num : 지금까지 연산자와 숫자를 이용하여 만든 수
        System.out.print(max + "\n" + min);
        sc.close();
    }

    static void dfs(int[] arr, int p, int m, int mt, int d, int n, int num) {
        // System.out.println("dfs");
        if (n != count) {
            if (p > 0) {
                // 여기서 p--; dfs(...); 하게 되면, 밑에 있는 다른 dfs 호출시에도 p가 1 감소된 상태로 들어가서
                // 올바른 완전 탐색이 이루어지지 않는다
                // 고로 함수 호출 하면서 까야 잘 된다
                dfs(arr, p - 1, m, mt, d, n + 1, num + arr[n + 1]);
            }
            if (m > 0) {
                dfs(arr, p, m - 1, mt, d, n + 1, num - arr[n + 1]);
            }
            if (mt > 0) {
                dfs(arr, p, m, mt - 1, d, n + 1, num * arr[n + 1]);
            }
            if (d > 0) {
                dfs(arr, p, m, mt, d - 1, n + 1, num / arr[n + 1]);
            }
        } else {
            if (num > max)
                max = num;
            if (num < min)
                min = num;
        }
    }
}
