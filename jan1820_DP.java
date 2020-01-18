//https://www.acmicpc.net/problem/1904
//점화식 공부_계속 생각해볼 것
import java.util.*;
public class jan1820{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long bin[] = new long[n+2];
        bin[0] = 0;
        bin[1] = 1;
        bin[2] = 2;
        for(int i = 3 ; i <= n ; i++) {
            bin[i] = bin[i - 1] + bin[i - 2];
            bin[i] %= 15746;
        }
        System.out.println(bin[n]);
    }
}
