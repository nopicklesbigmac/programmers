class Solution {
    boolean solution(String s) {
        boolean answer = true;
        System.out.println("Hello Java");
        
        s = s.toLowerCase();
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'p') {
                count++;
            } else if (s.charAt(i) == 'y') {
                count--;
            }
        }

        answer = count == 0;

        return answer;
    }
}