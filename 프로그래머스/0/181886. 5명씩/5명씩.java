class Solution {
    public String[] solution(String[] names) {
        String[] answer = {};
        int size = (names.length + 4) / 5;
        answer = new String[size];
        for (int i = 0; i < size; i++) {
            answer[i] = names[i * 5];
        }
        return answer;
    }
}