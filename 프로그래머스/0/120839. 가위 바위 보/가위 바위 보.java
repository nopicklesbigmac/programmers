class Solution {
    public String solution(String rsp) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        for (String s : rsp.split("")) {
            if (s.equals("2")) {
                sb.append("0");
            } else if (s.equals("0")) {
                sb.append("5");
            } else if (s.equals("5")) {
                sb.append("2");
            }
        }
        
        answer = sb.toString();
        return answer;
    }
}