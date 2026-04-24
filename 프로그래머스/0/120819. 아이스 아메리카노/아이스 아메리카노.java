class Solution {
    public int[] solution(int money) {
        int[] answer = {};
        int americano = money / 5500;
        int change = money % 5500;
        answer = new int[] {americano, change};
        return answer;
    }
}