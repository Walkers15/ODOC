
//https://www.acmicpc.net/problem/1002
//터렛 문제! 기하학적 사고 
import java.util.*;

public class jan1120_turret {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            turret t1 = new turret(sc.nextInt(), sc.nextInt(), sc.nextInt());
            turret t2 = new turret(sc.nextInt(), sc.nextInt(), sc.nextInt());
            double d = Math.sqrt((Math.pow(t1.x - t2.x, 2) + Math.pow(t1.y - t2.y, 2)));
            if (t1.x == t2.x && t1.y == t2.y) {
                if (t1.r == t2.r)
                    System.out.println(-1);
                else
                    System.out.println(0);
            } else if (d == t1.r + t2.r)
                System.out.println(1);
            else if (d > t1.r + t2.r)
                System.out.println(0);
            else if (t1.r > t2.r + d)
                System.out.println(0);
            else if (t1.r == (t2.r + d))
                System.out.println(1);
            else if (t1.r + d < t2.r)
                System.out.println(0);
            else if (t1.r + d == t2.r)
                System.out.println(1);
            else
                System.out.println(2);
        }
        sc.close();
    }

    static class turret {
        int x, y, r;

        public turret(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }
}
