// 프로그래머스 level2 괄호변환

package programmers.level2;

import java.util.Stack;

public class TranslatedBracket {

    public String solution(String p) {
        String answer = "";

        answer = makeValidString(p);

        return answer;
    }

    static String makeValidString (String p) {

        if (p.isBlank()) return "";

        String[] division = division(p);
        String U = division[0];
        String V = division[1];

        if (isValid(U)) return U + makeValidString(V);
        else return "(" + makeValidString(V) + ")" + reverse(U);
    }

    static String reverse(String U) {
        if (U.length() <= 2) return "";

        U =  U.substring(1, U.length() - 1);

        String answer = "";
        for (int i = 0; i < U.length(); i++) {
            if (U.charAt(i) == '(') answer += ")";
            else answer += "(";
        }

        return answer;
    }

    static String[] division(String p) {
        String U = "";
        String V = "";
        int n = p.length();

        for (int i = 0; i < n; i++) {
            U = p.substring(0, i + 1);
            V = p.substring(i + 1, n);

            if (isBalanced(U)) break;
        }

        String[] result = new String[2];
        result[0] = U;
        result[1] = V;

        return result;
    }

    static boolean isBalanced(String p) {
        int leftBlanket = 0;
        int rightBlanket = 0;

        if (p.length() == 0) return false;

        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') leftBlanket++;
            else if (p.charAt(i) == ')') rightBlanket++;
        }

        return leftBlanket == rightBlanket;
    }

    static boolean isValid (String p) {
        if (!isBalanced(p)) return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < p.length(); i++) {

            char blanket = p.charAt(i);

            if (stack.isEmpty()) stack.add(blanket);
            else {
                Character peek = stack.peek();
                if (peek == ')') break;

                if (peek == '(' && blanket == '(') stack.add(blanket);
                else if (peek == '(' && blanket == ')') stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
