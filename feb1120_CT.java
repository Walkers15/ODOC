
//PA 코딩테스트 알고리즘문제
//트럭 적재용량, 물건의 갯수와 무게 주어질 때 트럭이 최소 몇대 필요한가
//DP로 풀었어야 하는 거 같은데 그리디로 풀어서 TC 28개중 20개 정답. DP로 푸는 법 연구해보면 좋을 듯?
import java.util.*;

public class feb1120_CT {
    public int solution(int M, int[] load) {
        int answer = 0;
        Integer[] arr = new Integer[load.length];
        int[] box = new int[41]; // 인덱스 : 무게, 값 : 물건갯수
        for (int i = 0; i < load.length; i++) {
            box[load[i]]++;
            arr[i] = load[i];
        }
        Arrays.sort(arr, Collections.reverseOrder());
        int idx = 0;
        int count = load.length;
        int cur = M;
        if (arr[0] > M)
            return -1;
        while (count != 0) {
            if (box[arr[idx]] > 0) {
                box[arr[idx]]--;
                cur -= arr[idx];
                idx++;
                count--;
                for (int i = cur; i >= 0 && cur > 0; i--) {
                    if (box[i] > 0) {
                        box[i]--;
                        cur -= i;
                        count--;
                    }
                }
                answer++;
                cur = M;
            } else {
                cur = M;
                idx++;
            }
        }
        return answer;

    }
}
/*
 * 1. load 정렬, 무게별로 배열 만들어 갯수 저장
 * 2. 순차 선택
 * 3. 하나 고르고 용량 남으면, 딱 맞는 용량의 물건 찾기
 * 4. 있으면 적재 없으면 fracioal하게 넣을 수 있는
 */
