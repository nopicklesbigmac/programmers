class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        String str1 = my_string.substring(0, s);
        String str2 = my_string.substring(s + overwrite_string.length());
        
        String answer = str1 + overwrite_string + str2;
        
        return answer;
    }
}