// 프로그래머스 level2 카카오프렌즈 컬러링북

package programmers.level2;

public class ColoringBook {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        boolean[][] visited = new boolean[m][n];

        count = 1;
        maxCount = 0;

        for(int i=0; i<m; i++){
            for (int j = 0; j < n; j++) {
                if (picture[i][j]!=0 && !visited[i][j]) {
                    numberOfArea++;
                    count=1;

                    countArea(picture, visited, i, j);

                    if(maxCount<count) maxCount = count;
                }
            }
        }

        maxSizeOfOneArea = maxCount;

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static int maxCount;
    static int count;
    static void countArea(int[][] picture, boolean[][] visited, int row, int col){

        visited[row][col] = true;

        int m = picture.length;
        int n = picture[0].length;

        //오른쪽
        if(col+1<n && !visited[row][col+1] && picture[row][col+1]==picture[row][col]) {
            count++;
            countArea(picture, visited, row, col+1);
        }
        //아래쪽
        if(row+1<m && !visited[row+1][col] && picture[row+1][col]==picture[row][col]) {
            count++;
            countArea(picture, visited, row+1, col);
        }
        //왼쪽
        if(col-1>=0 && !visited[row][col-1] && picture[row][col-1]==picture[row][col]) {
            count++;
            countArea(picture, visited, row, col-1);
        }
        //위쪽
        if(row-1>=0 && !visited[row-1][col] && picture[row-1][col]==picture[row][col]) {
            count++;
            countArea(picture, visited, row-1, col);
        }
    }
}