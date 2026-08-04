class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        
        long r1Sq = (long) r1 * r1;
        long r2Sq = (long) r2 * r2;
        
        for (long x = 1; x <= r2; x++) {
            long xSq = x * x;
            
            long maxY = (long) Math.floor(Math.sqrt(r2Sq - xSq));
            long minY = 0;
            if (x < r1) {
                minY = (long) Math.ceil(Math.sqrt(r1Sq - xSq));
            }
            
            if (maxY >= minY) {
                answer += (maxY - minY + 1);
            }
        }
        return answer * 4;
    }
}