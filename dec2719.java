import java.io.IOException;
import java.util.Scanner;

public class dec2719 {
    public static int count = 0;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String number = sc.nextLine();
        int num = Integer.parseInt(number.trim());
        hanoi(num-1,1,3,2);
        hanoi(1,1, 2, 3);
        hanoi(num-1,2,1, 3);
        sb.insert(0, count+"\n");
        System.out.print(sb);
    }
    static void hanoi(int num, int start, int mid, int target){
        if(num == 0){
        }
        else if(num == 1) {
            count++;
            sb.append(start + " " + target + "\n");
        }
        else {
            count++;
            hanoi(num - 1, start, target,mid);
            sb.append(start+" "+target+"\n");
            hanoi(num - 1, mid, start,target);
        }
    }
}
