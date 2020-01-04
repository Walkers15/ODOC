//서로소 찾는 알고리즘(13412)
//메모리 초과 줄이기 대작전! 사실 Math.sqrt메소드로 find문의 loop횟수 더 줄일 수 있긴 한데 맞았길래 걍 냄
import java.util.*;
public class jan0420 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int i = 0 ; i < n ; i++)
            System.out.println(find(sc.nextInt()));
    }
    static int find(int num){
        int j = 0;
        int result = 0;
        for(int i = 1 ; i*i <= num ; i++){
            if(num % i == 0){
                if(gcd(i,num/i) == 1)
                    result++;
            }
        }
        return result;
    }
    static int gcd(int a, int b){
        if(b == 0)
            return a;
        else
            return gcd(b,a%b);
    }
}

//1. 주어진 수의 약수를 찾는다(num % i == 0)
//2. 주어진 수에서 그 약수를 나눈 몫을 구한다.
//3. 둘의 최대공약수가 1인지 확인한다 - gcd_recursion
//4. 1이면 결과값을 증가시킨다
