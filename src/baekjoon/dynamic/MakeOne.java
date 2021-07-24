//백준 1436번 실버3 1로 만들기
package baekjoon.dynamic;

import java.util.Scanner;

public class MakeOne {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] dp = new int[n+1];

        if(n < 3){
            dp[n] = n-1;
        }

        else if(n==3) dp[n] = 1;

        else {
            dp[1] = 0;
            dp[2] = 1;
            dp[3] = 1;
            for (int i = 4; i <= n; i++) {

                if (i % 3 != 0 && i % 2 != 0) {
                    dp[i] = dp[i-1] + 1;
                }
                else{
                    if (i%2==0 && i%3==0){
                        int a = dp[i/2] + 1;
                        int b = dp[i/3] + 1;
                        int c = dp[i-1] + 1;
                        dp[i] = Math.min(Math.min(a,b), c);
                    }
                    else if(i%2==0){
                        int a = dp[i/2] + 1;
                        int b = dp[i-1] + 1;
                        dp[i] = Math.min(a,b);
                    }
                    else if(i%3==0){
                        int a = dp[i/3] + 1;
                        int b = dp[i-1] + 1;
                        dp[i] = Math.min(a, b);
                    }
                }
            }
        }
        System.out.println(dp[n]);
    }
}
