class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int answer = 0;

        while (true) {
            boolean removed = false;

            for (int num = 1; num <= 200; num++) {
                int minR = n;
                int minC = n;
                int maxR = -1;
                int maxC = -1;

                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        if (board[r][c] == num) {
                            minR = Math.min(minR, r);
                            minC = Math.min(minC, c);
                            maxR = Math.max(maxR, r);
                            maxC = Math.max(maxC, c);
                        }
                    }
                }

                if (maxR == -1) {
                    continue;
                }

                int height = maxR - minR + 1;
                int width = maxC - minC + 1;

                if (!((height == 2 && width == 3) || (height == 3 && width == 2))) {
                    continue;
                }

                int zeroCount = 0;
                boolean possible = true;

                for (int r = minR; r <= maxR; r++) {
                    for (int c = minC; c <= maxC; c++) {
                        if (board[r][c] == 0) {
                            zeroCount++;
                        } else if (board[r][c] != num) {
                            possible = false;
                        }
                    }
                }

                if (!possible || zeroCount != 2) {
                    continue;
                }

                for (int r = minR; r <= maxR && possible; r++) {
                    for (int c = minC; c <= maxC; c++) {
                        if (board[r][c] == 0) {
                            for (int x = r - 1; x >= 0; x--) {
                                if (board[x][c] != 0) {
                                    possible = false;
                                    break;
                                }
                            }

                            if (!possible) {
                                break;
                            }
                        }
                    }
                }

                if (possible) {
                    for (int r = minR; r <= maxR; r++) {
                        for (int c = minC; c <= maxC; c++) {
                            if (board[r][c] == num) {
                                board[r][c] = 0;
                            }
                        }
                    }

                    answer++;
                    removed = true;
                }
            }

            if (!removed) {
                break;
            }
        }

        return answer;
    }
}