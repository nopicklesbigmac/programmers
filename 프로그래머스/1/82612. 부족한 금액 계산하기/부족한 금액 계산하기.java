class Solution {
    public long solution(int price, int money, int count) {
        long answer = -1;
        long totalCost = 0;
        totalCost = (long) price * count * (count + 1) / 2;
        answer = totalCost <= money ? 0 : totalCost - money;
        return answer;
    }
}