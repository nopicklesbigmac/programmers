class Solution {
    public int[] solution(String myString) {
        int[] answer = {};
        String[] splitArr = myString.split("x", -1);
        answer = new int[splitArr.length];
        for (int i = 0; i < splitArr.length; i++) {
            answer[i] = splitArr[i].length();
        }
        
        return answer;
    }
}