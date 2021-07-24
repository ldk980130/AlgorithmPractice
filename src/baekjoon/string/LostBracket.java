//백준 1541번 실버2 잃어버린 괄호

package baekjoon.string;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LostBracket {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.next();

        String[] nums = input.split("[-\\+]");
        String[] pm = new String[nums.length - 1];

        int index=0;
        for (int i = 0; i < input.length(); i++) {
            if(input.charAt(i)=='+'){
                pm[index++] = "+";
            } else if (input.charAt(i) == '-') {
                pm[index++] = "-";
            }
        }

        List<String> list = new LinkedList<>();
        for (int i = 0; i < pm.length; i++) {
            list.add(nums[i]);
            list.add(pm[i]);
        }
        list.add(nums[nums.length - 1]);

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == "+") {
                int num1 = Integer.parseInt(list.get(i - 1));
                int num2 = Integer.parseInt(list.get(i + 1));
                list.add(i, num1 + num2 + "");
                list.remove(i+1);
                list.remove(i+1);
                list.remove(i-1);
                i--;
            }
        }

        int answer = Integer.parseInt(list.get(0));
        for(int i=2; i<list.size(); i+=2){
            answer -= Integer.parseInt(list.get(i));
        }
        System.out.println(answer);
    }
}
