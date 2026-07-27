class Solution
{
    public int solution(int [][]board)
    {
        int answer = 1234;

        // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.
        System.out.println("Hello Java");

        
    int row = board.length;
        int col = board[0].length;
        
        int[][] dp = new int[row][col];
        int maxLen = 0;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    }
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        
        answer = maxLen * maxLen;
        return answer;
    }
}