class Solution {
    public String[] solution(String[] quiz) {
        String[] answer = {};
        answer = new String[quiz.length];
        
        for (int i = 0; i < quiz.length; i++) {
            String[] part = quiz[i].split(" ");
            
            int x = Integer.parseInt(part[0]);
            String operator = part[1];
            int y = Integer.parseInt(part[2]);
            int z = Integer.parseInt(part[4]);
            
            int result = 0;
            if (operator.equals("+")) {
                result = x + y;
            } else {
                result = x - y;
            }
            answer[i] = (result == z) ? "O" : "X";
        }
        return answer;
    }
}