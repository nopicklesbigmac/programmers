class Solution {
    public String solution(String[] id_pw, String[][] db) {
        String answer = "";
        String inputId = id_pw[0];
        String inputPw = id_pw[1];
        answer = "fail";

        for (String[] user : db) {
            if (inputId.equals(user[0])) {
                if (inputPw.equals(user[1])) {
                    answer = "login";
                } else {
                    answer = "wrong pw";
                }
                break; 
            }
        }
        return answer;
    }
}