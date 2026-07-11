import java.util.Arrays;
class Solution {
    public int solution(int[] mats, String[][] park) {
       int answer = -1;
        Arrays.sort(mats);
        
        int rows = park.length;
        int cols = park[0].length;

        for (int i = mats.length - 1; i >= 0; i--) {
            int size = mats[i];
            
            for (int r = 0; r <= rows - size; r++) {
                for (int c = 0; c <= cols - size; c++) {
                    if (canPlace(park, r, c, size)) {
                        answer = size;
                        return answer;
                    }
                }
            }
        }
        
        return answer;
    }

    private boolean canPlace(String[][] park, int r, int c, int size) {
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (!park[i][j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }
}