import java.util.*;
class Solution {
    Set<Integer> result = new HashSet<>();
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        dfs(0, 0, user_id, banned_id);
        answer = result.size();
        return answer;
    }
    void dfs(int depth, int used, String[] user_id, String[] banned_id) {
        if (depth == banned_id.length) {
            result.add(used);
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if ((used & (1 << i)) != 0) continue;

            if (match(user_id[i], banned_id[depth])) {
                dfs(depth + 1, used | (1 << i), user_id, banned_id);
            }
        }
    }

    boolean match(String user, String banned) {
        if (user.length() != banned.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != '*' && banned.charAt(i) != user.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
