
import java.util.Scanner;

public class feb2102_prime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cur;
        boolean prime = true;
        int result = 0;
        for (int i = 0; i < n; i++) {
            cur = sc.nextInt();
            if (cur == 1)
                continue;
            for (int j = 2; j <= Math.sqrt(cur); j++) {
                if (cur % j == 0)
                    prime = false;
            }
            if (prime)
                result++;
            else
                prime = true;
        }
        System.out.println(result);
        sc.close();
    }
}
