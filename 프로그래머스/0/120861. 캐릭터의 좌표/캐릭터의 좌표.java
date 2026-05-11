class Solution {
    public int[] solution(String[] keyinput, int[] board) {
        int[] answer = {};
        int x = 0;
        int y = 0;
        
        int xLimit = board[0] / 2;
        int yLimit = board[1] / 2;
        
        for (String key : keyinput) {
            if (key.equals("up")) {
                if (y < yLimit) y++;
            } else if (key.equals("down")) {
                if (y > -yLimit) y--;
            } else if (key.equals("left")) {
                if (x > -xLimit) x--;
            } else if (key.equals("right")) {
                if (x < xLimit) x++;
            }
        }
        
        answer = new int[]{x, y};
        return answer;
    }
}