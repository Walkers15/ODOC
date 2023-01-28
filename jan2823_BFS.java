// https://www.acmicpc.net/problem/1012
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class jan2823_BFS {
    public static boolean[][] bat;
    public static boolean[][] visited;
    public static int m, n, earthworm;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            calculate(sc);
        }
        sc.close();
    }

    public static void calculate(Scanner sc) {
        m = sc.nextInt();
        n = sc.nextInt();
        int k = sc.nextInt();
        bat = new boolean[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            bat[x][y] = true;
        }

        earthworm = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (bat[i][j] && !visited[i][j]) {
                    earthworm++;
                    bfs(i, j);
                }
            }
        }

        System.out.println(earthworm);
    }

    public static void bfs(int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        visited[i][j] = true;
        queue.add(new int[]{i, j});
        while(!queue.isEmpty()) {
            int[] position = queue.poll();
            int x = position[0];
            int y = position[1];
            // 상하좌우 검사
            if (x - 1 >= 0 && bat[x - 1][y] && !visited[x - 1][y]) {
                visited[x - 1][y] = true;
                queue.add(new int[]{x - 1, y});
            }
            if (x + 1 < m && bat[x + 1][y] && !visited[x + 1][y]) {
                visited[x + 1][y] = true;
                queue.add(new int[]{x + 1, y});
            }
            if (y - 1 >= 0 && bat[x][y - 1] && !visited[x][y - 1]) {
                visited[x][y - 1] = true;
                queue.add(new int[]{x, y - 1});
            }
            if (y + 1 < n && bat[x][y + 1] && !visited[x][y + 1]) {
                visited[x][y + 1] = true;
                queue.add(new int[]{x, y + 1});
            }
        }

    }
}
