// 프로그래머스 level2 메뉴 리뉴얼

package programmers.level2;

import java.util.*;

public class MenuRenewal {

    static Map<String, Integer> courses;
    static List<String> results;

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        courses = new HashMap<>();
        results = new ArrayList<>();

        for (int i = 0; i < course.length; i++) {

            int courseNum = course[i];

            for (int j = 0; j < orders.length; j++) {

                String order = orders[j];
                addCourseMap(order, courseNum);
            }

            List<Map.Entry<String, Integer>> list = new ArrayList<>(courses.entrySet());
            sortMap(list);

            addResults(list);

            courses.clear();
        }

        Collections.sort(results);

        answer = new String[results.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = results.get(i);
        }

        return answer;
    }

    private void addResults(List<Map.Entry<String, Integer>> list) {

        if (list.isEmpty()) return;

        String key = list.get(0).getKey();

        int num = courses.get(key);

        Iterator<Map.Entry<String, Integer>> iter = list.iterator();
        while (iter.hasNext()) {
            key = iter.next().getKey();

            if (courses.get(key) == num && num > 1) {
                results.add(key);
            }
        }
    }

    private void sortMap(List<Map.Entry<String, Integer>> list) {
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
    }

    static void addCourseMap(String order, int courseNum) {

        char[] orderArr = order.toCharArray();
        int n = orderArr.length;
        combination(orderArr, new boolean[n], 0, n, courseNum);
    }

    static void combination(char[] order, boolean[] visited, int start, int n, int r) {
        if (r == 0) {
            String temp = "";

            for (int i = 0; i < n; i++) {
                if (visited[i]) temp += order[i] + "";
            }

            char[] tempArr = temp.toCharArray();
            Arrays.sort(tempArr);
            temp = new String(tempArr);

            if (!courses.containsKey(temp)) courses.put(temp, 1);
            else {
                Integer count = courses.get(temp);
                courses.put(temp, count + 1);
            }

            return;
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(order, visited, i + 1, n, r - 1);
            visited[i] = false;
        }
    }
}
