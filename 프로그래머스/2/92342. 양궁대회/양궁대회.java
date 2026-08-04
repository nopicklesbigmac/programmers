class Solution {
   int maxDiff = -1;
    int[] bestRyan = {-1};

    public int[] solution(int n, int[] info) {
        int[] ryan = new int[11];
        backtrack(0, n, info, ryan);
        return bestRyan;
    }

    private void backtrack(int idx, int remainingArrows, int[] info, int[] ryan) {
        if (idx == 10) {
            ryan[10] = remainingArrows;
            calculateScore(info, ryan);
            ryan[10] = 0;
            return;
        }
        int required = info[idx] + 1;
        if (remainingArrows >= required) {
            ryan[idx] = required;
            backtrack(idx + 1, remainingArrows - required, info, ryan);
            ryan[idx] = 0;
        }
        ryan[idx] = 0;
        backtrack(idx + 1, remainingArrows, info, ryan);
    }

    private void calculateScore(int[] info, int[] ryan) {
        int apeachScore = 0;
        int ryanScore = 0;

        for (int i = 0; i < 11; i++) {
            if (info[i] == 0 && ryan[i] == 0) continue;
            
            if (info[i] >= ryan[i]) {
                apeachScore += (10 - i);
            } else {
                ryanScore += (10 - i);
            }
        }

        int diff = ryanScore - apeachScore;
        if (diff > 0) {
            if (diff > maxDiff) {
                maxDiff = diff;
                bestRyan = ryan.clone();
            } else if (diff == maxDiff) {
                if (isBetter(ryan, bestRyan)) {
                    bestRyan = ryan.clone();
                }
            }
        }
    }

    private boolean isBetter(int[] ryan, int[] currentBest) {
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] > currentBest[i]) return true;
            if (ryan[i] < currentBest[i]) return false;
        }
        return false;
    }
}