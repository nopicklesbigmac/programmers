class Solution {
    public int[] solution(int n, int k) {
        int[] answer = {};
        
        int count = n / k;
        answer = new int[count];
        for (int i = 0; i < count; i++) {
            answer[i] = (i + 1) * k;
        }
        return answer;
    }
}