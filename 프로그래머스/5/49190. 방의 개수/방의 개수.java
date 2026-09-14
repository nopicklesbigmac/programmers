import java.util.*;

class Solution {
    public int solution(int[] arrows) {
        int answer = 0;
        
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};
        
        Set<Long> nodes = new HashSet<>();
        Set<String> edges = new HashSet<>();
        
        int x = 0;
        int y = 0;
        
        nodes.add(getKey(x, y));
        
        for (int dir : arrows) {
            for (int i = 0; i < 2; i++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                long from = getKey(x, y);
                long to = getKey(nx, ny);
                
                String edge = from < to ? from + "," + to : to + "," + from;
                
                if (nodes.contains(to) && !edges.contains(edge)) {
                    answer++;
                }
                
                nodes.add(to);
                edges.add(edge);
                
                x = nx;
                y = ny;
            }
        }
        
        return answer;
    }
    
    private long getKey(int x, int y) {
        return ((long) x << 32) ^ (y & 0xffffffffL);
    }
}