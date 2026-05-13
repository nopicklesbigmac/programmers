class Solution {
    public int solution(String my_string) {
        int answer = 0;
        String replace = my_string.replaceAll("[^0-9]", " ");
        String[] number = replace.split("\\s+");
        
        for (String n : number) {
            if (!n.isEmpty()) {
                answer += Integer.parseInt(n);
            }
        }
        return answer;
    }
}