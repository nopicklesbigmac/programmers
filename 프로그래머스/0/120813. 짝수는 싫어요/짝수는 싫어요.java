class Solution {
    public int[] solution(int n) {
        int[] answer = {};
        int size = (n + 1) / 2;
        answer = new int[size];
        
        int index = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                answer[index++] = i;
            }
        }
        return answer;
    }
}