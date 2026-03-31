class Solution {
    public String solution(String my_string, String alp) {
        String answer = "";
        String upperAlp = alp.toUpperCase();
        answer = my_string.replace(alp, upperAlp);
        return answer;
    }
}