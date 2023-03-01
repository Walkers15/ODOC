package Jan;

// https://www.acmicpc.net/problem/1620
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Map.Entry;

public class jan2623 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    HashMap<String, Number> dogam = new HashMap<String, Number>();
    String[] countStrig = sc.nextLine().split(" ");
    int monsterCount = Integer.parseInt(countStrig[0]);
    int questionCount = Integer.parseInt(countStrig[1]);
    ArrayList<String> dogamByIndex = new ArrayList<String>();
    // System.out.println(monsterCount + " " + questionCount);
    for (int i = 0; i < monsterCount; i++) {
      String name = sc.nextLine();
      // System.out.println(name + i);
      dogam.put(name, i + 1);
      dogamByIndex.add(i, name);
    }
    // System.out.println(dogamByIndex.toString());
    // System.out.println("Input Question");
    // System.out.println("pikachu: " + dogam.get("Pikachu"));
    for (int i = 0; i < questionCount; i++) {
      String question = sc.nextLine();
      if (isNumber(question)) {
        int index = Integer.parseInt(question);
        System.out.println(dogamByIndex.get(index - 1));
      } else {
        System.out.println(dogam.get(question));
      }
    }
    // System.out.println(dogam.toString());
    sc.close();
  }

  public static boolean isNumber(String input) {
    boolean isNumber = true;
    try {
      Integer.parseInt(input);
    } catch (Exception e) {
      isNumber = false;
    }
    return isNumber;
  }

  public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
    for (Entry<T, E> entry : map.entrySet()) {
      if (Objects.equals(value, entry.getValue())) {
        return entry.getKey();
      }
    }
    return null;
  }
}