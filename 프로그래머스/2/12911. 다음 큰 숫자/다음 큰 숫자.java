class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int targetBitCount = Integer.bitCount(n);
        answer = n + 1;
        
        while (true) {
            if (Integer.bitCount(answer) == targetBitCount) {
                return answer;
            }
            answer++;
        }
    }
}