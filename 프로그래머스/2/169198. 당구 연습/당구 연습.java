class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = {};
        answer = new int[balls.length];
        
        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            
            int minDist = Integer.MAX_VALUE;
            
            if (!(startY == targetY && startX > targetX)) {
                int dist = (startX + targetX) * (startX + targetX) + (startY - targetY) * (startY - targetY);
                minDist = Math.min(minDist, dist);
            }
            
            if (!(startY == targetY && startX < targetX)) {
                int dist = (2 * m - startX - targetX) * (2 * m - startX - targetX) + (startY - targetY) * (startY - targetY);
                minDist = Math.min(minDist, dist);
            }
            
            if (!(startX == targetX && startY > targetY)) {
                int dist = (startX - targetX) * (startX - targetX) + (startY + targetY) * (startY + targetY);
                minDist = Math.min(minDist, dist);
            }
            
            if (!(startX == targetX && startY < targetY)) {
                int dist = (startX - targetX) * (startX - targetX) + (2 * n - startY - targetY) * (2 * n - startY - targetY);
                minDist = Math.min(minDist, dist);
            }
            
            answer[i] = minDist;
        }
        
        return answer;
    }
}