class Solution {
    public String solution(String my_string, int[][] queries) {
        String answer = "";
        StringBuilder sb = new StringBuilder(my_string);
        
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            String sub = sb.substring(start, end + 1);
            StringBuilder reversedSub = new StringBuilder(sub).reverse();
            sb.replace(start, end + 1, reversedSub.toString());
        }
        
        answer = sb.toString();
        return answer;
    }
}