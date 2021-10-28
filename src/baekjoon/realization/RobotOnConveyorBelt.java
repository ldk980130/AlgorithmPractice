// 백준 20055번 실버1 컨베이어 벨트 위에 로봇

package baekjoon.realization;

import java.util.Scanner;

public class RobotOnConveyorBelt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        k = sc.nextInt();
        int[] belt = new int[2 * n];

        for (int i = 0; i < 2 * n; i++) {
            belt[i] = sc.nextInt();
        }

        putPoint = 0;
        boolean[] robot = new boolean[n];

        int count = 0;
        while (k > 0) {
            count++;
            moveBelt(robot, n);
            moveRobot(belt, robot, n);
            putRobot(belt, robot);
        }
        System.out.println(count);
    }

    static int putPoint, k;

    static void moveBelt(boolean[] robot,  int n) {

        putPoint--;

        if (putPoint < 0) putPoint = 2 * n - 1;

        for (int i = n - 2; i >= 0; i--) {
            if (robot[i]) {
                robot[i] = false;
                if (i + 1 < n - 1) {
                    robot[i + 1] = true;
                }
            }
        }
    }

    static void moveRobot(int[] belt, boolean[] robot, int n) {
        for (int i = n - 2; i >= 0; i--) {
            if (robot[i]) {
                int next = putPoint + i + 1;
                if (next >= 2 * n) {
                    next -= 2 * n;
                }
                if (belt[next] > 0 && !robot[i + 1]) {
                    robot[i] = false;
                    if (i + 1 < n - 1) {
                        robot[i + 1] = true;
                    }
                    belt[next]--;
                    if (belt[next] == 0) {
                        k--;
                    }
                }

            }
        }
    }

    static void putRobot(int[] belt, boolean[] robot) {

        if (belt[putPoint] > 0 && !robot[0]) {
            robot[0] = true;
            belt[putPoint]--;
            if (belt[putPoint] == 0) {
                k--;
            }
        }
    }
}
