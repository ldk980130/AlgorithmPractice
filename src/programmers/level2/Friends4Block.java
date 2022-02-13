// 프로그래머스 level2 프렌즈4블록

package programmers.level2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class Friends4Block {

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Point point = (Point)o;
			return x == point.x && y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}

	private static Set<Point> removedList = new HashSet<>();
	private static int answer;
	private static boolean isContinue;
	public static final char EMPTY = '0';

	public int solution(int m, int n, String[] board) {
		answer = 0;

		char[][] charBoard = toCharArray(m, n, board);

		do {
			isContinue = false;
			play(m, n, charBoard);
		} while (isContinue);

		return answer;
	}

	private void play(int m, int n, char[][] charBoard) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				addRemoveList(charBoard, i, j, m, n);
			}
		}

		removePoints(charBoard);

		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				drop(m, charBoard, i, j);
			}
		}
		removedList.clear();
	}

	private void addRemoveList(char[][] charBoard, int i, int j, int m, int n) {
		char friend = charBoard[i][j];

		if (friend == EMPTY) {
			return;
		}

		if (j + 1 < n && friend == charBoard[i][j + 1]
			&& i + 1 < m && friend == charBoard[i + 1][j]
			&& friend == charBoard[i + 1][j + 1]) {

			Point point = new Point(i, j);
			if (!removedList.contains(point)) {
				answer++;
				removedList.add(point);
			}
			point = new Point(i, j + 1);
			if (!removedList.contains(point)) {
				answer++;
				removedList.add(point);
			}
			point = new Point(i + 1, j);
			if (!removedList.contains(point)) {
				answer++;
				removedList.add(point);
			}
			point = new Point(i + 1, j + 1);
			if (!removedList.contains(point)) {
				answer++;
				removedList.add(point);
			}

			isContinue = true;

		}
	}

	private void removePoints(char[][] charBoard) {
		Iterator<Point> iter = removedList.iterator();
		while (iter.hasNext()) {
			Point point = iter.next();
			charBoard[point.x][point.y] = EMPTY;
		}
	}

	private void drop(int m, char[][] charBoard, int i, int j) {
		if (charBoard[i][j] != EMPTY
			&& i + 1 < m && charBoard[i + 1][j] == EMPTY) {
			charBoard[i + 1][j] = charBoard[i][j];
			charBoard[i][j] = EMPTY;

			drop(m, charBoard , i + 1, j);
		}
	}

	private char[][] toCharArray(int m, int n, String[] board) {
		char[][] charBoard = new char[m][n];

		for (int i = 0; i < board.length; i++) {
			charBoard[i] = board[i].toCharArray();
		}

		return charBoard;
	}
}
