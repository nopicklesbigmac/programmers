class Solution {
    public int solution(int n, int w, int num) {
        int answer = 1;
        
        int floor = (num - 1) / w;
        int col = (num - 1) % w;
        
        if (floor % 2 == 1) {
            col = w - 1 - col;
        }
        int maxFloor = (n - 1) / w;
        
        for (int f = floor + 1; f <= maxFloor; f++) {
            int targetCol = (f % 2 == 0) ? col : (w - 1 - col);
            int targetNum = f * w + targetCol + 1;
            
            if (targetNum <= n) {
                answer++;
            }
        }
        return answer;
    }
}