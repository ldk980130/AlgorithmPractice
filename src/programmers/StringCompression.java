//프로그래머스 level2 문자열 압축
package programmers;

public class StringCompression {

    public int solution(String s) {
        int answer = s.length();

        for(int k=1; k<=s.length()/2; k++) {

            String temp = s;
            int count=0;
            boolean compacted = false;
            int start=0;
            int compactedCount = 1;

            while (start < temp.length()) {

                int end = start + k;

                if (end >= temp.length()) {

                    if (compacted) {
                        if (compactedCount < 10) count++;
                        else count += 2;
                    }
                    break;
                }

                String target = temp.substring(start, end);

                if (end + k <= temp.length() && target.equals(temp.substring(end, end + k))) {

                    temp = temp.replaceFirst(temp.substring(start, end+k), target);
                    compacted = true;
                    compactedCount++;
                }
                else{
                    if (compacted) {
                        if (compactedCount < 10) count++;
                        else count += 2;
                    }

                    compactedCount = 1;
                    compacted = false;
                    start += k;
                }
            }

            if (answer > temp.length() + count) {
                answer = temp.length() + count;
            }
        }

        return answer;
    }
}



