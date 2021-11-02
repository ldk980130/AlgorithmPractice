// 백준 15686번 골드5 치킨 배달

package baekjoon.realization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ChickenDelivery {
    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int answer;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        answer = 1000000000;

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] map = new int[n][n];

        List<Node> homeList = new ArrayList<>();
        List<Node> storeList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();

                if (map[i][j] == 1) homeList.add(new Node(i, j));
                if (map[i][j] == 2) storeList.add(new Node(i, j));
            }
        }
        permutation(storeList, homeList, new boolean[storeList.size()], 0, storeList.size(), m);

        System.out.println(answer);
    }

    static void permutation (List<Node> storeList, List<Node> homeList,
                             boolean[] visited, int start, int n, int m) {

        if (m == 0) {
            List<Node> selectedStores = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if (visited[i] == true) {
                    selectedStores.add(storeList.get(i));
                }
            }
            getMinChickenDistance(homeList, selectedStores);
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            permutation(storeList, homeList, visited, i + 1, n, m - 1);
            visited[i] = false;
        }

    }

    static void getMinChickenDistance(List<Node> homeList, List<Node> stores) {

        Iterator<Node> homeIter = homeList.iterator();

        int sum = 0;
        while (homeIter.hasNext()) {
            Node home = homeIter.next();
            int minDistance = 1000000000;

            Iterator<Node> storeIter = stores.iterator();
            while (storeIter.hasNext()) {
                minDistance = getMinDistance(home, minDistance, storeIter);
            }
            sum += minDistance;
        }

        if (answer > sum) {
            answer = sum;
        }
    }

    private static int getMinDistance(Node home, int minDistance, Iterator<Node> storeIter) {
        Node store = storeIter.next();
        int distance = getDistance(home, store);
        if (minDistance > distance) {
            minDistance = distance;
        }
        return minDistance;
    }

    static int getDistance(Node node1, Node node2) {
        int absR = Math.abs(node1.r - node2.r);
        int absC = Math.abs(node1.c - node2.c);
        return absR + absC;
    }
}
