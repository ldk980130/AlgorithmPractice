// 프로그래머스 level2 수식 최대화

package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class MaximizeFormula {

    static List<String> expressionList;
    static long answer;
    public long solution(String expression) {
        answer = 0;

        char[] priority = {'*', '+', '-'};

        String[] nums = expression.split("[*+\\-]");
        String[] operators = expression.split("[0-9]+");

        expressionList = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i != 0) expressionList.add(operators[i]);
            expressionList.add(nums[i]);
        }

        permutation(priority, 0, 3);

        return answer;
    }

    static void calculateAll (char[] priority) {

        List<String> temp = new ArrayList<>();
        expressionList.stream().forEach(s -> temp.add(s));

        for (int i = 0; i < priority.length; i++) {

            String operator = priority[i] + "";

            int j = 0;
            while (temp.size() != j) {

                if (temp.get(j).equals(operator)) {
                    String num1 = temp.remove(j - 1);
                    temp.remove(j - 1);
                    String num2 = temp.remove(j - 1);

                    String result = calculate(num1, num2, operator);

                    temp.add(j - 1, result);
                }

                else j++;
            }
        }

        Long result = Math.abs(Long.parseLong(temp.get(0)));
        if (answer < result) answer = result;
    }

    static String calculate(String num1, String num2, String operator) {

        long result = 0;
        long x = Long.parseLong(num1);
        long y = Long.parseLong(num2);

        if (operator.equals("+")) result = x + y;
        else if (operator.equals("*")) result = x * y;
        else if (operator.equals("-")) result = x - y;

        return result + "";
    }

    static void permutation(char[] priority, int depth, int n) {
        if (depth == n) {
            calculateAll(priority);
            return;
        }

        for (int i = depth; i < n; i++) {
            swap(priority, depth, i);
            permutation(priority, depth + 1, n);
            swap(priority, depth, i);
        }
    }

    static void swap(char[] arr, int depth, int i) {
        char temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }
}
