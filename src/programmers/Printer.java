//프로그래머스 level2 스택/큐 프린터

package programmers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Printer {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        Queue<Boolean> locationQueue = new LinkedList<>();
        Queue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < priorities.length; i++) {
            queue.add(priorities[i]);

            priorityQueue.add(priorities[i]);

            if (i == location) locationQueue.add(true);
            else locationQueue.add(false);
        }

        while (!queue.isEmpty()) {
            int priority = queue.poll();
            Boolean isLocation = locationQueue.poll();

            if (priority < priorityQueue.peek()) {
                queue.add(priority);
                locationQueue.add(isLocation);
                continue;
            }

            answer++;
            priorityQueue.poll();

            if (isLocation == true) break;
        }

        return answer;
    }
}
