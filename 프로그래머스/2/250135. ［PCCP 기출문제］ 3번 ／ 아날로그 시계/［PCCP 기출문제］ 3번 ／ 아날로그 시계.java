class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        int startSeconds = h1 * 3600 + m1 * 60 + s1;
        int endSeconds = h2 * 3600 + m2 * 60 + s2;
        
        answer = count(endSeconds) - count(startSeconds) + (isAlarmAt(startSeconds) ? 1 : 0);
        return answer;
    }
    
    private int count(int seconds) {
        int cnt = 0;
        
        if (seconds >= 0) {
            cnt = 1;
        }
        
        for (int t = 0; t < seconds; t++) {
            double curS = (t * 6.0) % 360;
            double curM = (t / 10.0) % 360;
            double curH = (t / 120.0) % 360;
            
            double nextS = ((t + 1) * 6.0) % 360;
            double nextM = ((t + 1) / 10.0) % 360;
            double nextH = ((t + 1) / 120.0) % 360;
            
            if (nextS == 0) nextS = 360;
            if (nextM == 0) nextM = 360;
            if (nextH == 0) nextH = 360;
            
            if (curS < curM && nextS >= nextM) {
                cnt++;
            }
            
            if (curS < curH && nextS >= nextH) {
                cnt++;
            }
            
            if (nextS == nextM && nextS == nextH) {
                cnt--;
            }
        }
        
        return cnt;
    }
    
    private boolean isAlarmAt(int t) {
        double s = (t * 6.0) % 360;
        double m = (t / 10.0) % 360;
        double h = (t / 120.0) % 360;
        
        if (s == 0) s = 360;
        if (m == 0) m = 360;
        if (h == 0) h = 360;
        
        return (s == m) || (s == h);
    }
}