class Solution {
    public int solution(String s) {
        int answer = 0;
        String[] strs = s.split(" ");
        int x = 0;
        
        for (String str : strs) {
            if (str.equals("Z")) {
                answer -= x;
            } else {
                x = Integer.parseInt(str);
                answer += x;
            }
        }
        return answer;
    }
}