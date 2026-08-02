class Solution {
    public int solution(String[] board) {
        int answer = 1;
        int oCount = 0;
        int xCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'O') {
                    oCount++;
                } else if (board[i].charAt(j) == 'X') {
                    xCount++;
                }
            }
        }

        if (oCount < xCount || oCount > xCount + 1) {
            answer = 0;
            return answer;
        }

        boolean oWin = checkWin(board, 'O');
        boolean xWin = checkWin(board, 'X');

        if (oWin && xWin) {
            answer = 0;
            return answer;
        }

        if (oWin && oCount != xCount + 1) {
            answer = 0;
            return answer;
        }

        if (xWin && oCount != xCount) {
            answer = 0;
            return answer;
        }

        return answer;
    }

    private boolean checkWin(String[] board, char c) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
        }

        for (int j = 0; j < 3; j++) {
            if (board[0].charAt(j) == c && board[1].charAt(j) == c && board[2].charAt(j) == c) {
                return true;
            }
        }

        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) {
            return true;
        }
        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c) {
            return true;
        }

        return false;
    }
}