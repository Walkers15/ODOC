//비트연산자 쓰는 법! 비트마스크 공부용
//https://www.acmicpc.net/problem/12813
import java.io.*;
public class jan1420 {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char a[];
        char b[];
        int[] r_1 = new int[100000];
        int[] r_2 = new int[100000];
        int[] r_3 = new int[100000];
        int[] r_4 = new int[100000];
        int[] r_5 = new int[100000];
        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();
        for(int i = 0 ; i < a.length ; i++){
            r_1[i] = (a[i]-'0')&(b[i]-'0');
            r_2[i] = (a[i]-'0')|(b[i]-'0');
            r_3[i] = (a[i]-'0')^(b[i]-'0');
            if(a[i]-'0' == 0) r_4[i] = 1;
            else r_4[i] = 0;
            if(b[i]-'0' == 0) r_5[i] = 1;
            else r_5[i] = 0;
        }
        for(int i = 0 ; i < a.length ; i++) System.out.print(r_1[i]);
        System.out.println();
        for(int i = 0 ; i < a.length ; i++) System.out.print(r_2[i]);
        System.out.println();
        for(int i = 0 ; i < a.length ; i++) System.out.print(r_3[i]);
        System.out.println();
        for(int i = 0 ; i < a.length ; i++) System.out.print(r_4[i]);
        System.out.println();
        for(int i = 0 ; i < a.length ; i++) System.out.print(r_5[i]);
        System.out.println();
    }
}
