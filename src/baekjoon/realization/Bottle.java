// 백준 1052번 실버1 물병

package baekjoon.realization;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Bottle {
    static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        int k = sc.nextInt();

        Queue<Integer> bottles = new PriorityQueue<>();

        int amount = 1;
        int count = 0;

        while (n > 0) {
            amount = combine(amount);
            n--;
            bottles.add(amount);
        }

        while (bottles.size() > k) {
            count += CombineTwoSmallest(bottles);
        }
        System.out.println(count);
    }

    private static int CombineTwoSmallest(Queue<Integer> bottles) {
        int bottleA = bottles.poll();
        int bottleB = bottles.poll();

        int num = bottleB / bottleA;

        bottles.add(bottleB * 2);

        return (num - 1) * bottleA;
    }

    private static int combine(int amount) {

        while (n % 2 == 0) {
            n /= 2;
            amount *= 2;
        }
        return amount;
    }
}
