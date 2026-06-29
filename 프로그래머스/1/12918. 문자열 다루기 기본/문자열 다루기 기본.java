class Solution {
    public boolean solution(String s) {
        boolean answer = true;
        answer = s.matches("^(\\d{4}|\\d{6})$");
        return answer;
    }
}