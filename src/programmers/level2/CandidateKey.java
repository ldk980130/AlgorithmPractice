// 프로그래머스 level2 후보키

package programmers.level2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CandidateKey {

    static int answer = 0;

    public int solution(String[][] relation) {

        answer = 0;
        int col = relation[0].length;

        boolean[] visited = new boolean[col];
        Set<String> uniqueSet = new HashSet<>();

        for (int i = 1; i <= col; i++) {
            combination(relation, visited, 0, col, i, uniqueSet);
        }

        return answer;
    }

    static void combination(String[][] relation, boolean[] visited,
                            int start, int n, int r, Set<String> uniqueSet) {
        if (r == 0) {
            if (isUnique(relation, visited, n) && isMin(uniqueSet, visited)) {
                answer++;
            }
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(relation, visited, i + 1, n, r - 1, uniqueSet);
            visited[i] = false;
        }
    }

    static boolean isMin(Set<String> uniqueSet, boolean[] visited) {

        String temp = "";
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) temp += i;
        }

        Iterator<String> iter = uniqueSet.iterator();

        while (iter.hasNext()) {

            String key = iter.next();
            int count = 0;

            for (int i = 0; i < key.length(); i++) {
                if (temp.contains(key.charAt(i)+"")) {
                    count++;
                }
            }

            if (count == key.length()) return false;
        }

        uniqueSet.add(temp);

        return true;
    }



    static boolean isUnique(String[][] relation, boolean[] visited, int n) {
        String[] temp = new String[relation.length];
        Arrays.fill(temp, "");

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                for (int j = 0; j < relation.length; j++) {
                    temp[j] += relation[j][i];
                }
            }
        }

        Set<String> set = new HashSet<>();
        for (int i = 0; i < temp.length; i++) {
            set.add(temp[i]);
        }
        if (temp.length == set.size()) {
            return true;
        }

        return false;
    }
}
