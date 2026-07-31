class Solution {
    static int maxSubscribers = 0;
    static int maxSales = 0;
    static int[] discountRates = {10, 20, 30, 40};
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};
        maxSubscribers = 0;
        maxSales = 0;
        
        dfs(0, new int[emoticons.length], users, emoticons);
        
        answer = new int[]{maxSubscribers, maxSales};
        return answer;
    }
private void dfs(int depth, int[] currentDiscounts, int[][] users, int[] emoticons) {
        if (depth == emoticons.length) {
            calculate(currentDiscounts, users, emoticons);
            return;
        }
        
        for (int rate : discountRates) {
            currentDiscounts[depth] = rate;
            dfs(depth + 1, currentDiscounts, users, emoticons);
        }
    }
    
    private void calculate(int[] discounts, int[][] users, int[] emoticons) {
        int subscribers = 0;
        int sales = 0;
        
        for (int[] user : users) {
            int userRateLimit = user[0];
            int userPriceLimit = user[1];
            
            int purchasedSum = 0;
            for (int i = 0; i < emoticons.length; i++) {
                if (discounts[i] >= userRateLimit) {
                    purchasedSum += emoticons[i] * (100 - discounts[i]) / 100;
                }
            }
            
            if (purchasedSum >= userPriceLimit) {
                subscribers++;
            } else {
                sales += purchasedSum;
            }
        }
        
        if (subscribers > maxSubscribers) {
            maxSubscribers = subscribers;
            maxSales = sales;
        } else if (subscribers == maxSubscribers) {
            if (sales > maxSales) {
                maxSales = sales;
            }
        }
    }
}