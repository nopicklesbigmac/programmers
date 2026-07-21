class Solution {
    public int solution(int n) {
        int answer = 0;
        
        if (n <= 2) {
            answer = n;
            return answer;
        }
        
        int mod = 1000000007;
        int prev2 = 1;
        int prev1 = 2;
        
        for (int i = 3; i <= n; i++) {
            answer = (prev1 + prev2) % mod;
            prev2 = prev1;
            prev1 = answer;
        }
        
        return answer;
    }
}