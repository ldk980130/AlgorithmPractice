//백준 9935번 골드4 문자열 폭발

package baekjoon.string;

import java.util.Scanner;
import java.util.Stack;

public class EKSPLOZIJA {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        String explosion = sc.next();
        char last = explosion.charAt(explosion.length() - 1);

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.peek()==last && stack.size()>=explosion.length()) {
                String temp="";
                for (int j = explosion.length()-1; j >= 0; j--) {
                    temp = stack.pop() + temp;
                }

                if(!temp.equals(explosion)){
                    for (int j = 0; j < explosion.length(); j++){
                        stack.push(temp.charAt(j));
                    }
                }
            }
        }

        if(stack.isEmpty()) System.out.println("FRULA");
        else{
            StringBuilder sb = new StringBuilder();
            char[] answer = new char[stack.size()];
            for (Character c : stack) {
                sb.append(c);
            }
            System.out.println(sb);
        }
    }
}
