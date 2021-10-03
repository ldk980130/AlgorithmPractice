// 프로그래머스 level2 다리를 지나는 트럭

package programmers.level2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TruckPassingBridge {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> bridgeQueue = new LinkedList<>();
        Queue<Integer> waitingQueue = new LinkedList<>();

        for (int i = 0; i < truck_weights.length; i++) {
            waitingQueue.add(truck_weights[i]);
        }

        Queue<Integer> timeQueue = new LinkedList<>();

        while (!waitingQueue.isEmpty()) {
            answer++;

            Integer firstTruckTime = timeQueue.peek();
            firstTruckTime = (firstTruckTime == null) ? 0 : firstTruckTime;

            if (answer - firstTruckTime == bridge_length) {
                bridgeQueue.poll();
                timeQueue.poll();
            }

            Integer truck = waitingQueue.peek();

            if (camPass(bridgeQueue, weight, truck)) {
                bridgeQueue.add(waitingQueue.poll());
                timeQueue.add(answer);
            }
        }
        answer += bridge_length;

        return answer;
    }

    static boolean camPass(Queue<Integer> queue, int bridgeWeight, int truckWeight) {

        Iterator<Integer> iter = queue.iterator();
        int sum = 0;

        while (iter.hasNext()) {
            Integer next = iter.next();
            sum += next;
        }
        sum += truckWeight;

        return bridgeWeight >= sum;
    }
}
