//백준 12865번 골드5 평범한 배낭
package baekjoon.dynamic;

import java.util.Scanner;

public class DynamicKnapsack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] weight = new int[n+1];
        int[] value = new int[n+1];
        for (int i = 1; i <= n; i++) {
            weight[i] = sc.nextInt();
            value[i] = sc.nextInt();
        }

        int[][] dp = new int[n+1][k+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                if(j < weight[i]) {
                    dp[i][j] = dp[i-1][j];
                }
                else if (j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i-1][j-weight[i]]+value[i],  dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[n][k]);
    }
}
