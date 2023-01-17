
//8980_택배
//그리디 적용하는 법 확인해 볼 것!
import java.util.*;

public class jan0220_greedy {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int vc = sc.nextInt(); // Village Count
        int Capacity = sc.nextInt();
        sc.nextLine();
        int bc = sc.nextInt();// boxCount
        sc.nextLine();
        String input;
        String[] inputarr;
        int[] boxdata = new int[3];
        ArrayList<box> arr = new ArrayList<>();
        for (int i = 0; i < bc; i++) {
            if (!sc.hasNextLine())
                break;
            input = sc.nextLine();
            inputarr = input.split(" ");
            boxdata[0] = Integer.parseInt(inputarr[0]);
            boxdata[1] = Integer.parseInt(inputarr[1]);
            boxdata[2] = Integer.parseInt(inputarr[2]);
            arr.add(new box(boxdata[0], boxdata[1], boxdata[2]));
        }
        Collections.sort(arr);
        int min;
        int result = 0;
        int[] dp = new int[vc];
        for (int i = 0; i < vc; i++)
            dp[i] = 0;

        for (int i = 0; i < bc; i++) {
            min = arr.get(i).count;
            for (int j = arr.get(i).from - 1; j < arr.get(i).to - 1; j++) {
                if (Capacity - dp[j] < arr.get(i).count) {// 이번 박스의 양이 현재 적재할 수 있는 양보다 큰 경우
                    if (min > Capacity - dp[j])
                        min = Capacity - dp[j];// 이번 박스에서 쪼개서 넣는 양을, 남은 트럭의 용량만큼 채워줌
                }
            }
            // System.out.println((arr.get(i).from-1) + " " + (arr.get(i).to-1));
            for (int j = arr.get(i).from - 1; j < arr.get(i).to - 1; j++)
                dp[j] += min;// 채운 용량만큼 더해줌
            result += min;

        }
        System.out.println(result);
        sc.close();
    }
}

class box implements Comparable<box> {
    int from;
    int to;
    int count;

    box(int from, int to, int count) {
        this.from = from;
        this.to = to;
        this.count = count;
    }

    @Override
    public int compareTo(box B) {
        if (this.to > B.to)
            return 1;
        else if (this.to == B.to) {
            if (this.from > B.from)
                return 1;
            else if (this.from < B.from)
                return -1;
            return 0;
        } else
            return -1;
    }

    public String toString() {
        return (this.from + " " + this.to + " " + this.count);
    }
}
