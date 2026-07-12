import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        int n = friends.length;
        Map<String, Integer> nameMap = new HashMap<>();
        for (int i = 0; i < n; i++) nameMap.put(friends[i], i);

        int[][] giftMap = new int[n][n];
        int[] giftedCount = new int[n];
        int[] receivedCount = new int[n];

        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            int giver = nameMap.get(parts[0]);
            int receiver = nameMap.get(parts[1]);
            
            giftMap[giver][receiver]++;
            giftedCount[giver]++;
            receivedCount[receiver]++;
        }

        int[] nextMonthGifts = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (giftMap[i][j] > giftMap[j][i]) {
                    nextMonthGifts[i]++;
                } 
                else if (giftMap[i][j] < giftMap[j][i]) {
                    nextMonthGifts[j]++;
                } 
                else {
                    int indexI = giftedCount[i] - receivedCount[i];
                    int indexJ = giftedCount[j] - receivedCount[j];
                    
                    if (indexI > indexJ) nextMonthGifts[i]++;
                    else if (indexJ > indexI) nextMonthGifts[j]++;
                }
            }
        }

        for (int count : nextMonthGifts) {
            answer = Math.max(answer, count);
        }

        return answer;
    }
}