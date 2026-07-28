import java.util.Arrays;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int totalPicks = picks[0] + picks[1] + picks[2];
        int mineralCount = Math.min(minerals.length, totalPicks * 5);
        
        int blockCount = (mineralCount + 4) / 5;
        int[][] score = new int[blockCount][3];
        
        for (int i = 0; i < mineralCount; i++) {
            int blockIdx = i / 5;
            String m = minerals[i];
            
            score[blockIdx][0] += 1;
            
            if (m.equals("diamond")) {
                score[blockIdx][1] += 5;
                score[blockIdx][2] += 25;
            } else if (m.equals("iron")) {
                score[blockIdx][1] += 1;
                score[blockIdx][2] += 5;
            } else {
                score[blockIdx][1] += 1;
                score[blockIdx][2] += 1;
            }
        }
        
        Arrays.sort(score, (a, b) -> {
            if (b[2] != a[2]) {
                return b[2] - a[2];
            } else if (b[1] != a[1]) {
                return b[1] - a[1];
            } else {
                return b[0] - a[0];
            }
        });
        int dia = picks[0];
        int iron = picks[1];
        int stone = picks[2];
        
        for (int i = 0; i < blockCount; i++) {
            if (dia > 0) {
                answer += score[i][0];
                dia--;
            } else if (iron > 0) {
                answer += score[i][1];
                iron--;
            } else if (stone > 0) {
                answer += score[i][2];
                stone--;
            } else {
                break;
            }
        }
        
        return answer;
    }
}