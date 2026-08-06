import java.util.*;
class Solution {
    public String[] solution(int[][] line) {
        String[] answer = {};
        List<long[]> points = new ArrayList<>();
        
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;
        
        for (int i = 0; i < line.length; i++) {
            long a1 = line[i][0];
            long b1 = line[i][1];
            long c1 = line[i][2];
            
            for (int j = i + 1; j < line.length; j++) {
                long a2 = line[j][0];
                long b2 = line[j][1];
                long c2 = line[j][2];
                
                long denominator = a1 * b2 - b1 * a2;
                if (denominator == 0) continue;
                
                long numeratorX = b1 * c2 - c1 * b2;
                long numeratorY = c1 * a2 - a1 * c2;
                
                if (numeratorX % denominator != 0 || numeratorY % denominator != 0) continue;
                
                long x = numeratorX / denominator;
                long y = numeratorY / denominator;
                
                points.add(new long[]{x, y});
                
                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }
        
        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);
        
        char[][] map = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(map[i], '.');
        }
        
        for (long[] p : points) {
            int x = (int) (p[0] - minX);
            int y = (int) (maxY - p[1]);
            map[y][x] = '*';
        }
        
        answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(map[i]);
        }
        
        return answer;
    }
}