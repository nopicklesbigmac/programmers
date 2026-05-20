class Solution {
    public int solution(String A, String B) {
        int answer = 0;
        answer = (B + B).indexOf(A);
        return answer;
    }
}