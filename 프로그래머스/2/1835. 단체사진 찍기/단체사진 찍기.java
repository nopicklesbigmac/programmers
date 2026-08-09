class Solution {
    private String[] conditions;
    private int count;
    private char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private boolean[] visited;
    private char[] currentArr;
    public int solution(int n, String[] data) {
        int answer = 0;
        conditions = data;
        count = 0;
        visited = new boolean[8];
        currentArr = new char[8];
        
        dfs(0);
        answer = count;
        return answer;
    }
private void dfs(int depth) {
        if (depth == 8) {
            if (checkConditions()) {
                count++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                currentArr[depth] = friends[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    private boolean checkConditions() {
        for (String cond : conditions) {
            char f1 = cond.charAt(0);
            char f2 = cond.charAt(2);
            char op = cond.charAt(3);
            int dist = cond.charAt(4) - '0';

            int idx1 = -1, idx2 = -1;
            for (int i = 0; i < 8; i++) {
                if (currentArr[i] == f1) idx1 = i;
                if (currentArr[i] == f2) idx2 = i;
            }

            int actualDist = Math.abs(idx1 - idx2) - 1;

            if (op == '=') {
                if (actualDist != dist) return false;
            } else if (op == '<') {
                if (actualDist >= dist) return false;
            } else if (op == '>') {
                if (actualDist <= dist) return false;
            }
        }
        return true;
    }
}