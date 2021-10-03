// 프로그래머스 level2 카카오프렌즈 단체 사진 찍기

package programmers.level2;

public class GroupPhoto {

    static int answer;

    public int solution(int n, String[] data) {
        char[] members = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

        answer = 0;
        permutation(members, 0, members.length, data);

        return answer;
    }

    static void permutation(char[] members, int depth, int n, String[] data) {
        if (depth == n) {
            // 조건 맞는지 확인
            if (checkConditions(members, data)) {
                answer++;
            }
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(members, depth, i);
            permutation(members, depth + 1, n, data);
            swap(members, depth, i);
        }
    }

    static void swap(char[] arr, int depth, int i) {
        char temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    private static boolean checkConditions(char[] members, String[] data) {

        for (int i = 0; i < data.length; i++) {
            char memberL = data[i].charAt(0);
            char memberR = data[i].charAt(2);
            char condition = data[i].charAt(3);
            int distance = Character.getNumericValue(data[i].charAt(4));

            if (!checkOneCondition(members, memberL, memberR, condition, distance)) {
                return false;
            }
        }

        return true;
    }

    private static boolean checkOneCondition(char[] members, char memberL, char memberR,
                                             char condition, int distance) {
        int indexOfMemberL = 0;
        int indexOfMemberR = 0;

        for (int j = 0; j < members.length; j++) {
            if (members[j] == memberL) {
                indexOfMemberL = j;
            }
            if (members[j] == memberR) {
                indexOfMemberR = j;
            }
        }

        int dist = Math.abs(indexOfMemberL - indexOfMemberR) - 1;

        if (condition == '=') {
            if (dist != distance) return false;
        }
        else if (condition == '<') {
            if (dist >= distance) return false;
        }
        else {
            if (dist <= distance) return false;
        }

        return true;
    }

}
