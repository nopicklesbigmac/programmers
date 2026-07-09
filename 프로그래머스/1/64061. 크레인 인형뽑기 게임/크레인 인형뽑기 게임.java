import java.util.Stack;
class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int n = board.length;
        Stack<Integer>[] lanes = new Stack[n];
        for (int i = 0; i < n; i++) {
            lanes[i] = new Stack<>();
        }
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != 0) {
                    lanes[j].push(board[i][j]);
                }
            }
        }
        
        Stack<Integer> basket = new Stack<>();
        
        for (int move : moves) {
            int col = move - 1; 
            
            if (!lanes[col].isEmpty()) {
                int doll = lanes[col].pop();
                
                if (!basket.isEmpty() && basket.peek() == doll) {
                    basket.pop();
                    answer += 2;
                } else {
                    basket.push(doll);
                }
            }
        }
        
        return answer;
    }
}