class Solution {
    public int solution(String myString, String pat) {
        int answer = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myString.length(); i++) {
            char c = myString.charAt(i);
            if (c == 'A') {
                sb.append('B');
            } else if (c == 'B') {
                sb.append('A');
            }
        }
        String converted = sb.toString();
        answer = converted.contains(pat) ? 1 : 0;
        return answer;
    }
}