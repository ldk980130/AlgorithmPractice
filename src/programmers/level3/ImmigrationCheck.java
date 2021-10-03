//프로그래머스 level3 입국심사
package programmers.level3;

import java.util.Arrays;

public class ImmigrationCheck {
    public long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);

        long left = 1;
        long right = n * (long)times[times.length-1];

        while (left <= right) {
            long mid = (left + right) / 2;

            long sum = 0;
            for (int i = 0; i < times.length; i++) {
                sum += mid / times[i];
            }

            if(sum < n){
                left = mid + 1;
            }
            else{
                right = mid -1;
                answer = mid;
            }
        }

        return answer;
    }
}