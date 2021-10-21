// 프로그래머스 level2 게임 맵 최단거리

package programmers.level2;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestGameMap {

    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public int solution(int[][] maps) {
        int answer = bfs(maps);

        return answer;
    }

    static int bfs(int[][] maps) {

        int answer = -1;

        int n = maps.length;
        int m = maps[0].length;

        Queue<Point> points = new LinkedList<>();
        Queue<Integer> counts = new LinkedList<>();

        points.add(new Point(0, 0));
        counts.add(1);

        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;

        while (!points.isEmpty()) {

            Point point = points.poll();
            int count = counts.poll();

            int x = point.getX();
            int y = point.getY();

            if (x == n - 1 && y == m - 1) {
                answer = count;
                break;
            }

            // 오른쪽
            if (y < m - 1 && !visited[x][y + 1] && maps[x][y + 1] == 1) {
                points.add(new Point(x, y + 1));
                counts.add(count + 1);
                visited[x][y + 1] = true;
            }

            // 아래쪽
            if (x < n - 1 && !visited[x + 1][y] && maps[x + 1][y] == 1) {
                points.add(new Point(x + 1, y));
                counts.add(count + 1);
                visited[x + 1][y] = true;
            }

            // 왼쪽
            if (y > 0 && !visited[x][y - 1] && maps[x][y - 1] == 1) {
                points.add(new Point(x, y - 1));
                counts.add(count + 1);
                visited[x][y - 1] = true;
            }

            // 위쪽
            if (x > 0 && !visited[x - 1][y] && maps[x - 1][y] == 1) {
                points.add(new Point(x - 1, y));
                counts.add(count + 1);
                visited[x - 1][y] = true;
            }
        }

        return answer;
    }
}
