import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Main
{
    static int N, K;
    static int[] w;
    static int[] v;
    static int[] bag;

    public static int func(int n, int sum) {
        if(n < 0) return 0;
        if(bag[sum] != -1) return bag[sum];
        if(sum + w[n] > K) bag[sum] =  func(n-1, sum);
        else bag[sum] = Math.max(func(n-1, sum), func(n-1, sum+w[n])+v[n]);
        return bag[sum];
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        N= sc.nextInt(); K = sc.nextInt();
        w = new int[101];
        v= new int[101];
        bag = new int[100001];
        for(int i = 0; i < 100001; i++) bag[i] = -1;
        for(int i = 0; i < N; i++) {
            w[i] = sc.nextInt();
            v[i] = sc.nextInt();
        }
        int ans = func(N-1, 0);
        System.out.print(ans);
    }
}