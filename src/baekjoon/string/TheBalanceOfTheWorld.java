//백준 4949번 실버4 균형잡힌 세상
package baekjoon.string;

import java.util.Scanner;
import java.util.Stack;

public class TheBalanceOfTheWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if(input.equals(".")) break;
            sb.append(isBalance(input));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static String isBalance(String input) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)=='(') stack.push('(');
            else if(input.charAt(i)=='[') stack.push('[');

            else if(input.charAt(i)==')'){
                if(stack.isEmpty()) return "no";
                else{
                    if(stack.peek()=='(') stack.pop();
                    else return "no";
                }
            }
            else if(input.charAt(i)==']'){
                if(stack.isEmpty()) return "no";
                else{
                    if(stack.peek()=='[') stack.pop();
                    else return "no";
                }
            }
        }

        if(stack.isEmpty()) return "yes";
        else return "no";
    }
}
