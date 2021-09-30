// 프로그래머스 level2 행렬 테두리 회전하기

package programmers;

public class RotatingBorderOfMatrix {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] table = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                table[i][j] = i * columns + j + 1;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int min = rotation(table, query);
            answer[i] = min;
        }

        return answer;
    }

    static int rotation(int[][] table, int[] query) {

        int startRow = query[0] - 1;
        int startCol = query[1] - 1;
        int endRow = query[2] - 1;
        int endCol = query[3] - 1;

        int temp[] = {0, table[startRow][startCol]};
        int min = table[startRow][startCol];

        for (int i = startCol + 1; i <= endCol; i++) {
            temp[0] = temp[1];
            temp[1] = table[startRow][i];
            table[startRow][i] = temp[0];

            if (min > temp[1]) min = temp[1];
        }

        for (int i = startRow + 1; i <= endRow; i++) {
            temp[0] = temp[1];
            temp[1] = table[i][endCol];
            table[i][endCol] = temp[0];

            if (min > temp[1]) min = temp[1];
        }

        for (int i = endCol - 1; i >= startCol; i--) {
            temp[0] = temp[1];
            temp[1] = table[endRow][i];
            table[endRow][i] = temp[0];

            if (min > temp[1]) min = temp[1];
        }

        for (int i = endRow - 1; i >= startRow; i--) {
            temp[0] = temp[1];
            temp[1] = table[i][startCol];
            table[i][startCol] = temp[0];

            if (min > temp[1]) min = temp[1];
        }

        return min;
    }
}
