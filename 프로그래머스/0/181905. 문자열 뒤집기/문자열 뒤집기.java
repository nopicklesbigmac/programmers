class Solution {
    public String solution(String my_string, int s, int e) {
        String answer = "";
        String sub = my_string.substring(s, e + 1);
        StringBuilder sb = new StringBuilder(sub);
        String reversed = sb.reverse().toString();
        answer = my_string.substring(0, s) + reversed + my_string.substring(e + 1);
        return answer;
    }
}