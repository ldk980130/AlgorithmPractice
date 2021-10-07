// 백준 1012번 실버2 유기농 배추

package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class OrganicCabbage {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int i = 0; i < t; i++) {

            int m = sc.nextInt();
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[][] table = new int[m][n];

            setTable(sc, k, table);
            boolean[][] visited = new boolean[m][n];

            int answer = 0;

            for (int j = 0; j < m; j++) {
                for (int l = 0; l < n; l++) {

                    if (table[j][l] == 1 && visited[j][l] == false) {
                        bfs(table, j, l, visited);
                        answer++;
                    }
                }
            }

            System.out.println(answer);
        }

    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int[][] table, int x, int y, boolean[][] visited) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));

        visited[x][y] = true;

        int m = table.length;
        int n = table[0].length;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            x = node.x;
            y = node.y;

            // 위
            if (x > 0 && table[x - 1][y] == 1 && visited[x - 1][y] == false) {
                queue.add(new Node(x - 1, y));
                visited[x-1][y] = true;
            }

            // 오른쪽
            if (y < n - 1 && table[x][y + 1] == 1 && visited[x][y + 1] == false) {
                queue.add(new Node(x, y + 1));
                visited[x][y+1] = true;
            }

            // 아래
            if (x < m - 1 && table[x + 1][y] == 1 && visited[x + 1][y] == false) {
                queue.add(new Node(x + 1, y));
                visited[x+1][y] = true;
            }

            // 완쪽
            if (y > 0 && table[x][y - 1] == 1 && visited[x][y - 1] == false) {
                queue.add(new Node(x, y - 1));
                visited[x][y-1] = true;
            }
        }
    }

    static void setTable(Scanner sc, int k, int[][] table) {
        for (int j = 0; j < k; j++) {
            int x = sc.nextInt();
            int y = sc.nextInt();

            table[x][y] = 1;
        }
    }
}
