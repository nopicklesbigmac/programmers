class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = {};
        int zeroCount = 0;
        int matchCount = 0;
        
        for (int lotto : lottos) {
            if (lotto == 0) {
                zeroCount++;
            } else {
                for (int win : win_nums) {
                    if (lotto == win) {
                        matchCount++;
                        break;
                    }
                }
            }
        }
        
        int maxRank = getRank(matchCount + zeroCount);
        int minRank = getRank(matchCount);
        
        answer = new int[]{maxRank, minRank};
        return answer;
    }
    
    private int getRank(int matchCount) {
        switch (matchCount) {
            case 6: return 1;
            case 5: return 2;
            case 4: return 3;
            case 3: return 4;
            case 2: return 5;
            default: return 6;
        }
    }
}