class Solution {
    public int solution(int n) {
        int answer = 0;
        int i = 1;
        int factorial = 1;
        
        while (factorial <= n) {
            i++;
            factorial *= i;
        }
        answer = i - 1;
        return answer;
    }
}