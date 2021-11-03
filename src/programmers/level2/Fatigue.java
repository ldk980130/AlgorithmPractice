// 프로그래머스 level2 피로도

package programmers.level2;

public class Fatigue {

    static int answer;

    public int solution(int k, int[][] dungeons) {
        answer = 0;

        int n = dungeons.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        perm(arr, new int[n], new boolean[n], 0, n, n, k, dungeons);

        return answer;
    }

    static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r,
                     int k, int[][] dungeons) {
        if (depth == r) {
            exploration(dungeons, output, k);
            return;
        }

        for (int i=0; i<n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                output[depth] = arr[i];
                perm(arr, output, visited, depth + 1, n, r, k, dungeons);
                output[depth] = 0;
                visited[i] = false;;
            }
        }
    }

    static void exploration (int[][] dungeons, int[] order, int k) {

        int count = 0;

        for (int i = 0; i < order.length; i++) {

            if (k < dungeons[order[i]][0]) break;
            k -= dungeons[order[i]][1];
            count++;
        }

        if (answer < count) answer = count;
    }
}
