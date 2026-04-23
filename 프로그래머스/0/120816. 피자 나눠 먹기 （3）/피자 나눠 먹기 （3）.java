class Solution {
    public int solution(int slice, int n) {
        int answer = 0;
        while (answer * slice < n) {
            answer++;
        }
        return answer;
    }
}