
class Solution {
    public String[] solution(String[] picture, int k) {
        String[] answer = {};
        int rows = picture.length;
        int cols = picture[0].length();
        answer = new String[rows * k];
        
        int index = 0;
        for (String row : picture) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < row.length(); i++) {
                char pixel = row.charAt(i);
                for (int j = 0; j < k; j++) {
                    sb.append(pixel);
                }
            }
            String rowStr = sb.toString();
            for (int j = 0; j < k; j++) {
                answer[index++] = rowStr;
            }
        }
        return answer;
    }
}