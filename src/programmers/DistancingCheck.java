//프로그래머스 level2 거리두기 확인하기
package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point{
    int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class DistancingCheck {

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);

        for (int i = 0; i < 5; i++) {
            char[][] room = new char[5][5];
            boolean result = true;

            for (int j = 0; j < 5; j++) {
                room[j] = places[i][j].toCharArray();
            }

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if(room[j][k]=='P'){
                        result = isSafe(room, j, k);
                        if(!result) break;
                    }
                }
                if(!result){
                    answer[i] = 0;
                    break;
                }
            }
        }

        return answer;
    }

    static boolean isSafe(char[][] places, int row, int col){
        boolean safe = true;
        boolean[][] visited = new boolean[5][5];

        Queue<Point> pointQ = new LinkedList<>();
        Queue<Integer> countQ = new LinkedList<>();
        pointQ.add(new Point(row, col));
        countQ.add(0);

        while (!pointQ.isEmpty()) {

            Point point = pointQ.poll();
            int distance = countQ.poll();

            visited[row][col] = true;
            if(distance>=2) continue;

            row = point.row;
            col = point.col;

            //오른쪽 검사
            if(col<4 && places[row][col+1]=='O' && !visited[row][col+1]){
                pointQ.add(new Point(row, col+1));
                countQ.add(distance+1);
            }
            else if(col<4 && places[row][col+1]=='P' && !visited[row][col+1]){
                safe = false;
                break;
            }

            //아래쪽 검사
            if(row<4 && places[row+1][col]=='O' && !visited[row+1][col]){
                pointQ.add(new Point(row + 1, col));
                countQ.add(distance + 1);
            }
            else if(row<4 && places[row+1][col]=='P' && !visited[row+1][col]){
                safe = false;
                break;
            }

            //왼쪽 검사
            if(col>0 && places[row][col-1]=='O' && !visited[row][col-1]){
                pointQ.add(new Point(row, col - 1));
                countQ.add(distance + 1);
            }
            else if(col>0 && places[row][col-1]=='P' && !visited[row][col-1]){
                safe = false;
                break;
            }

            //위쪽 검사
            if(row>0 && places[row-1][col]=='O' && !visited[row-1][col]){
                pointQ.add(new Point(row-1, col));
                countQ.add(distance + 1);
            }
            else if(row>0 && places[row-1][col]=='P' && !visited[row-1][col]){
                safe = false;
                break;
            }
        }

        return safe;
    }
}
