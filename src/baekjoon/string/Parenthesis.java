//백준 9012번 실버4 괄호
package baekjoon.string;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Parenthesis {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] parenthesis = new String[n];

        for (int i = 0; i < n; i++) {
            parenthesis[i] = sc.next();
        }

        Arrays.stream(parenthesis).forEach(s -> System.out.println(isVps(s)));
    }

    static String isVps(String str){

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {

            if(str.charAt(i)=='(') stack.push('(');

            else if(str.charAt(i)==')'){
                if(stack.isEmpty()) return "NO";
                else if(stack.peek()=='(') stack.pop();
                else stack.push(')');
            }
        }

        if(stack.isEmpty()) return "YES";
        else return "NO";
    }
}

