class Solution {
    public int[] solution(String[][] places) {
        int[] answer = {};
        answer = new int[5];
        
        for (int i = 0; i < 5; i++) {
            answer[i] = check(places[i]) ? 1 : 0;
        }
        
        return answer;
    }
    
    private boolean check(String[] place) {
        char[][] room = new char[5][5];
        for (int i = 0; i < 5; i++) {
            room[i] = place[i].toCharArray();
        }
        
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (room[r][c] == 'P') {
                    if (!bfs(room, r, c)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    private boolean bfs(char[][] room, int startR, int startC) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        boolean[][] visited = new boolean[5][5];
        int[][] dist = new int[5][5];
        
        java.util.Queue<int[]> queue = new java.util.LinkedList<>();
        queue.add(new int[]{startR, startC});
        visited[startR][startC] = true;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];
            
            if (dist[r][c] == 2) {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5 && !visited[nr][nc]) {
                    if (room[nr][nc] == 'X') {
                        continue;
                    }
                    
                    visited[nr][nc] = true;
                    dist[nr][nc] = dist[r][c] + 1;
                    
                    if (room[nr][nc] == 'P') {
                        return false;
                    }
                    
                    queue.add(new int[]{nr, nc});
                }
            }
        }
        
        return true;
    }
}