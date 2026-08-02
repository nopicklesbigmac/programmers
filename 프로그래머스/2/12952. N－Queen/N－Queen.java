class Solution {
    private int answer = 0;
    private int[] board;

    public int solution(int n) {
        board = new int[n];
        backtrack(0, n);
        return answer;
    }

    private void backtrack(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }

        for (int col = 0; col < n; col++) {
            board[row] = col;
            if (isAvailable(row)) {
                backtrack(row + 1, n);
            }
        }
    }

    private boolean isAvailable(int row) {
        for (int i = 0; i < row; i++) {
            if (board[row] == board[i] || Math.abs(row - i) == Math.abs(board[row] - board[i])) {
                return false;
            }
        }
        return true;
    }
}