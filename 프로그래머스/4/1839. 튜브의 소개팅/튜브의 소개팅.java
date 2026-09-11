import java.util.PriorityQueue;

class Solution {
    public int[] solution(int m, int n, int s, int[][] time_map) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        long[][] minTalkTime = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                minTalkTime[i][j] = Long.MAX_VALUE;
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.dist != b.dist) {
                return Integer.compare(a.dist, b.dist);
            }
            return Long.compare(a.talk, b.talk);
        });
        
        pq.add(new Node(0, 0, 0, 0));
        minTalkTime[0][0] = 0;
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            
            if (current.r == m - 1 && current.c == n - 1) {
                return new int[]{current.dist, (int) current.talk};
            }
            
            if (current.talk > minTalkTime[current.r][current.c]) {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = current.r + dr[i];
                int nc = current.c + dc[i];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n || time_map[nr][nc] == -1) {
                    continue;
                }
                
                int nextDist = current.dist + 1;
                long nextTalk = current.talk + time_map[nr][nc];
                
                if (nextTalk > s) {
                    continue;
                }
                
                if (nextTalk < minTalkTime[nr][nc]) {
                    minTalkTime[nr][nc] = nextTalk;
                    pq.add(new Node(nr, nc, nextDist, nextTalk));
                }
            }
        }
        
        return new int[]{0, 0};
    }
    
    class Node {
        int r, c, dist;
        long talk;
        
        public Node(int r, int c, int dist, long talk) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.talk = talk;
        }
    }
}