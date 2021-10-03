//프로그래머스 level2 배달

package programmers.level2;

import java.util.*;

class Node implements Comparable<Node>{

    int end;
    int distance;

    public Node(int end, int distance) {
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance-o.distance;
    }
}

public class Delivery {
    public int solution(int n, int[][] road, int k) {
        int answer = 0;

        List<Node>[] graph = new LinkedList[n+1];
        for(int i=1; i<=n; i++) {
            graph[i] = new LinkedList<>();
        }


        for(int i=0; i<road.length; i++) {

            graph[road[i][0]].add(new Node(road[i][1], road[i][2]));
            graph[road[i][1]].add(new Node(road[i][0], road[i][2]));

        }

        Queue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[n+1];

        int INF = 1000000000;
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);

        queue.add(new Node(1, 0));
        dist[1] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int v = node.end;

            if(visited[v]) continue;
            visited[v] = true;

            Iterator<Node> iter = graph[v].iterator();
            while(iter.hasNext()) {
                Node next = iter.next();

                if(dist[next.end] > dist[v]+next.distance) {
                    dist[next.end] = dist[v]+next.distance;
                    queue.add(new Node(next.end, dist[next.end]));
                }
            }
        }

        for(int i=1; i<=n; i++) {
            if(dist[i]<=k) answer++;
        }

        return answer;
    }
}