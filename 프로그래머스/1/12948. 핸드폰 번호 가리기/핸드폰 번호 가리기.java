class Solution {
    public String solution(String phone_number) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int len = phone_number.length();
        for (int i = 0; i < len - 4; i++) {
            sb.append("*");
        }
        sb.append(phone_number.substring(len - 4));
        answer = sb.toString();
        return answer;
    }
}