class Solution {
    public String solution(int age) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        String agestr = String.valueOf(age);

        for (int i = 0; i < agestr.length(); i++) {
            sb.append((char) ('a' + (agestr.charAt(i) - '0')));
        }

        answer = sb.toString();
        return answer;
    }
}