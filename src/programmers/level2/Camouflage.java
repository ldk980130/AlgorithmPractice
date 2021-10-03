// 프로그래머스 level2 위장

package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Camouflage {

    static int answer;
    static Map<String, Integer> clothesMap;

    public int solution(String[][] clothes) {

        answer = 1;
        clothesMap = new HashMap<>();
        List<String> typeList = new ArrayList<>();

        for (int i = 0; i < clothes.length; i++) {
            String type = clothes[i][1];

            if (!clothesMap.containsKey(type)) {
                clothesMap.put(type, 0);
                typeList.add(type);
            }

            Integer num = clothesMap.get(type);
            clothesMap.put(type, num + 1);
        }

        for (int i = 0; i < typeList.size(); i++) {
            // combination(typeList, new boolean[typeList.size()], 0, typeList.size(), i + 1);
            answer *= (clothesMap.get(typeList.get((i))) + 1);
        }
        answer--;

        return answer;
    }

    static void combination(List<String> list, boolean[] visited, int start, int n, int r) {
        if(r == 0) {
            count(list, visited);
            return;
        }

        for(int i=start; i<n; i++) {
            visited[i] = true;
            combination(list, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }

    private static void count(List<String> list, boolean[] visited) {

        int sum = 1;
        for (int i = 0; i < list.size(); i++) {
            if (visited[i] == true) {
                Integer num = clothesMap.get(list.get(i));
                sum *= num;
            }
        }
        answer += sum;
    }
}
