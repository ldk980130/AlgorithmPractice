// 프로그래머스 level2 빛의 경로 사이클

package programmers.level2;

import java.util.PriorityQueue;
import java.util.Queue;

public class PathCycleOfLight {

    static Queue<Integer> result;

    public int[] solution(String[] grid) {
        int[] answer;
        result = new PriorityQueue<>();

        char[][] map = new char[grid.length][grid[0].length()];
        for (int i = 0; i < grid.length; i++) {
            map[i] = grid[i].toCharArray();
        }

        boolean[][][] visited = new boolean[grid.length][grid[0].length()][4];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length(); j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d]) {
                        result.add(getCount(map, visited, i, j, d));
                    }
                }
            }
        }

        answer = new int[result.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = result.poll();
        }

        return answer;
    }

    static int getCount(char[][] grid, boolean[][][] visited, int r, int c, int d) {
        int count = 0;

        int n = grid.length;
        int m = grid[0].length;

        while (true) {
            if (visited[r][c][d]) break;
            visited[r][c][d] = true;
            count++;

            if (grid[r][c] == 'L')
                d = d == 0 ? 3 : d - 1; // 좌회전
            else if (grid[r][c] == 'R')
                d = d == 3 ? 0 : d + 1;

            if (d == 0) r = ((r + 1) >= n) ? 0 : (r + 1);
            else if (d == 1) c = ((c - 1) < 0) ? m - 1 : c - 1;
            else if (d == 2) r = (r - 1 < 0) ? n - 1 : r - 1;
            else if (d==3 ) c = (c + 1 >= m) ? 0 : (c + 1);
        }

        return count;
    }
}
