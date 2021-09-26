//백준 1120 실버4 문자열

package baekjoon.string;

import java.util.Scanner;

public class String1120 {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();
        int lengthDifference = b.length() - a.length();

        int answer = 50;

        for (int i = lengthDifference; i >= 0; i--) {
            String tmp = b.substring(0, i) + a;
            tmp += b.substring(b.length() - (lengthDifference - i));

            int differ = calDifference(tmp, b);
            if (differ < answer) {
                answer = differ;
            }
        }

        System.out.println(answer);
    }

    static int calDifference(String a, String b) {

        int result = 0;

        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                result++;
            }
        }

        return result;
    }
}
