class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        int transformCount = 0;
        int removedZeroCount = 0;

        while (!s.equals("1")) {
            int originalLength = s.length();
            s = s.replace("0", "");
            int newLength = s.length();
            
            removedZeroCount += (originalLength - newLength);
            s = Integer.toBinaryString(newLength);
            transformCount++;
        }

        answer = new int[]{transformCount, removedZeroCount};
        
        return answer;
    }
}