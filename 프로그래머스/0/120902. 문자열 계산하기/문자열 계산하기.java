class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String[] part = my_string.split(" ");
        answer = Integer.parseInt(part[0]);
        
        for (int i = 1; i < part.length; i += 2) {
            String operator = part[i];
            int next = Integer.parseInt(part[i + 1]);
            
            if (operator.equals("+")) {
                answer += next;
            } else if (operator.equals("-")) {
                answer -= next;
            }
        }
        return answer;
    }
}