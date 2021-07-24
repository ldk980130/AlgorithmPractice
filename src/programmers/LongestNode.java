//프로그래머스 level3 가장 먼 노드
package programmers;


import java.util.*;

public class LongestNode {
    public int solution(int n, int[][] edge) {
        int answer = 0;

        LinkedList<Integer>[] graph = new LinkedList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edge.length; i++) {
            graph[edge[i][0]].add(edge[i][1]);
            graph[edge[i][1]].add(edge[i][0]);
        }

        answer = bfs(graph, n);

        return answer;
    }

    static int bfs(LinkedList<Integer>[] graph, int n){

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n+1];
        int[] distance = new int[n+1];

        queue.add(1);

        while (!queue.isEmpty()) {
            int v = queue.poll();

            if(visited[v]) continue;
            visited[v] = true;

            Iterator<Integer> iter = graph[v].iterator();
            while (iter.hasNext()) {
                int w = iter.next();

                if(!visited[w]) {
                    queue.add(w);
                    if(distance[w]==0) distance[w] = distance[v]+1;
                }
            }
        }

        int count = 1;
        Arrays.sort(distance);
        for(int i=n; i>=1; i--){
            if(distance[i]==distance[i-1]) count++;
            else break;
        }

        return count;
    }
}
