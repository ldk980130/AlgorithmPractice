// 백준 14503번 골드5 로봇청소기

package baekjoon.realization;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RobotVacuumCleaner {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int r = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int answer = solution(n, m, r, c, d, map);
        System.out.println(answer);
    }

    static class Robot {
        int r, c, d;

        public Robot(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void changeDirectionToLeft() {
            d = (d - 1) == -1 ? 3 : d - 1;
        }

        public void changeDirectionToRight() {
            d = (d + 1) == 4 ? 0 : d + 1;
        }

        public void move() {

            if (d == 0) {
                r -= 1;
            }
            else if (d == 1) {
                c += 1;
            }
            else if (d == 2){
                r += 1;
            }
            else if (d == 3) {
                c -= 1;
            }
        }

        public void moveBack() {

            if (d == 0) {
                r += 1;
            }
            else if (d == 1) {
                c -= 1;
            }
            else if (d == 2){
                r -= 1;
            }
            else if (d == 3) {
                c += 1;
            }
        }
    }

    static int solution(int n, int m, int r, int c, int d, int[][] map) {
        int answer = 0;

        boolean[][] visited = new boolean[n][m];

        Queue<Robot> robotQueue = new LinkedList<>();
        robotQueue.add(new Robot(r, c, d));
        answer++;
        visited[r][c] = true;

        while (!robotQueue.isEmpty()) {

            Robot robot = robotQueue.poll();

            for (int i=0; i < 4; i++) {

                if (canMoveToLeft(robot, n, m, visited, map)) {
                    robotQueue.add(robot);
                    answer++;
                    visited[robot.r][robot.c] = true;
                    break;
                }
                else {
                    robot.changeDirectionToLeft();
                }
            }

            if (robotQueue.isEmpty()) {
                robot.moveBack();
                if (map[robot.r][robot.c] == 0) {
                    robotQueue.add(robot);
                }
            }
        }

        return answer;
    }

    static boolean canMoveToLeft(Robot robot, int n, int m, boolean[][] visited, int[][] map) {

        robot.changeDirectionToLeft();
        robot.move();

        if (robot.r < 0 || robot.r >= n || robot.c < 0 || robot.c >= m
                || visited[robot.r][robot.c] || map[robot.r][robot.c] == 1) {

            robot.moveBack();
            robot.changeDirectionToRight();
            return false;
        }

        return true;
    }
}
