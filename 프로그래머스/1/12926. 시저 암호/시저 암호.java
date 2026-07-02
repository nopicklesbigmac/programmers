class Solution {
    public String solution(String s, int n) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(' ');
            } else if (c >= 'a' && c <= 'z') {
                sb.append((char) ((c - 'a' + n) % 26 + 'a'));
            } else if (c >= 'A' && c <= 'Z') {
                sb.append((char) ((c - 'A' + n) % 26 + 'A'));
            }
        }
        
        answer = sb.toString();
        return answer;
    }
}