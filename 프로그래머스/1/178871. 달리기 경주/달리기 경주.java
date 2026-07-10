import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = {};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }

        for (String calling : callings) {
            int curRank = map.get(calling);
            String frontPlayer = players[curRank - 1];
            players[curRank - 1] = calling;
            players[curRank] = frontPlayer;
            map.put(calling, curRank - 1);
            map.put(frontPlayer, curRank);
        }
        answer = players;
        return players;
    }
}
