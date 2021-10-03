// 프로그래머스 level3 N으로 표현
package programmers.level3;

import java.util.LinkedList;
import java.util.Queue;

public class NExpression {

    public int solution(int n, int number) {
        int answer = bfs(n, number);


        return answer;
    }

    static int bfs(int n, int number){

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> countQ = new LinkedList<>();

        int temp = n;
        for (int i = 0; i < 8; i++) {
            queue.add(temp);
            countQ.add(1+i);

            temp = temp*10 + n;
        }

        while (!queue.isEmpty()) {
            int present = queue.poll();
            int count = countQ.poll();

            if(count>8) continue;

            if(present==number){
                return count;
            }

            if(count<8){
                temp = n;
                for (int i = 0; i < 8 - count; i++) {
                    queue.add(present+temp);
                    countQ.add(count+1+i);
                    queue.add(present-temp);
                    countQ.add(count+1+i);
                    queue.add(present*temp);
                    countQ.add(count+1+i);
                    queue.add(present/temp);
                    countQ.add(count+1+i);

                    temp = temp*10 + n;
                }

                queue.add(present+1);
                countQ.add(count+2);
                queue.add(present-1);
                countQ.add(count+2);
            }
        }

        return -1;
    }
}