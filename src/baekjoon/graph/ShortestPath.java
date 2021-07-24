//백준 1753번 골드5 최단경로
package baekjoon.graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node implements Comparable<Node>{
    int end, weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return weight-o.weight;
    }
}
public class ShortestPath {
    final static int INF = 1000000000;

    static void dijkstra(LinkedList<Node>[] graph, int start, int[] dist) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[graph.length];

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Node curNode = queue.poll();
            int v = curNode.end;

            if(visited[v]) continue;
            visited[v] = true;

            Iterator<Node> iter = graph[v].iterator();

            while(iter.hasNext()) {
                Node nextNode = iter.next();

                if(dist[nextNode.end] > dist[v]+nextNode.weight) {
                    dist[nextNode.end]=  dist[v]+nextNode.weight;
                    queue.add(new Node(nextNode.end, dist[nextNode.end]));
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();
        int k = sc.nextInt();

        LinkedList<Node>[] graph = new LinkedList[v+1];
        for(int i=1; i<=v; i++) {
            graph[i] = new LinkedList();
        }

        for(int i=0; i<e; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int w= sc.nextInt();

            graph[start].add(new Node(end, w));
        }
        int[] dist = new int[v+1];
        Arrays.fill(dist, INF);

        dijkstra(graph, k, dist);

        for(int i=1; i<=v; i++) {
            if(dist[i]==INF) {
                System.out.println("INF");
            }
            else {
                System.out.println(dist[i]);
            }
        }
    }
}



