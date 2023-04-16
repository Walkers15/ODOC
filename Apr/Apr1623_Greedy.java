// https://www.acmicpc.net/problem/1202 보석 도둑
package Apr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Apr1623_Greedy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Jewelry> jewelryList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            jewelryList.add(new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        ArrayList<Integer> bags = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(bags);
        Collections.sort(jewelryList);

        PriorityQueue<Jewelry> queue = new PriorityQueue<Jewelry>((Jewelry j1, Jewelry j2) -> {
            return j2.price - j1.price;
        });

        long result = 0;
        int index = 0;
        for (int i = 0; i < k; i++) {
            int capacity = bags.get(i);
            // 현재 가방에 담을 수 있는 가장 큰 무게까지 queue에 집어넣음
            while (index < n) {
                if (jewelryList.get(index).weight <= capacity) {
                    queue.add(jewelryList.get(index));
                    index++;
                } else {
                    break;
                }
                
            }
            if (!queue.isEmpty()) {
                result += queue.poll().price;
            }
        }

        System.out.println(result);
        
    }

    static class Jewelry implements Comparable<Jewelry> {
        int weight;
        int price;

        Jewelry(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewelry o) {
            // 무게에 대해서는 오름차순
            return this.weight - o.weight;
        }
    }
}
