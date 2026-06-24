class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 123456789;
       for (int i = 0; i < absolutes.length; i++) {
            answer += signs[i] ? absolutes[i] : -absolutes[i];
        }
        answer = answer - 123456789;
        return answer;
    }
}