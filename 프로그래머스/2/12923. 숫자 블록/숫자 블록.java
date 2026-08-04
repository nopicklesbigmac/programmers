class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = {};
        int length = (int) (end - begin + 1);
        answer = new int[length];
        
        for (int i = 0; i < length; i++) {
            long pos = begin + i;
            answer[i] = (int) getMaxDivisor(pos);
        }
        
        return answer;
    }
private long getMaxDivisor(long n) {
        if (n == 1) {
            return 0;
        }
        
        long maxDiv = 1;
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (n / i <= 10_000_000) {
                    return n / i;
                }
                maxDiv = i;
            }
        }
        
        if (n <= 10_000_000) {
            return 1;
        }
        
        return maxDiv;
    }
}