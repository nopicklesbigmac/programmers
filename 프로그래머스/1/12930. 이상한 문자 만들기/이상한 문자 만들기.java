class Solution {
    public String solution(String s) {
        String answer = "";
        String[] words = s.split(" ", -1);
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if (j % 2 == 0) {
                    sb.append(Character.toUpperCase(c));
                } else {
                    sb.append(Character.toLowerCase(c));
                }
            }
            if (i < words.length - 1) {
                sb.append(" ");
            }
        }
        
        answer = sb.toString();
        return answer;
    }
}