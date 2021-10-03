// 프로그래머스 [1차] 뉴스 클러스터링

package programmers.level2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class NewsClustering {
    public int solution(String str1, String str2) {
        int answer = 0;

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        List<String> str1Set = makeMultiSet(str1);
        List<String> str2Set = makeMultiSet(str2);

        if (str1Set.isEmpty() && str2Set.isEmpty()) {
            return 65536;
        }

        Iterator<String> iter = str1Set.iterator();
        double intersection = 0;
        double combination = (str1Set.size() + str2Set.size());

        while (iter.hasNext()) {
            String next = iter.next();
            if (str2Set.contains(next)) {
                intersection++;
                str2Set.remove(next);
            }
        }
        combination -= intersection;

        double j = intersection / combination;
        answer = (int) (j * 65536);

        return answer;
    }

    static List<String> makeMultiSet(String str) {
        char[] chars = str.toCharArray();
        List<String> list = new LinkedList<>();
        String pattern = "^[a-zA-Z]*$";

        for (int i = 1; i < chars.length; i++) {
            String temp = chars[i - 1] + "" + chars[i];
            if (Pattern.matches(pattern, temp)) {
                list.add(temp);
            }
        }
        return list;
    }
}
