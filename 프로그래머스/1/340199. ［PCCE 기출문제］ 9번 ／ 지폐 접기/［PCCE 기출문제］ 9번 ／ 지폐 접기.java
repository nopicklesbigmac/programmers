class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int wSmall = Math.min(wallet[0], wallet[1]);
        int wLarge = Math.max(wallet[0], wallet[1]);
        
        int bSmall = Math.min(bill[0], bill[1]);
        int bLarge = Math.max(bill[0], bill[1]);
        
        while (bSmall > wSmall || bLarge > wLarge) {
            if (bSmall > bLarge) {
                bSmall /= 2;
            } else {
                bLarge /= 2;
            }
            
            if (bSmall > bLarge) {
                int temp = bSmall;
                bSmall = bLarge;
                bLarge = temp;
            }
            
            answer++;
        }
        return answer;
    }
}