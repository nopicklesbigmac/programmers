class Solution {
    public String solution(String myString, String pat) {
        String answer = "";
        int lastIndex = myString.lastIndexOf(pat);
        answer = myString.substring(0, lastIndex + pat.length());
        return answer;
    }
}