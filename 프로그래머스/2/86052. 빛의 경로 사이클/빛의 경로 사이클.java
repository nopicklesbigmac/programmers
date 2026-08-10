import java.util.*;
class Solution {
    public int[] solution(String[] grid) {
        int[] answer = {};
        int R = grid.length;
        int C = grid[0].length();
        boolean[][][] visited = new boolean[R][C][4];
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        List<Integer> cycles = new ArrayList<>();
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < 4; d++) {
                    if (visited[r][c][d]) continue;
                    
                    int currR = r;
                    int currC = c;
                    int currD = d;
                    int length = 0;
                    
                    while (!visited[currR][currC][currD]) {
                        visited[currR][currC][currD] = true;
                        length++;
                        
                        char cell = grid[currR].charAt(currC);
                        if (cell == 'L') {
                            currD = (currD + 3) % 4;
                        } else if (cell == 'R') {
                            currD = (currD + 1) % 4;
                        }
                        
                        currR = (currR + dr[currD] + R) % R;
                        currC = (currC + dc[currD] + C) % C;
                    }
                    
                    cycles.add(length);
                }
            }
        }
        
        Collections.sort(cycles);
        answer = new int[cycles.size()];
        for (int i = 0; i < cycles.size(); i++) {
            answer[i] = cycles.get(i);
        }
        
        return answer;
    }
}