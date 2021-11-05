// 프로그래머스 level2 소수찾기

package programmers.level2;

import java.util.HashSet;
import java.util.Set;

public class FindPrimeNumber {

    static int answer = 0;
    static Set<Integer> primeSet;

    public int solution(String numbers) {
        answer = 0;
        primeSet = new HashSet<>();

        char[] nums = numbers.toCharArray();

        for (int i = 1; i <= nums.length; i++) {
            permutation(nums, 0, nums.length, i);
        }

        return answer;
    }

    static void permutation(char[] arr, int depth, int n, int r) {
        if (depth == r) {
            String temp = "";
            for (int i = 0; i < r; i++) {
                temp += arr[i];
            }
            int num = Integer.parseInt(temp);

            if (!primeSet.contains(num) && isPrime(num)) {
                primeSet.add(num);
                answer++;
            }

            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    static void swap(char[] arr, int depth, int i) {
        char temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
    }

    static boolean isPrime(int n) {

        if (n < 2) {
            return false;
        }

        int i = 2;

        while (i * i <= n) {
            if (n % i == 0) return false;
            i++;
        }
        return true;
    }
}
