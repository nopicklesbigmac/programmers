class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        int dist = Math.abs(x - r) + Math.abs(y - c);

        if (dist > k || (k - dist) % 2 != 0) {
            return "impossible";
        }

        StringBuilder sb = new StringBuilder();

        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        char[] move = {'d', 'l', 'r', 'u'};

        for (int step = 0; step < k; step++) {
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }

                int remain = k - step - 1;
                int nextDist = Math.abs(nx - r) + Math.abs(ny - c);

                if (nextDist <= remain && (remain - nextDist) % 2 == 0) {
                    x = nx;
                    y = ny;
                    sb.append(move[i]);
                    break;
                }
            }
        }

        answer = sb.toString();
        return answer;
    }
}