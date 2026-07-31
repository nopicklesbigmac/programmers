class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        long width = (long) w;
        long height = (long) h;
        
        long gcd = getGcd(width, height);
        
        long unusable = width + height - gcd;
        
        answer = (width * height) - unusable;
        return answer;
    }
private long getGcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}