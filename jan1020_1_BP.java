
//https://www.acmicpc.net/problem/1436
//브루트 포스로 탐색하여 수 찾기
//자바는 정말 사기다,,,남들은 다 수 열심히 나눌 때 그냥 String으로 변환하는 녀석
import java.util.Scanner;

public class jan1020_1_BP {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        int i = 0;
        while (count != n) {
            String str = Integer.toString(i);
            if (str.contains("666"))
                count++;
            i++;
        }
        i--;
        System.out.println(i);
        sc.close();
    }
}
