import java.util.ArrayDeque;
import java.util.Queue;
class Solution {
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int n = storage.length;
        int m = storage[0].length();
        
        char[][] map = new char[n + 2][m + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                map[i][j] = '.';
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (String req : requests) {
            char target = req.charAt(0);
            boolean isCrane = (req.length() == 2);
            
            if (isCrane) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (map[i][j] == target) {
                            map[i][j] = '.';
                        }
                    }
                }
            } else {
                boolean[][] visited = new boolean[n + 2][m + 2];
                Queue<int[]> q = new ArrayDeque<>();
                q.offer(new int[]{0, 0});
                visited[0][0] = true;
                
                boolean[][] toRemove = new boolean[n + 2][m + 2];
                
                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int r = cur[0];
                    int c = cur[1];
                    
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        
                        if (nr >= 0 && nr < n + 2 && nc >= 0 && nc < m + 2 && !visited[nr][nc]) {
                            visited[nr][nc] = true;
                            if (map[nr][nc] == '.') {
                                q.offer(new int[]{nr, nc});
                            } else if (map[nr][nc] == target) {
                                toRemove[nr][nc] = true;
                            }
                        }
                    }
                }
                
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (toRemove[i][j]) {
                            map[i][j] = '.';
                        }
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] != '.') {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}