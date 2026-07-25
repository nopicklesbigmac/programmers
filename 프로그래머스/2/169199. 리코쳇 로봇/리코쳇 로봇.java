import java.util.ArrayDeque;
import java.util.Queue;
class Solution {
    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();
        
        int startR = 0, startC = 0;
        int targetR = 0, targetC = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i].charAt(j);
                if (ch == 'R') {
                    startR = i;
                    startC = j;
                } else if (ch == 'G') {
                    targetR = i;
                    targetC = j;
                }
            }
        }
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[] {startR, startC, 0});
        visited[startR][startC] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            int dist = curr[2];
            
            if (r == targetR && c == targetC) {
                return dist;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r;
                int nc = c;
                
                while (nr + dr[i] >= 0 && nr + dr[i] < n && nc + dc[i] >= 0 && nc + dc[i] < m && board[nr + dr[i]].charAt(nc + dc[i]) != 'D') {
                    nr += dr[i];
                    nc += dc[i];
                }
                
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[] {nr, nc, dist + 1});
                }
            }
        }
        
        return -1;
    }
}