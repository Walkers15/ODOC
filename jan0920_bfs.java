
//https://www.acmicpc.net/problem/7576
//bfs 기본! 나중에 다시 풀어볼 것
import java.util.*;

public class jan0920_bfs {
    static int[][] box;
    static int m, n;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        box = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                box[i][j] = sc.nextInt();
            }
        }
        bfs();
        sc.close();
    }

    static class tomato {
        int x;
        int y;
        int date;

        public tomato(int x, int y, int date) {
            this.x = x;
            this.y = y;
            this.date = date;
        }
    }

    public static void bfs() {
        Queue<tomato> queue = new LinkedList<>();
        int[] dx = { 0, 0, 1, -1 };
        int[] dy = { 1, -1, 0, 0 };
        int day = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (box[i][j] == 1)
                    queue.offer(new tomato(i, j, 0));
            }
        }
        while (!queue.isEmpty()) {
            tomato cur = queue.poll();
            day = cur.date;
            for (int i = 0; i < 4; i++) {
                int x = cur.x + dx[i];
                int y = cur.y + dy[i];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    if (box[x][y] == 0) {
                        box[x][y] = 1;
                        queue.offer(new tomato(x, y, day + 1));
                    }
                }
            }
        }
        boolean all = true;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (box[i][j] == 0)
                    all = false;
            }
        }
        if (all)
            System.out.println(day);
        else
            System.out.println(-1);
    }
}
