import java.util.ArrayList;
import java.util.List;
class Solution {
    public int[][] solution(int n) {
        int[][] answer = {};
        
        List<int[]> moves = new ArrayList<>();
        hanoi(n, 1, 3, 2, moves);
        answer = moves.toArray(new int[moves.size()][]);
        return answer;
    }
    
    private void hanoi(int n, int from, int to, int aux, List<int[]> moves) {
        if (n == 1) {
            moves.add(new int[]{from, to});
            return;
        }
        hanoi(n - 1, from, aux, to, moves);
        moves.add(new int[]{from, to});
        hanoi(n - 1, aux, to, from, moves);
    }
}