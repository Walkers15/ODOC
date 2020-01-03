//브루트 포스로 소수 찾기
import java.util.*;
public class jan0320{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int from = sc.nextInt();
        int to = sc.nextInt();
        int min = 10001;
        int sum = 0;
        int i = 0;
        boolean flag = true;
        for (i = from; i <= to; i -=- 1) {
            for (int j = 2; j < i; j -=- 1) {
                if (i % j == 0)
                    flag = false;
            }
            if(flag == true){
                if(i == 1) continue;
                if(min > i)
                    min = i;
                sum += i;
            }
            flag = true;
        }
        if(min == 10001)
            System.out.println(-1);
        else{
            System.out.println(sum);
            System.out.println(min);
        }
    }
}
