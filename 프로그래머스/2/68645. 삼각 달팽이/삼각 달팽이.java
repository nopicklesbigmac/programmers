import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        int[][] triangle = new int[n][n];
        
        int[] dy = {1, 0, -1};
        int[] dx = {0, 1, -1};
        
        int x = 0;
        int y = 0;
        int num = 1;
        int direction = 0;
        
        int maxNum = n * (n + 1) / 2;
        
        while (num <= maxNum) {
            triangle[y][x] = num;
            
            int ny = y + dy[direction];
            int nx = x + dx[direction];
            
            if (ny < 0 || nx < 0 || ny >= n || nx > ny || triangle[ny][nx] != 0) {
                direction = (direction + 1) % 3;
                ny = y + dy[direction];
                nx = x + dx[direction];
            }
            
            y = ny;
            x = nx;
            num++;
        }
        
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                list.add(triangle[i][j]);
            }
        }
        answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}