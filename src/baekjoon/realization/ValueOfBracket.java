// 백준 2504번 실버2 괄호의 값

package baekjoon.realization;

import java.util.*;

public class ValueOfBracket {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        System.out.println(solution(str));
    }

    static int solution(String str) {
        if (!isValid(str)) return 0;

        int answer = calculate(str);

        return answer;
    }

    static int calculate(String str) {

        if (str.equals("")) return 1;
        if (str.equals("()")) return 2;
        if (str.equals("[]")) return 3;

        int result = 0;

        List<String> division = division(str);

        Iterator<String> iter = division.iterator();
        while (iter.hasNext()) {
            String next = iter.next();
            String temp = next.substring(1, next.length() - 1);

            if (next.startsWith("(")) {
                result += 2 * calculate(temp);
            }
            else if (next.startsWith("[")) {
                result += 3 * calculate(temp);
            }
        }

        return result;
    }

    static List<String> division (String str) {

        List<String> parts = new ArrayList<>();

        int start = 0;
        int end = 1;
        while (end <= str.length()) {

            String temp = str.substring(start, end);

            if (isValid(temp)) {
                parts.add(temp);
                start += temp.length();
            }
            end++;
        }

        return parts;
    }

    static boolean isValid(String s) {

        char[] arr = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            if (stack.isEmpty()) stack.add(arr[i]);

            else {
                Character peek = stack.peek();

                if (peek == '(') {
                    if (arr[i] == ')') stack.pop();
                    else if (arr[i] == '(' || arr[i] == '[') stack.add(arr[i]);
                    else if (arr[i] == ']') return false;
                }
                else if (peek == '[') {
                    if (arr[i] == ']') stack.pop();
                    else if (arr[i] == '[' || arr[i] == '(')stack.add(arr[i]);
                    else if (arr[i] == ')') return false;
                }
                else if (peek == ')' || peek == ']') return false;
            }
        }

        return stack.isEmpty();
    }
}
