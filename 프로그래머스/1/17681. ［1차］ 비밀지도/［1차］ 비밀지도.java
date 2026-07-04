class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = {};
        answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            int combined = arr1[i] | arr2[i];
            String binaryString = Integer.toBinaryString(combined);
            while (binaryString.length() < n) {
                binaryString = "0" + binaryString;
            }
            answer[i] = binaryString.replace('1', '#').replace('0', ' ');
        }
        
        return answer;
    }
}