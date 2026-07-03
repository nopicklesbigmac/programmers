class Solution {
    public String solution(int[] food) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i < food.length; i++) {
            int count = food[i] / 2;
            for (int j = 0; j < count; j++) {
                sb.append(i);
            }
        }
        
        String leftSide = sb.toString();
        String rightSide = sb.reverse().toString();
        
        answer = leftSide + "0" + rightSide;
        return answer;
    }
}