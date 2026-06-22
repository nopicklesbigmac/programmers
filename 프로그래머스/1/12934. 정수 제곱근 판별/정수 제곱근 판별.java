import java.util.*;
class Solution {
    public long solution(long n) {
        long answer = -1;
        long x = (long) Math.sqrt(n);
        
        if (x * x == n) {
            answer = (x + 1) * (x + 1);
        }
        return answer;
    }
}